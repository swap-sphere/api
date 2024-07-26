package com.example.spring_security_demo.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    //hardcoding the secret key or we can use generateSecretKey
    private static final String SECRET = "TmV3U2VjcmV0S2V5Rm9ySldUU2lnbmluZ1B1cnBvc2VzMTIzNDU2Nzg=\r\n";
    private String secretKey;
    public JwtService(){
        secretKey = generateSecretKey();
    }
    //here the functn generates the secret key by the keyGenerator class which uses the HmacSHA256 algo,and print the key and encode and we decode later in the getKey() functn..!!
    public String generateSecretKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey secretKey = keyGen.generateKey();
            System.out.println("Secret Key : " + secretKey.toString());
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating secret key", e);
        }
    }

    public String generateToken(String username) {  //to generate token
        Map<String, Object> claims = new HashMap<>();   //your payload in jwt will have some data about the user,ie the claims
        // a claim will have username,expiry date ,time of issue etc .. we are creating map for such claims

        return  Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*10))  //5 min of token expiration in milliseconds
                .signWith(getKey(), SignatureAlgorithm.HS256).compact();  //signed with signature algo and key,also compact for giving the token
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey); //since hmacShaKey accepts byte,so we need to convert sec key to byte
        return Keys.hmacShaKeyFor(keyBytes);     //use this method to generate the key but we need to pass a secret message as a parameter to generate key
    }

    public String extractUserName(String token) {
        // extract the username from jwt token
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build().parseClaimsJws(token).getBody();
    }


    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);

        //here checking the username which we are passing from the token and in the UserDetails we are getting data from database and compare both
        //if both are matching then true ,also need to verify if the token is expired or not ?
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
