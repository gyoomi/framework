/**
 * Copyright © 2018, TaoDing
 * <p>
 * All Rights Reserved.
 */

package com.cherry.framework.platform.config;

import com.cherry.framework.platform.constant.JWTConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/7/17 14:51
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Autowired
    JWTConstant jwtConstant;

    @Bean
    public Docket createRestApi() {
        ParameterBuilder pb = new ParameterBuilder();
        List<Parameter> token = new ArrayList<>();
        pb.name(jwtConstant.JWT_HEADERS).description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        token.add(pb.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder().title("FrameWork 开发平台接口文档").description("Based On Spring Boot").termsOfServiceUrl("").version("1.0").build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cherry"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(token);
    }
}
