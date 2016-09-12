package fr.sysf.sample

import java.time.LocalDate

import fr.sysf.sample.domain.Customer
import fr.sysf.sample.service.CustomerRepository
import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

/**
  * Created by florent on 21/02/2016.
  */
@RunWith(classOf[SpringJUnit4ClassRunner])
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = Array(classOf[ApplicationConfig]))
//@ContextConfiguration(classes = Array(classOf[ApplicationConfig]))
class CustomerDataTest {

  @Autowired
  private val customerRepository: CustomerRepository = null

  @Test
  def getHello {

    val customer = new Customer()
    customer.firstName = "Anna"
    customer.lastName = "Blum"
    customer.birthDate = LocalDate.of(1965, 2, 7)

    var request = customerRepository.save(customer)

    // getAll before insert
    Assertions.assertThat(request).isNotNull
    Assertions.assertThat(request.createdDate).isNotNull
    Assertions.assertThat(request.updatedDate).isEqualTo(request.createdDate)
    Assertions.assertThat(request.version).isEqualTo(0l)

    request.city = "Paris"
    request = customerRepository.save(request)

    Assertions.assertThat(request).isNotNull
    Assertions.assertThat(request.createdDate).isNotNull
    Assertions.assertThat(request.updatedDate).isNotEqualTo(request.createdDate)
    Assertions.assertThat(request.city).isNotNull
    Assertions.assertThat(request.version).isEqualTo(1l)


  }
}
