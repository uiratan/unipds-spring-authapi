package com.dev.uira.authapi.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Collections;

public class TokenUtil {

    public static Authentication decode(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        System.out.println("header: " + header);
        if (header != null && isValidToken(header)) {
            return new UsernamePasswordAuthenticationToken("valido", null, Collections.emptyList());
        }
        return null;
    }

    private static boolean isValidToken(String header) {
        String token = getHeaderToken(header);
        return token.equals("security123");
    }

    private static String getHeaderToken(String header) {
        return header.replace("Bearer ", "");
    }
}
