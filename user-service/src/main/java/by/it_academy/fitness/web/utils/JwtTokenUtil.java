package by.it_academy.fitness.web.utils;

import by.it_academy.fitness.core.dto.user.UserDTO;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JwtTokenUtil {

    private static final String jwtSecret = "NDQ1ZjAzNjQtMzViZi00MDRjLTljZjQtNjNjYWIyZTU5ZDYw";
    private static final String jwtIssuer = "ITAcademy";

    public static String generateAccessToken(Map<String,Object> claims,String name) {
        return Jwts.builder().setClaims(claims)
                .setSubject(name)
                .setIssuer(jwtIssuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(7))) // 1 week
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    // генерация токена (кладем в него имя пользователя и authorities)
    public static String generateToken(UserDTO userDTO) {
        Map<String, Object> claims = new HashMap<>();
        String commaSeparatedListOfAuthorities=  userDTO.getAuthorities().stream().map(a->a.getAuthority()).collect(Collectors.joining(","));
        claims.put("authorities", commaSeparatedListOfAuthorities);
        return generateAccessToken(claims, userDTO.getUsername());
    }

    //извлечение имени пользователя из токена (внутри валидация токена)
    public static String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    //извлечение authorities (внутри валидация токена)
    public static String extractAuthorities(String token) {
        return extractClaim(token, claims -> (String)claims.get("authorities"));
    }
    private static  <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private static Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

    public static Date getExpirationDate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }

    public static boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
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
