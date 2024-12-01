//package com.ispy.ispy.config
//
//
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.web.SecurityFilterChain
//
//
//@Configuration
//@EnableWebSecurity
//class SecurityConfig {
//
//    @Bean
//    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
//        http
//            .csrf { csrf -> csrf.disable() }
//            .authorizeHttpRequests { auth ->
//                auth.requestMatchers(    "/graphiql/**",
//                    "/playground/**",
//                    "/graphql",
//                    "/vendor/graphiql/**",
//                    "/vendor/playground/**",
//                    "/actuator/**").permitAll()
//                    .anyRequest().authenticated()
//            }
//            .httpBasic { }
//
//        return http.build()
//
//    }
//
//}
//
////
////@Configuration
////class SecurityConfig {
////
////    @Bean
////    fun filterChain(http: HttpSecurity): SecurityFilterChain {
////        http
////            .csrf().disable()
////            .authorizeHttpRequests { auth ->
////                auth
////                    .antMatchers(
////                        "/graphiql",
////                        "/playground",
////                        "/graphql",
////                        "/vendor/graphiql/**",
////                        "/vendor/playground/**"
////                    ).permitAll()
////                    .anyRequest().authenticated()
////            }
////
////        return http.build()
////    }
////}
