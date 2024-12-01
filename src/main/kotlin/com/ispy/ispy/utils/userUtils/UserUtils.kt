package com.ispy.ispy.utils.userUtils

import com.ispy.ispy.dto.UserDTO
import com.ispy.spy.jooq.enums.UserRole
import com.ispy.ispy.dto.token.UserTokenDTO
import java.util.*

data class CustomUserDetails(
    val userDetails: UserDTO
) {
    fun getUserId(): UUID {
        return userDetails.userId
    }

    fun getUserRole(): UserRole? {
        return userDetails.role
    }

    fun getEmail(): String? {
        return userDetails.email
    }


}
