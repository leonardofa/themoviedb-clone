package br.com.leonardo.swagger;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

  @Value("${aplicacao.nome}")
  private String nome;

  @Value("${aplicacao.descricao}")
  private String descricao;

  @Value("${aplicacao.versao}")
  private String versao;

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(RequestHandlerSelectors.basePackage("br.com.leonardo.api.rest")).paths(PathSelectors.any()).build()
        .apiInfo(apiInfo()).useDefaultResponseMessages(false);
  }

  private ApiInfo apiInfo() {
    return new ApiInfo(nome, descricao, versao,
        "Criada para fins educacionais e de demonstração",
        new Contact(
            "Leonardo Ferreira Alves",
            "https://www.linkedin.com/in/leonardo-ferreira-alves-9472aa38",
            "leonardofa@gmail.com"
        ),
        "MIT", "https://opensource.org/licenses/MIT", Collections.emptyList()
    );
  }

}
