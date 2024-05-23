package gameet.security;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

	private static final String SECRET_WORD = "uywef384FREHsdgrt8te8732874rwgFDEfAFWETGsa";
    private static final SecretKey SECRET_KEY = new SecretKeySpec(SECRET_WORD.getBytes(), SignatureAlgorithm.HS256.getJcaName());
  
    private static final long EXPIRATION_TIME = 86400000;
    
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)  // Utiliza la clave secreta
                .compact();
    }

    public static String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public static boolean validateToken(String token) {
        try {
            Claims claims = extractClaims(token);
            Date expiration = claims.getExpiration();
            return expiration.after(new Date());
        } catch (ExpiredJwtException e) {
            return false;
        }
    }

    private static Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)  // Utiliza la clave secreta
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
