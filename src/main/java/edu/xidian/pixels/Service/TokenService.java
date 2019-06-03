package edu.xidian.pixels.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.springframework.stereotype.Service;

import edu.xidian.pixels.Entity.User;

/**
 * TokenService
 */
@Service
public class TokenService {

    public String getToken(User user) {
        String token = "";
        token = JWT.create().withAudience(String.valueOf(user.getAccount()))
                    .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}