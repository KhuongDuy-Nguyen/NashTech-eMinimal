package com.eminimal.backend.jwt;

import com.eminimal.backend.models.CustomUserDetails;
import com.eminimal.backend.models.UsersToken;
import com.eminimal.backend.repository.UsersTokenRepository;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    // Đoạn JWT_SECRET này là bí mật, chỉ có phía server biết
    private final String JWT_SECRET = "eminimal-secretkey";

    //Thời gian có hiệu lực của chuỗi jwt
    private final long JWT_EXPIRATION = 604800000L;

    @Autowired
    private UsersTokenRepository tokenRepository;

    // Tạo ra jwt từ thông tin user
    public UsersToken generateToken(CustomUserDetails userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);

        // Tạo chuỗi json web token từ id của user.
        String token = Jwts.builder()
                .setSubject((userDetails.getUsers().getUserId()))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();

        UsersToken usersToken = new UsersToken(userDetails.getUsers().getUserId(), token, new Date() ,expiryDate);
        return  tokenRepository.save(usersToken);
    }



    // Lấy thông tin user từ jwt
    public String getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            log.error("Token: " + authToken);
            log.error("--> " + Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken));

            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;

        } catch (MalformedJwtException ex) {
            log.error(ex.getMessage());
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
