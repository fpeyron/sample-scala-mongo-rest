package fr.sysf.sample.service


import javax.validation.Valid

import fr.sysf.sample.domain.Customer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpStatus, MediaType, ResponseEntity}
import org.springframework.web.bind.annotation._

/**
  * @author florent 
  *         12/09/2016
  */
@RestController
@RequestMapping(Array("/customers"))
class CustomerController {

  @Autowired
  private val customerRepository: CustomerRepository = null


  @RequestMapping(value = Array("/"), method = Array(RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH), produces = Array(MediaType.APPLICATION_JSON_UTF8_VALUE))
  def setCustomer(
                   @Valid @RequestBody customer: Customer
                 ): ResponseEntity[Customer] = {

    new ResponseEntity[Customer](customer, HttpStatus.ACCEPTED)
  }

}