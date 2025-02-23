package com.ispy.ispy.service.user

import com.ispy.ispy.dto.UserDTO
import com.ispy.ispy.jooq.com.ispy.spy.jooq.tables.records.UsersRecord
import com.ispy.ispy.jooq.com.ispy.spy.jooq.tables.references.USERS
import org.jooq.DSLContext
import org.springframework.stereotype.Service
import java.time.ZoneOffset
import java.util.*

@Service
class UserService(private val dsl: DSLContext) {

    fun getUserById(userId: UUID): UserDTO? {
        val userRecord: UsersRecord? = dsl.selectFrom(USERS)
            .where(USERS.USER_ID.eq(userId))
            .fetchOneInto(UsersRecord::class.java)


        return userRecord?.let {
//            it.role?.name?.let { it1 -> UserRole.valueOf(it1) }?.let { it2 ->
                UserDTO(
                    userId = it.userId!!,
                    email = it.email!!,
                    passwordHash = it.passwordHash,
                    oAuthProvider = it.oauthProvider,
                    oAuthId = it.oauthId,
                    role = it.role!!,
                    createdAt = it.createdAt?.atOffset(ZoneOffset.UTC),
                    updatedAt = it.updatedAt?.atOffset(ZoneOffset.UTC)
                )
            }
        }

    }
