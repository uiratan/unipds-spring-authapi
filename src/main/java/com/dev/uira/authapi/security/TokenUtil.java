package com.dev.uira.authapi.security;

import com.dev.uira.authapi.controller.MyToken;
import com.dev.uira.authapi.model.User;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

public class TokenUtil {

    private static final String EMITER = "UiratanCavalcante";
    private static final long EXPIRATION_TIME = 60 * 60 * 1000;
    private static final String SECRET_KEY = "0123456789012345678901234567890123456789";

    public static MyToken encode(User user) {
        try {
            Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
            String jwtToken = Jwts.builder()
                    .subject(user.getUsername())
                    .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .issuer(EMITER)
                    .claim(EMITER, key)
                    .signWith(key)
                    .compact();
            return new MyToken(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

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
