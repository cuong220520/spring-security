package com.workshop.ivar.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.gson.io.GsonSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author cuongnd9
 * @date 31/03/2023
 * @project ivar
 * @package com.workshop.ivar.util
 */
@Component
@RequiredArgsConstructor
public class JwtUtil {

    @Value("${jwt.expire_in:15}")
    private Long jwtExpiresIn;

    private final Key jwtSigningKey;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean hasClaim(String token, String claimName) {
        final Claims claims = extractAllClaims(token);
        return claims.containsKey(claimName);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtSigningKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails, Map<String, Object> claims) {
        return createToken(claims, userDetails);
    }

    public String generateToken(UserDetails userDetails) {
        return createToken(null, userDetails);
    }

    private String createToken(Map<String, Object> claims, UserDetails userDetails) {
        Date now = DateUtil.getCurrentDateTime();
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .claim("authorities", userDetails.getAuthorities())
                .setIssuedAt(now)
                .setExpiration(DateUtil.addTimeMillis(now, TimeUnit.MINUTES.toMillis(jwtExpiresIn)))
                .signWith(jwtSigningKey)
                .compact();
    }

    private boolean isTokenExpired(String token) {
        return StringUtils.hasText(token) && extractExpiration(token).before(DateUtil.getCurrentDateTime());
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

}
