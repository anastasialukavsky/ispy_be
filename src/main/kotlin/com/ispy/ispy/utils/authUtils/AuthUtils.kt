package com.ispy.ispy.utils.authUtils

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset

@Component
class AuthUtils {

    private val passwordEncoder = BCryptPasswordEncoder()

    fun verifyPassword(plainPassword: String, hashedPassword: String): Boolean {
        return passwordEncoder.matches(plainPassword, hashedPassword)
    }

     fun convertToOffsetDateTime(localDateTime: LocalDateTime?): OffsetDateTime? {
        return localDateTime?.atOffset(ZoneOffset.UTC)
    }
     fun validatePassword(rawPassword: String, hashedPassword: String?): Boolean {
        return hashedPassword != null && BCrypt.checkpw(rawPassword, hashedPassword)
    }

    fun verifyGoogleToken(idToken: String): Pair<String, String?>? {
        val transport = NetHttpTransport()
        val jsonFactory = JacksonFactory.getDefaultInstance()
        val verifier = GoogleIdTokenVerifier.Builder(transport, jsonFactory)
            .setAudience(listOf("778743708511-482k2i9mrc6oq5fgs5824p50f5jfod93.apps.googleusercontent.com"))
            .build()

        val googleIdToken = verifier.verify(idToken)
        if (googleIdToken == null) {
            println("Google ID token is invalid or could not be verified.")
            return null
        }

        val payload = googleIdToken.payload
        println("Successfully verified Google ID token for email: ${payload.email}")
        return Pair(payload.subject, payload.email)
    }
}