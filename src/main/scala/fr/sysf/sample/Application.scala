package fr.sysf.sample

import com.mongodb.MongoClientOptions
import com.mongodb.MongoClientOptions.Builder
import fr.sysf.sample.config.MongoOptionProperties
import fr.sysf.sample.domain.Hotel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.{RepositoryRestConfigurer, RepositoryRestConfigurerAdapter}

object Application extends App {
  SpringApplication.run(classOf[ApplicationConfig])
}

@EnableAutoConfiguration
@ComponentScan
@EnableEurekaClient
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