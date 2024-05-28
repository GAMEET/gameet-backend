package gameet.service;


import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class StreamChatService {

    public String generaToken(String username) {
        var calendar = new GregorianCalendar();
        calendar.add(Calendar.MINUTE, 240);
        String apiSecret = "4e2g24j65p6uhcx6kr6qs8c2j746zkw3mhtf8dsud9vd3jwefb4tc94hmbqnjmwh";
        return createStreamChatToken(username, calendar.getTime(), apiSecret);
    }

    private String createStreamChatToken(String username, Date expiresAt, String apiSecret) {
        Key key = Keys.hmacShaKeyFor(apiSecret.getBytes());

        String token = Jwts.builder()
                .claim("user_id", username)
                .claim("role", "admin")
                .setExpiration(expiresAt)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        return token;
    }
}