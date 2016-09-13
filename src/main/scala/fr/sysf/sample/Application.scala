package fr.sysf.sample

import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper, SerializationFeature}
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.google.common.base.{Predicate, Predicates}
import com.mongodb.MongoClientOptions
import com.mongodb.MongoClientOptions.Builder
import fr.sysf.sample.config.MongoOptionProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
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

  private def paths(): Predicate[String] = {
    Predicates.or(
      regex("/customers.*")
    )
  }

  override def getValidator: Validator = {
    validator()
  }


  @Bean
  def validator(): LocalValidatorFactoryBean = {
    new LocalValidatorFactoryBean()
  }

}

@Configuration
@EnableConfigurationProperties(Array(classOf[MongoOptionProperties]))
class MongoDbConfig {

  @Autowired
  private val mongoOptionProperties: MongoOptionProperties = null

  @Bean
  def mongoClientOptions(): MongoClientOptions = {
    val builder: Builder = MongoClientOptions.builder()

    if (mongoOptionProperties.connectionsPerHost > 0) {
      builder.connectionsPerHost(mongoOptionProperties.connectionsPerHost)
    }
    if (mongoOptionProperties.threadsAllowedToBlockForConnectionMultiplier > 0) {
      builder.threadsAllowedToBlockForConnectionMultiplier(mongoOptionProperties.threadsAllowedToBlockForConnectionMultiplier)
    }
    if (mongoOptionProperties.connectTimeout > 0) {
      builder.connectTimeout(mongoOptionProperties.connectTimeout)
    }
    if (mongoOptionProperties.socketTimeout > 0) {
      builder.socketTimeout(mongoOptionProperties.socketTimeout)
    }

    builder.build()
  }
}