package project.irfanadios.apigateway.util;

import java.security.Key;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${irfanadios.app.jwt-secret}")
    private String jwtSecret;

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("JWT Invalid: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT Expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT Unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT is Empty: {}", e.getMessage());
        }
        return false;
    }

    private Key getKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }
}
