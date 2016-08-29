package fr.sysf.sample

import fr.sysf.sample.domain.Hotel
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.{RepositoryRestConfigurer, RepositoryRestConfigurerAdapter}

object Application extends App {
  SpringApplication.run(classOf[ApplicationConfig])
}

@EnableAutoConfiguration
@ComponentScan
@EnableMongoAuditing
class ApplicationConfig


@Configuration
class RestMvcConfig {

  @Bean
  def repositoryRestConfigurer(): RepositoryRestConfigurer = new RepositoryRestConfigurerAdapter() {
    override def configureRepositoryRestConfiguration(config: RepositoryRestConfiguration) {
      config.exposeIdsFor(classOf[Hotel])
    }
  }

}