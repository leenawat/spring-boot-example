package com.example.demo.exception;

import com.example.demo.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(JwtAuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleJwtAuthenticationException(
            JwtAuthenticationException ex,
            HttpServletRequest request) {
        return ErrorResponse.of(
            ex.getMessage(),
            HttpStatus.UNAUTHORIZED.value(),
            "Unauthorized",
            request.getRequestURI()
        );
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleExpiredJwtException(
            ExpiredJwtException ex,
            HttpServletRequest request) {
        return ErrorResponse.of(
            "Token has expired",
            HttpStatus.UNAUTHORIZED.value(),
            "Unauthorized",
            request.getRequestURI()
        );
    }

    @ExceptionHandler(MalformedJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleMalformedJwtException(
            MalformedJwtException ex,
            HttpServletRequest request) {
        return ErrorResponse.of(
            "Malformed token",
            HttpStatus.UNAUTHORIZED.value(),
            "Unauthorized",
            request.getRequestURI()
        );
    }

    @ExceptionHandler(SignatureException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleSignatureException(
            SignatureException ex,
            HttpServletRequest request) {
        return ErrorResponse.of(
            "Invalid token signature",
            HttpStatus.UNAUTHORIZED.value(),
            "Unauthorized",
            request.getRequestURI()
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGlobalException(
            Exception ex,
            HttpServletRequest request) {
        log.error("Unexpected error occurred", ex);
        return ErrorResponse.of(
            "Internal server error",
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal Server Error",
            request.getRequestURI()
        );
    }
}