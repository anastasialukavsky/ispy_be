package com.ispy.ispy.dto.auth

import com.ispy.spy.jooq.enums.UserRole

data class AuthSignUpInputDTO(
    val email: String?,
    val passwordHash: String?,
    val oAuthProvider: String?,
    val oAuthId: String?,
//    val role: UserRole?,
//    val createdAt: LocalDateTime = LocalDateTime.now(),
//    val updatedAt: LocalDateTime? = LocalDateTime.now()
    )
