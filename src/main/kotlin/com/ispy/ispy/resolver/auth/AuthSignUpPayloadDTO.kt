package com.ispy.ispy.resolver.auth

import com.ispy.ispy.dto.UserDTO
import org.jooq.User

data class AuthSignUpPayloadDTO(
    val user: UserDTO,
    val accessToken: String,
)
