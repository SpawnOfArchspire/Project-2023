package com.ascendingdc.learnrestapi.service.impl;

import com.ascendingdc.learnrestapi.dto.RoleDto;
import com.ascendingdc.learnrestapi.dto.UserDto;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

import static io.jsonwebtoken.Claims.ISSUER;

@Service
public class JWTServiceImpl implements JWTService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private final String SECRET_KEY = System.getenv("secret_key");
    private final long EXPIRATION_TIME = 86400 * 1000;
    @Override
    public String generateToken(UserDto userDto) {
        logger.info(", retrieved SECRET_KEY={}", SECRET_KEY);
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
        Claims claims = Jwts.claims();
        claims.setId(String.valueOf(userDto.getId()));
        claims.setSubject(userDto.getName());
        claims.setIssuedAt(new Date());
        claims.setIssuer(ISSUER);
        claims.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME));
        JwtBuilder jwtBuilder = Jwts.builder().setClaims(claims).signWith(signatureAlgorithm, SECRET_KEY);
        String generatedToken = jwtBuilder.compact();

        String allowedReadResources = "";
        String allowedCreateResources = "";
        String allowedUpdateResources = "";
        String allowedDeleteResources = "";

        Set<RoleDto> roleDtoSet = userDto.getRoleDtoSet();
        for(RoleDto roleDto : roleDtoSet){
            if(roleDto.isAllowedRead())
                allowedReadResources = String.join(",", roleDto.getAllowedResource(), allowedReadResources);
            if(roleDto.isAllowedRead())
                allowedCreateResources = String.join(",", roleDto.getAllowedResource(), allowedCreateResources);
            if(roleDto.isAllowedRead())
                allowedUpdateResources = String.join(",", roleDto.getAllowedResource(), allowedUpdateResources);
            if(roleDto.isAllowedRead())
                allowedDeleteResources = String.join(",", roleDto.getAllowedResource(), allowedDeleteResources);
        }
        logger.info("===, The final result of allowedReadResources = {}", allowedReadResources);
        logger.info("===, The final result of allowedCreateResources = {}", allowedCreateResources);
        logger.info("===, The final result of allowedUpdateResources = {}", allowedUpdateResources);
        logger.info("===, The final result of allowedDeleteResources = {}", allowedDeleteResources);

        claims.put("allowedReadResources", allowedReadResources.replaceAll(",$",""));
        claims.put("allowedCreateResources", allowedCreateResources.replaceAll(",$",""));
        claims.put("allowedUpdateResources", allowedUpdateResources.replaceAll(",$",""));
        claims.put("allowedDeleteResources", allowedDeleteResources.replaceAll(",$",""));

        return generatedToken;
    }

    @Override
    public Claims decryptJwtToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        logger.info("parsed claims = {}", claims);
        return claims;
    }

    @Override
    public boolean hasTokenExpired(String token) {
        boolean isTokenExpired = true;

       try {
           Claims claims = decryptJwtToken(token);
           Date tokenExpirationDate = claims.getExpiration();
           Date nowDate = new Date();
           isTokenExpired = nowDate.before(tokenExpirationDate);
       } catch (ExpiredJwtException e){
           logger.error("ExpiredJwtException is Caught, error", e.getMessage());
       }
        return isTokenExpired;
    }

    @Override
    public boolean validateAccessToken(String token) {
        boolean isTokenValid = false;
        try{
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            isTokenValid = true;
        } catch (ExpiredJwtException ex){
            logger.error("JWT expired", ex.getMessage());
        } catch (IllegalArgumentException ex){
            logger.error("Token is null, empty or only whitespace", ex.getMessage());
        } catch (MalformedJwtException ex){
            logger.error("JWT is invalid", ex);
        } catch (UnsupportedJwtException ex){
            logger.error("JWT is not supported",ex);
        } catch (SignatureException ex){
            logger.error("Signature validation failed");
        }
        return isTokenValid;
    }
}
