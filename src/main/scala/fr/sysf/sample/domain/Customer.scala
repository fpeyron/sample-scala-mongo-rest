package fr.sysf.sample.domain

import java.time.{Instant, LocalDate}
import javax.validation.constraints.{NotNull, Size}

import com.fasterxml.jackson.annotation.JsonIgnore
import org.bson.types.ObjectId
import org.hibernate.validator.constraints.{Email, NotEmpty}
import org.springframework.data.annotation.{CreatedDate, Id, LastModifiedDate, Version}
import org.springframework.data.mongodb.core.index.Indexed

import scala.beans.BeanProperty


class Customer {

  @Id
  @JsonIgnore
  @BeanProperty var id: ObjectId = _

  @Indexed
  @BeanProperty var customerId: String = _

  @NotNull
  @Size(min = 2, max = 5)
  @BeanProperty var countryCode: String = _

  @BeanProperty var languageCode: String = _

  @Indexed
  @Email
  @NotEmpty
  @BeanProperty var email: String = _

  @BeanProperty var firstName: String = _

  @BeanProperty var lastName: String = _

  @BeanProperty var gender: String = _

  @BeanProperty var birthDate: LocalDate = _

  @BeanProperty var address1: String = _

  @BeanProperty var address2: String = _

  @BeanProperty var address3: String = _

  @BeanProperty var address4: String = _

  @BeanProperty var zipCode: String = _

  @BeanProperty var city: String = _

  @BeanProperty var state: String = _

  @BeanProperty var landlinePhone: String = _

  @BeanProperty var landlinePhonePrefix: String = _

  @BeanProperty var mobilePhone: String = _

  @BeanProperty var mobilePhonePrefix: String = _

  @BeanProperty var latlong: String = _

  @BeanProperty var status: String = _


  @CreatedDate
  @BeanProperty var createdDate: Instant = _

  @LastModifiedDate
  @BeanProperty var updatedDate: Instant = _

  @JsonIgnore
  @Version
  @BeanProperty var version: Long = _

}