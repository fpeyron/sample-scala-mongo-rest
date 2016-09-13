package fr.sysf.sample

import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper, SerializationFeature}
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import fr.sysf.sample.domain.Hotel
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
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
class RestMvcConfig extends WebMvcConfigurerAdapter {

  @Bean
  def repositoryRestConfigurer() = new RepositoryRestConfigurerAdapter() {
    override def configureRepositoryRestConfiguration(config: RepositoryRestConfiguration) {
      config.exposeIdsFor(classOf[Hotel])
    }
  }


  override def configureMessageConverters(converters: java.util.List[HttpMessageConverter[_]]) {
    val converter = new MappingJackson2HttpMessageConverter()
    converter.setObjectMapper(
      new ObjectMapper()
        .registerModule(new JavaTimeModule())
        .setSerializationInclusion(Include.NON_EMPTY)
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .findAndRegisterModules())
    converters.add(converter)
  }


  @Bean
  def validator() = new LocalValidatorFactoryBean()


  override def getValidator = validator()

}