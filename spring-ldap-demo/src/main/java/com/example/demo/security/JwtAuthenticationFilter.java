package com.example.demo.security;

import com.example.demo.dto.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider tokenProvider;

    private final ObjectMapper objectMapper;

    // สร้าง list ของ path ที่ไม่ต้องตรวจสอบ token
    private final List<String> excludedPaths = Arrays.asList(
            "/api/auth/login",
            "/api/auth/register",  // ถ้ามี path อื่นๆ เพิ่มได้
            "/api/public/**"       // ใช้ /** สำหรับ path ที่มี sub path
    );

    public JwtAuthenticationFilter() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        if (request.getDispatcherType() != DispatcherType.REQUEST) {
            return true;
        }
        String path = request.getRequestURI();
        // ตรวจสอบว่า path ปัจจุบันตรงกับ path ที่ exclude หรือไม่
        return excludedPaths.stream()
                .anyMatch(p -> {
                    // ถ้า pattern มี /** ให้ใช้ antMatcher
                    if (p.endsWith("/**")) {
                        return new AntPathMatcher().match(p, path);
                    }
                    // ถ้าไม่มี /** ให้เทียบ path ตรงๆ
                    return path.equals(p);
                });
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);

            // กรณีไม่มี token
            if (!StringUtils.hasText(jwt)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("No token provided");
                return;
            }


            try {

                // กรณี token ไม่ถูกต้อง
                if (!tokenProvider.validateToken(jwt)) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Invalid token");
                    return;
                }

                String username = tokenProvider.getUsernameFromJWT(jwt);
                List<String> roles = tokenProvider.getRolesFromJWT(jwt);

                List<GrantedAuthority> authorities = roles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(username, null, authorities);
                authentication.setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(request));

                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);

            } catch (ExpiredJwtException e) {
                sendErrorResponse(response, request,
                        "Token has expired", "JWT_AUTH_002");
                return;
            } catch (MalformedJwtException e) {
                sendErrorResponse(response, request,
                        "Malformed token", "JWT_AUTH_003");
                return;
            } catch (SignatureException e) {
                sendErrorResponse(response, request,
                        "Invalid token signature", "JWT_AUTH_004");
                return;
            } catch (Exception e) {
                log.error("Authentication error", e);
                sendErrorResponse(response, request,
                        "Authentication failed", "SYSTEM_001");
                return;
            }

        } catch (Exception ex) {
            log.error("Could not set user authentication in security context", ex);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Authentication failed");
            return;
        }

        filterChain.doFilter(request, response);
    }
    private void sendErrorResponse(
            HttpServletResponse response,
            HttpServletRequest request,
            String message,
            String code) throws IOException {

        ErrorResponse errorResponse = ErrorResponse.of(
                message,
                HttpStatus.UNAUTHORIZED.value(),
                code,
                request.getRequestURI()
        );

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.getWriter().write(
                objectMapper.writeValueAsString(errorResponse)
        );
    }
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}