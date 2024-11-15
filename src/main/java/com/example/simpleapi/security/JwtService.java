package com.example.simpleapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "7538782F413F4428472B4B6150645367566B5970337336763979244226452948";

    public String extractUsername(String jwt){
        return  extractClaim(jwt, Claims::getSubject);
    }


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }



    private Claims extractAllClaims (String token) {

//        if (!isTokenExpired(token))
            return Jwts.parserBuilder().setSigningKey(getSigningKey())
                    .build().parseClaimsJws(token).getBody();

//        return null;
    }


    private Key getSigningKey() {

        byte [] keyBytes  = Decoders.BASE64URL.decode(SECRET_KEY);
//        return  Keys.secretKeyFor(SignatureAlgorithm.HS256);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Generate a jwt token without extra claims
    public Map<String, String> generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    // Generate a jwt token with extra claims
    public Map<String, String> generateToken(
            Map<String, Object> extraClaims, /* this map will contain the extra claims to be added*/
            UserDetails userDetails
    ) {
        Map <String, String> tokens = new HashMap<>();

        tokens.put("refresh token", generateToken(extraClaims, userDetails, 1440L));
        tokens.put("access token", generateToken(extraClaims, userDetails, 720L));// compact will generate and return the token
        return tokens;
    }


    public String generateToken(
            Map<String, Object> extraClaims, /* this map will contain the extra claims to be added*/
            UserDetails userDetails, Long expired
    ) {
        return  Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis())) // used to return the time when the token will created
                .setExpiration(new Date (System.currentTimeMillis() + 1000 * 60 * expired))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact(); // compact will generate and return the token
    }

    //Validate the jwt
    public boolean isValidToken(String jwt, UserDetails userDetails) {
        final String username = userDetails.getUsername();

        return username.equals(extractUsername(jwt)) && !isTokenExpired(jwt);
    }


    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}

