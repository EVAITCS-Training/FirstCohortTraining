package com.horrorcore.bankapp.services;

import com.horrorcore.bankapp.config.JwtConfigProperty;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Autowired
    private JwtConfigProperty jwtConfigProperty;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Generates a JWT token for the given username.
     * The token includes claims such as the username and roles of the user.
     *
     * @param username the username for which the token is generated
     * @return the generated JWT token as a String
     */
    public String generateToken(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", userDetails.getUsername());
        claims.put("role", userDetails.getAuthorities().toString());
        return generateToken(claims, userDetails);
    }

    /**
     * Extracts the username (subject) from the given JWT token.
     *
     * @param token the JWT token
     * @return the username extracted from the token
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts the expiration date from the given JWT token.
     *
     * @param token the JWT token
     * @return the expiration date of the token
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Validates the given JWT token by checking its username and expiration.
     *
     * @param token the JWT token to validate
     * @param userDetails the user details to compare against
     * @return true if the token is valid, false otherwise
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Checks if the given JWT token is expired.
     *
     * @param token the JWT token
     * @return true if the token is expired, false otherwise
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extracts a specific claim from the given JWT token using a claims resolver function.
     *
     * @param token the JWT token
     * @param claimsResolver a function to resolve the desired claim
     * @param <T> the type of the claim
     * @return the resolved claim
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extracts all claims from the given JWT token.
     *
     * @param token the JWT token
     * @return the claims extracted from the token
     */
    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Generates a JWT token with the given claims and user details.
     *
     * @param claims a map of claims to include in the token
     * @param userDetails the user details to include in the token
     * @return the generated JWT token as a String
     */
    private String generateToken(Map<String, Object> claims, UserDetails userDetails) {
        return Jwts
                .builder()
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtConfigProperty.getExpiration()))
                .signWith(getSecretKey())
                .compact();
    }

    /**
     * Retrieves the secret key used for signing and verifying JWT tokens.
     *
     * @return the secret key as a SecretKey object
     */
    private SecretKey getSecretKey() {
        byte[] encodedKey = Decoders.BASE64.decode(jwtConfigProperty.getSecret());
        return Keys.hmacShaKeyFor(encodedKey);
    }
}
