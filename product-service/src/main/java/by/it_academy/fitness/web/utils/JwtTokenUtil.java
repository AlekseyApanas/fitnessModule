package by.it_academy.fitness.web.utils;

import by.it_academy.fitness.web.utils.properties.JWTProperty;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;


import java.util.function.Function;

@Component
public class JwtTokenUtil {

    private final JWTProperty property;

    public JwtTokenUtil(JWTProperty property) {
        this.property = property;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractAuthorities(String token) {
        return extractClaim(token, claims -> (String) claims.get("authorities"));
    }

    public String extractUUID(String token) {
        return extractClaim(token, claims -> (String) claims.get("uuid"));
    }

    public String extractFio(String token) {
        return extractClaim(token, claims -> (String) claims.get("fio"));
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(property.getSecret()).parseClaimsJws(token).getBody();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(property.getSecret()).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            //logger.error("Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            //logger.error("Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            //logger.error("Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            //logger.error("JWT claims string is empty - {}", ex.getMessage());
        }
        return false;
    }
}
