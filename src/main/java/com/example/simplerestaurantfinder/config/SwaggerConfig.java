package com.example.simplerestaurantfinder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by BERM-PC on 23/12/2559.
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig  {

    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/Api/.*"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Simple Restaurant Finder System APIs ")
                .description(" Restful web service to manage restaurant entries for the simple restaurant finder system.")
                .termsOfServiceUrl("http://www-03.ibm.com/software/sla/sladb.nsf/sla/bm?Open")
                .contact("Songkran Sommit")
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/bsongkran/SimpleRestaurantFinder")
                .version("2.0")
                .build();
    }


}
