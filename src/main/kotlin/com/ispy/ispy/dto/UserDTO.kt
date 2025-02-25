package com.ispy.ispy.dto

import com.ispy.ispy.jooq.enums.UserRole
import java.time.OffsetDateTime
import java.util.UUID

data class UserDTO(
    val userId: UUID,
    val email: String?,
    val passwordHash: String?,
    val oAuthProvider: String?,
    val oAuthId: String?,
    val role: UserRole?,
    val createdAt: OffsetDateTime?,
    val updatedAt: OffsetDateTime?
)

enum class UserRole {
    ADMIN,
    USER
}