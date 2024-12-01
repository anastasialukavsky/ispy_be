package com.ispy.ispy.utils.jwtUtils


import com.ispy.ispy.dto.UserDTO
import com.ispy.ispy.utils.userUtils.CustomUserDetails
//import com.ispy.ispy.utils.userUtils.UserUtils
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtils {
    companion object {
        private const val EXPIRATION_TIME = 86400000 // 1 day
        private val secret_key = "secret_key"
    }

    fun generateToken(userDetails: CustomUserDetails): String {
        val claims = mapOf("userId" to userDetails.getUserId(), "email" to userDetails.getEmail())
        val key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256)

        val token = Jwts.builder()
            .setClaims(claims)
            .setSubject(userDetails.userDetails.email)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
            .signWith(key)
            .compact()

        println("Generated JWT token: $token")
        return token
    }

    fun createToken(claims: Map<String, Any>, subject: String): String {
        val currentDate = Date(System.currentTimeMillis())
        val key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256)

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(currentDate)
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
            .signWith(key)
            .compact()
    }


    fun extractAllClaims(token: String): Claims {
        return Jwts.parserBuilder().setSigningKey(secret_key.toByteArray()).build().parseClaimsJws(token).body
    }

    fun <T> extractClaim(token: String, claimResolver: (Claims) -> T): T {
        val claims = extractAllClaims(token)
        return claimResolver.invoke(claims)
    }


    fun extractUserId(token: String): String {
        val userId = extractClaim(token) { claims ->
            claims["userId"] as String
        }

        return userId
    }

    fun extractUserEmail(token: String): String {
        val email = extractClaim(token) { claims ->
            claims["email"] as String
        }

        return email
    }

    fun validateToken(token: String, userDetails: CustomUserDetails): Boolean {
        val email = extractUserEmail(token)
        return email == userDetails.userDetails.email && !isTokenExpired(token)
    }

    fun extractClaimsAndExpiration(token: String): Pair<Claims, Date> {
        val jwtParser = Jwts.parserBuilder().setSigningKey(secret_key.toByteArray()).build()
        val jwt = jwtParser.parseClaimsJws(token)
        val claims = jwt.body
        val expiration = claims.expiration
        return Pair(claims, expiration)
    }

    fun isTokenExpired(token: String): Boolean {
        val (claims, expiration) = extractClaimsAndExpiration(token)
        return expiration != null && expiration.before(Date())
    }







}
