package fr.sysf.sample.service


import fr.sysf.sample.domain.Customer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpStatus, MediaType, ResponseEntity}
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation._

/**
  * @author florent 
  *         12/09/2016
  */
@RestController("Customers/")
class CustomerController {

  @Autowired
  private val customerRepository: CustomerRepository = null


  @RequestMapping(path = Array("/"), method = Array(RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH), produces = Array(MediaType.APPLICATION_JSON_UTF8_VALUE))
  def setCustomer(
                   @Validated @RequestBody customer: Customer
                 ): ResponseEntity[Customer] = {

    new ResponseEntity[Customer](customer, HttpStatus.ACCEPTED)
  }

}