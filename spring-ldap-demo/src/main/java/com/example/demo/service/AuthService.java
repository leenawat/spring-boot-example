package com.example.demo.service;

import com.example.demo.security.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.NamingException;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import javax.naming.directory.Attributes;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class AuthService {

    @Autowired
    private LdapTemplate ldapTemplate;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public String login(String username, String password) {
        // ตรวจสอบ credentials
        if (!authenticate(username, password)) {
            throw new BadCredentialsException("Invalid credentials");
        }

        // ดึง roles
        List<String> roles = getUserRoles(username);

        // สร้าง JWT
        return jwtTokenProvider.createToken(username, roles);
    }

    private boolean authenticate(String username, String password) {
        try {
            AndFilter filter = new AndFilter();
            filter.and(new EqualsFilter("objectclass", "person"))
                    .and(new EqualsFilter("uid", username));

            return ldapTemplate.authenticate("ou=users", filter.encode(), password);
        } catch (Exception e) {
            log.error("Authentication failed for user: " + username, e);
            return false;
        }
    }

    private List<String> getUserRoles(String username) {
        try {
            String userDn = String.format("uid=%s,ou=users,dc=mycompany,dc=com", username);

            AndFilter filter = new AndFilter();
            filter.and(new EqualsFilter("objectclass", "groupOfUniqueNames"))
                    .and(new EqualsFilter("uniqueMember", userDn));

            return ldapTemplate.search(
                    "ou=groups",
                    filter.encode(),
                    new AttributesMapper<String>() {
                        @Override
                        public String mapFromAttributes(Attributes attrs)
                                throws NamingException, javax.naming.NamingException {
                            return attrs.get("cn").get().toString();
                        }
                    }
            );
        } catch (Exception e) {
            log.error("Error getting user roles", e);
            return Collections.emptyList();
        }
    }
}
