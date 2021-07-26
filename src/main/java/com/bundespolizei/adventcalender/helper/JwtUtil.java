package com.bundespolizei.adventcalender.helper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.json.JsonParseException;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class JwtUtil {
    public static final Key KEY;

    static {
        String keyString = System.getenv("JWT_SECRET");
        if (keyString == null) {
            keyString = "EMPTY";
        }
        KEY = new SecretKeySpec(keyString.getBytes(), SignatureAlgorithm.HS256.getJcaName());
    }

    public static String encodeAsJWT(String data, Key key) {
        return Jwts.builder().setSubject(data).signWith(key).compact();
    }

    public static String decodeJWT(String jwt, Key key) {
        String result;
        try {
            result = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody().getSubject();
        } catch (Exception exception) {
            result = "";
        }
        return result;
    }
}
