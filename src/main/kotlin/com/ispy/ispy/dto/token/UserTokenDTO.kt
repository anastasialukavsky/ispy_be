package com.ispy.ispy.dto.token

import com.ispy.spy.jooq.enums.UserRole
import java.util.UUID

class UserTokenDTO (
    val userId: UUID,
    val email: String,
    val passwordHash: String?,
    val role: UserRole,

)