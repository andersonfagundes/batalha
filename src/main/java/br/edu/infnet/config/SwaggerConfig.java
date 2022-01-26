package br.edu.infnet.config;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

    String appName;

    @Bean
    public OpenAPI OpenAPI() {

        appName = StringUtils.capitalize("BATALHA-API");
        Server server = new Server();
        server.setUrl("http://" + StringUtils.upperCase("BATALHA") + "/");
        return new OpenAPI().servers(List.of(server))
                .info(new Info()
                        .title(StringUtils.capitalize("BATALHA"))
                        .description("Swagger - BATALHA")
                        .version("v1.0.0")
                );
    }

}
