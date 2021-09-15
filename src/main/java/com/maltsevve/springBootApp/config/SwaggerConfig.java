package com.maltsevve.springBootApp.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Collections;

@Configuration
@EnableSwagger2
@Data
public class SwaggerConfig {
    @Value("${app.api.version}")
    private String version;
    @Value("${app.api.title}")
    private String title;
    @Value("${app.api.description}")
    private String description;
    @Value("${app.api.basePackage}")
    private String basePackage;
    @Value("${app.api.contactName}")
    private String contactName;
    @Value("${app.api.contactEmail}")
    private String contactEmail;

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(Collections.singletonList(authorizationHeader()))
                .directModelSubstitute(LocalDate.class, String.class)
                .directModelSubstitute(Timestamp.class, String.class)
                .apiInfo(apiInfo());
    }

    private RequestParameter authorizationHeader() {
        return new RequestParameterBuilder()
                .name("Authorization")
                .description("Authorization header")
                .in(ParameterType.HEADER)
                .required(false)
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version(version)
                .contact(new Contact(contactName, null, contactEmail))
                .build();
    }
}
