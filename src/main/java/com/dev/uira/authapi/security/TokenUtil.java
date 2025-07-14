package com.dev.uira.authapi.security;

import com.dev.uira.authapi.controller.MyToken;
import com.dev.uira.authapi.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Collections;
import java.util.Date;

public class TokenUtil {

    private static final String EMITER = "UiratanCavalcante";
    private static final long EXPIRATION_TIME = 15 * 1000;
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
        try {
            String header = request.getHeader("Authorization");
            System.out.println("header: " + header);
            if (header != null) {
                String token = header.replace("Bearer ", "");
                SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
                JwtParser parser = Jwts.parser().verifyWith(key).build();
                Claims claims = parser.parseClaimsJws(token).getBody();

                String subject = claims.getSubject();
                String issuer = claims.getIssuer();
                Date expiration = claims.getExpiration();

                if (issuer.equals(EMITER) && subject.length() > 0 && expiration.after(new Date(System.currentTimeMillis()))) {
                    return new UsernamePasswordAuthenticationToken("valido", null, Collections.emptyList());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
