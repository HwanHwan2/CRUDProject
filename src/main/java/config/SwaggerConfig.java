package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * apis(RequestHandlerSelectors.any()) : 현재 RequestMapping으로 할당된 모든 URL 리스트 추출
 * paths(PathSelectors.any()) : 지정된 url 필터링하기. ex)any("/user/*") -> user만
 * 
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket swaggerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.ant("/*"))
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("API 타이틀")
				.description("API 상세소개 및 사용법 등")
				.version("1.0")
				.build();
	}
	
	
}
