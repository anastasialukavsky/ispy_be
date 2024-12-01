package com.ispy.ispy.service.auth

import com.ispy.ispy.dto.UserDTO
import com.ispy.ispy.dto.auth.AuthSignUpInputDTO
import com.ispy.ispy.dto.auth.AuthSignUpPayloadDTO
import com.ispy.ispy.service.user.UserService
import com.ispy.ispy.utils.authUtils.AuthUtils
import com.ispy.ispy.utils.jwtUtils.JwtUtils
import com.ispy.ispy.utils.userUtils.CustomUserDetails
import com.ispy.spy.jooq.tables.references.USERS
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
//import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.*

@Service
class AuthService {
    @Autowired
    lateinit var dsl: DSLContext

    @Autowired
    lateinit var jwtUtils: JwtUtils

    @Autowired
    lateinit var authUtils: AuthUtils
    private val passwordEncoder = BCryptPasswordEncoder()

    fun signUp(input: AuthSignUpInputDTO): AuthSignUpPayloadDTO? {
        var email: String? = input.email
        var oAuthId: String? = input.oAuthId

        if (input.oAuthProvider == "google" && input.oAuthId != null) {
            val verifiedResult = authUtils.verifyGoogleToken(input.oAuthId)
            if (verifiedResult == null) {
                throw IllegalArgumentException("Invalid Google ID token")
            }

            oAuthId = verifiedResult.first
            email = verifiedResult.second
        }

        if (email == null) {
            throw IllegalArgumentException("Email must not be null for sign-up.")
        }

        println("Checking if user exists with email: $email")
        val existingUser = dsl.selectFrom(USERS)
            .where(USERS.EMAIL.eq(email))
            .fetchOne()

        if (existingUser != null) {
            println("User already exists!")
            throw IllegalArgumentException("User with email $email already exists.")
        }

        val hashedPassword = if (input.passwordHash != null) {
            passwordEncoder.encode(input.passwordHash).also {
                println("Hashed password: $it")
            }
        } else {
            // For OAuth users, passwordHash is null
            null
        }

        println("Creating new user")
        val userRecord = dsl.insertInto(USERS)
            .set(USERS.USER_ID, UUID.randomUUID())
            .set(USERS.EMAIL, email)
            .set(USERS.PASSWORD_HASH, hashedPassword)
            .set(USERS.OAUTH_PROVIDER, input.oAuthProvider)
            .set(USERS.OAUTH_ID, oAuthId)
            .set(USERS.CREATED_AT, LocalDateTime.now())
            .set(USERS.UPDATED_AT, LocalDateTime.now())
            .returning()
            .fetchOne() ?: throw Exception("Failed to create user.")

        val user = UserDTO(
            userId = userRecord.userId!!,
            email = userRecord.email!!,
            passwordHash = userRecord.passwordHash,
            oAuthProvider = userRecord.oauthProvider,
            oAuthId = userRecord.oauthId,
            role = userRecord.role!!,
            createdAt = authUtils.convertToOffsetDateTime(userRecord.createdAt),
            updatedAt = authUtils.convertToOffsetDateTime(userRecord.updatedAt)
        )

        println("User created: $user")

        val token = jwtUtils.generateToken(CustomUserDetails(user))
        println("Generated token: $token")

        return AuthSignUpPayloadDTO(user = user, accessToken = token)
    }


fun signIn(input: AuthSignUpInputDTO): AuthSignUpPayloadDTO? {
    val userRecord = when {
        input.oAuthProvider == "google" && input.oAuthId != null -> {
            val verifiedResult = authUtils.verifyGoogleToken(input.oAuthId)
            if (verifiedResult == null) {
                throw IllegalArgumentException("Invalid Google ID token")
            }

            val (extractedOAuthId, email) = verifiedResult

            // OAuth login: Find user by provider and OAuth ID (sub)
            dsl.selectFrom(USERS)
                .where(USERS.OAUTH_PROVIDER.eq(input.oAuthProvider))
                .and(USERS.OAUTH_ID.eq(extractedOAuthId))
                .fetchOne()
        }

        input.email != null && input.passwordHash != null -> {
            val user = dsl.selectFrom(USERS)
                .where(USERS.EMAIL.eq(input.email))
                .fetchOne()

            if (user == null || !user.passwordHash?.let { authUtils.verifyPassword(input.passwordHash, it) }!!) {
                throw IllegalArgumentException("Invalid credentials")
            }
            user
        }
        else -> throw IllegalArgumentException("Invalid sign-in input")
    }

    val user = userRecord?.let {
        UserDTO(
            userId = it.userId!!,
            email = it.email!!,
            passwordHash = it.passwordHash,
            oAuthProvider = it.oauthProvider,
            oAuthId = it.oauthId,
            role = it.role,
            createdAt = authUtils.convertToOffsetDateTime(userRecord.createdAt),
            updatedAt = authUtils.convertToOffsetDateTime(userRecord.updatedAt)
        )
    } ?: throw IllegalArgumentException("User not found")

    val token = jwtUtils.generateToken(CustomUserDetails(user))

    return AuthSignUpPayloadDTO(user = user, accessToken = token)
}
}