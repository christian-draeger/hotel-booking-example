package codes.draeger.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import org.springframework.boot.info.BuildProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig() {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .components(Components())
            .info(
                Info()
                    .title("Hotel API")
                    .version("")
                    .description("Documentation for Hotel API")
                    .contact(
                        Contact()
                            .email("info@draeger.codes")
                            .name("Christian Dräger")
                            .url("https://github.com/christian-draeger")
                    )
            )
    }
}
