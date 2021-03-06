package zadacha.tz.ist_telecom.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import zadacha.tz.ist_telecom.repository.UserRepository;

import java.util.Date;

@Component
public class JwtProvider {

    @Autowired
    UserRepository userRepository;
    @Value("${jwt.secretKey}")
    private String secretKey;
    @Value("${jwt.expireTime}")
    private long expire;

    public String generateToken(String username) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512,secretKey)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();//yaratish xuddi save qilganday
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean expireToken(String token) {
        try {

            Date expiration = Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();
            return expiration.after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
