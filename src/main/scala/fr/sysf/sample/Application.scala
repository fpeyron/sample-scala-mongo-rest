package fr.sysf.sample

import com.google.common.base.{Predicate, Predicates}
import fr.sysf.sample.domain.Customer
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.{RepositoryRestConfigurer, RepositoryRestConfigurerAdapter}
import org.springframework.validation.Validator
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors.regex
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

object Application extends App {
  SpringApplication.run(classOf[ApplicationConfig])
}

@EnableSwagger2
@EnableAutoConfiguration
@ComponentScan
@EnableMongoAuditing
class ApplicationConfig

@Configuration
class RestMvcConfig extends WebMvcConfigurerAdapter {

  @Bean
  def repositoryRestConfigurer(): RepositoryRestConfigurer = new RepositoryRestConfigurerAdapter() {
    override def configureRepositoryRestConfiguration(config: RepositoryRestConfiguration) {
      config.exposeIdsFor(classOf[Customer])
    }
  }

  @Bean
  def validator(): LocalValidatorFactoryBean = {
    new LocalValidatorFactoryBean()
  }

  @Bean
  def newsApi(): Docket = {
    new Docket(DocumentationType.SWAGGER_2)
        .useDefaultResponseMessages(false)
        .groupName("customers")
        .apiInfo(apiInfo())
        .select()
        .paths(paths())
        .build()
  }

  private def apiInfo() = {
    new ApiInfoBuilder()
        .title("Fustion Audit Swagger")
        .description("Spring REST Webservice Fustion Audit Swagger")
        .contact("BETC Fusion Team")
        .license("Apache License Version 2.0")
        .version("2.0")
        .build()
  }

  private def paths():Predicate[String] = {
    Predicates.or(
      regex("/customers.*")
    )
  }

  override def getValidator: Validator = {
    validator()
  }
}