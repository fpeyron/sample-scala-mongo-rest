package fr.sysf.sample

import fr.sysf.sample.domain.Hotel
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.{RepositoryRestConfigurer, RepositoryRestConfigurerAdapter}
import org.springframework.validation.Validator
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

object Application extends App {
  SpringApplication.run(classOf[ApplicationConfig])
}

@EnableAutoConfiguration
@ComponentScan
@EnableMongoAuditing
class ApplicationConfig


@Configuration
class RestMvcConfig extends WebMvcConfigurerAdapter{

  @Bean
  def repositoryRestConfigurer(): RepositoryRestConfigurer = new RepositoryRestConfigurerAdapter() {
    override def configureRepositoryRestConfiguration(config: RepositoryRestConfiguration) {
      config.exposeIdsFor(classOf[Hotel])
    }
  }

  @Bean
  def validator(): LocalValidatorFactoryBean = {
    new LocalValidatorFactoryBean()
  }

  override def getValidator: Validator = {
    validator()
  }
}