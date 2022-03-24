package codes.draeger

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@Testcontainers
@ContextConfiguration(initializers = [TestContainer.Initializer::class])
@Suppress("UtilityClassWithPublicConstructor")
abstract class TestContainer {

    companion object {
        @Container
        @JvmStatic
        internal val postgresContainer = CustomSQLContainer()
            .withExposedPorts(5432)
    }

    class Initializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
        override fun initialize(context: ConfigurableApplicationContext) {
            TestPropertyValues.of(
                "spring.datasource.url=jdbc:postgresql://${postgresContainer.containerIpAddress}:${postgresContainer.getMappedPort(5432)}/hotel-booking",
            ).applyTo(context.environment)
        }
    }
}

internal class CustomSQLContainer(
    version: String = "12.4-alpine",
) : PostgreSQLContainer<CustomSQLContainer>(DockerImageName.parse("postgres:$version")) {
    init {
        withDatabaseName("hotel-booking")
        withUsername("booking")
        withPassword("booking")
        withCommand("postgres -N 500")
    }
}