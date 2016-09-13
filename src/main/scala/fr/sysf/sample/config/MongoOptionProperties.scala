package fr.sysf.sample.config

import org.springframework.boot.context.properties.ConfigurationProperties

import scala.beans.BeanProperty


@ConfigurationProperties(locations = Array("classpath:application-${spring.profiles.active}.properties"), ignoreUnknownFields = false, prefix = "mongo.options")
class MongoOptionProperties {

  @BeanProperty
  var connectionsPerHost: Int = _

  @BeanProperty
  var threadsAllowedToBlockForConnectionMultiplier: Int = _

  @BeanProperty
  var connectTimeout: Int = _

  @BeanProperty
  var socketTimeout: Int = _

}