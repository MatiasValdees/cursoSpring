package cl.bytnet.best_travel.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources(
        {
                @PropertySource(value = "classpath:api-application.properties"),
                @PropertySource(value = "classpath:redis.properties"),
                @PropertySource(value = "classpath:client_security.properties")
        })
public class PropertiesConfig {
}
