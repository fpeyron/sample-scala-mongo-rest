package fr.sysf.sample

import fr.sysf.sample.domain.Hotel
import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.hateoas
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

/**
  * Created by florent on 21/02/2016.
  */
@RunWith(classOf[SpringJUnit4ClassRunner])
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = Array(classOf[ApplicationConfig]))
class ApiRestTest {

  @Autowired
  private val restTemplate: TestRestTemplate = null

  @Test
  def getHello {

    // getAll before insert
    val response = restTemplate.getForEntity("/hotels", classOf[hateoas.Resources[Hotel]])
    Assertions.assertThat(response.getStatusCodeValue).isEqualTo(HttpStatus.OK.value())
    Assertions.assertThat(response.getBody.getContent).isEmpty

    // Insert hotel
    val hotel = new Hotel()
    hotel.setName("myName")
    hotel.setZip("75010")
    hotel.setAddress("my address")
    val response1 = restTemplate.postForEntity("/hotels", hotel, classOf[Hotel])
    Assertions.assertThat(response1.getStatusCodeValue).isEqualTo(HttpStatus.CREATED.value())
    Assertions.assertThat(response.getBody.getContent).isEmpty


    // getAll after insert
    val response2 = restTemplate.getForEntity("/hotels", classOf[hateoas.Resources[Hotel]])
    Assertions.assertThat(response2.getStatusCodeValue).isEqualTo(HttpStatus.OK.value())
    Assertions.assertThat(response2.getBody.getContent).isNotEmpty
    Assertions.assertThat(response2.getBody.getContent.size).isEqualTo(1)
  }
}
