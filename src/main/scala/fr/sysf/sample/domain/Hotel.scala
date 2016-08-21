package fr.sysf.sample.domain

import java.util.Date

import org.hibernate.validator.constraints.NotEmpty
import org.springframework.data.annotation.{CreatedDate, Id, LastModifiedDate}

import scala.beans.BeanProperty

class Hotel {

  @Id
  @BeanProperty
  var id: String = _

  @BeanProperty
  @CreatedDate
  var creatingDate: Date = _

  @BeanProperty
  @LastModifiedDate
  var updatingDate: Date = _

  @BeanProperty
  @NotEmpty
  var name: String = _

  @BeanProperty
  @NotEmpty
  var address: String = _

  @BeanProperty
  @NotEmpty
  var zip: String = _

}