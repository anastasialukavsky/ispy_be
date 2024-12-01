package com.ispy.ispy.resolver.auth
import com.ispy.ispy.dto.auth.AuthSignUpInputDTO
import com.ispy.ispy.dto.auth.AuthSignUpPayloadDTO
import com.ispy.ispy.service.auth.AuthService
import com.ispy.ispy.utils.jwtUtils.JwtUtils
import com.ispy.ispy.utils.userUtils.CustomUserDetails
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod


@Controller
class AuthResolver {
    @Autowired
    private lateinit var authService: AuthService

    @RequestMapping(
        path = ["/graphql"],
        method = [RequestMethod.OPTIONS]
    )

    @MutationMapping(name = "signUp")
    fun signUp(
        @Argument(name = "input") input: AuthSignUpInputDTO): AuthSignUpPayloadDTO? {
        try {
            val result = authService.signUp(input)
            println("Result: $result")
            return result ?: throw RuntimeException("Null result from sign-up")
        } catch (e: Exception) {
            println("Exception in authSignUp: ${e.message}")
            throw RuntimeException("Error during authSignUp: ${e.message}")
        }
    }

    @MutationMapping(name="signIn")
    fun signIn(
        @Argument(name="input") input: AuthSignUpInputDTO
    ): AuthSignUpPayloadDTO? {
        val result = authService.signIn(input)
        return result ?: throw RuntimeException("Null result from sign-in")
    }

}