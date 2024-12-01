//import org.apache.catalina.filters.CorsFilter
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.web.cors.CorsConfiguration
//import org.springframework.web.cors.CorsConfigurationSource
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource
//import org.springframework.web.servlet.config.annotation.CorsRegistry
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
//
//@Configuration
//class WebConfig : WebMvcConfigurer {
//    override fun addCorsMappings(registry: CorsRegistry) {
//        registry.addMapping("/graphql")
//            .allowedOrigins("http://localhost:5174")  // Your React app's URL
//            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//            .allowedHeaders("*")
//            .allowCredentials(true)
//    }
//}