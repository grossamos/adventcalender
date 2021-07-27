package com.bundespolizei.adventcalender.helper;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import java.security.Key;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    @Test
    @Disabled
    void can_encode_and_decode_jwt_tokens() {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String start = "Hello World!";
        String jwt = JwtUtil.encodeAsJWT(start, key);
        String end = JwtUtil.decodeJWT(jwt, key);
        assertEquals(start, end);
    }

}