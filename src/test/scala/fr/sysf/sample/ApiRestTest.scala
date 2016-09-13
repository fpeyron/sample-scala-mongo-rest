package fr.sysf.sample

import org.junit.runner.RunWith
import org.junit.{Ignore, Test}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

/**
  * Created by florent on 21/02/2016.
  */
@RunWith(classOf[SpringJUnit4ClassRunner])
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = Array(classOf[ApplicationConfig]))
class ApiRestTest {

  @Autowired
  private val restTemplate: TestRestTemplate = null


  // todo: to complete
  @Ignore
  @Test
  def should_put_customer_when_it_does_not_exits {

  }
}
