package com.ispy.ispy.dto.auth

import com.ispy.ispy.dto.UserDTO

data class AuthSignUpPayloadDTO(
    val user: UserDTO,
    val accessToken: String,
)
