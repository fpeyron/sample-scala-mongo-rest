package fr.sysf.sample.service


import java.net.HttpURLConnection
import javax.validation.Valid

import fr.sysf.sample.domain.Customer
import io.swagger.annotations.{Api, ApiOperation, ApiResponse, ApiResponses}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpStatus, MediaType, ResponseEntity}
import org.springframework.web.bind.annotation._

/**
  * @author florent 
  *         12/09/2016
  */
@Api(value = "Customers service", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
@RestController
@RequestMapping(Array("/customers"))
class CustomerController {

  @Autowired
  private val customerRepository: CustomerRepository = null

  @ApiOperation(value = "Put_Customers", notes = "method to create new Customer or update customer")
  @ApiResponses(value = Array(
    new ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Bad Request"),
    new ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not found"),
    new ApiResponse(code = HttpURLConnection.HTTP_ACCEPTED, response = classOf[Customer], message = "Success PUT")
  ))
  @RequestMapping(
    value = Array("/"),
    method = Array(RequestMethod.PUT, RequestMethod.POST, RequestMethod.PATCH),
    produces = Array(MediaType.APPLICATION_JSON_UTF8_VALUE))
  def setCustomer(
                   @Valid @RequestBody customer: Customer
                 ): ResponseEntity[Customer] = {

    val customerExisted = customerRepository.findByEmail(customer.email)
    if (customerExisted != null) {
      customer.id = customerExisted.id
      customer.version = customerExisted.version
    }
    val customerSaved = customerRepository.save(customer)

    new ResponseEntity[Customer](customerSaved, HttpStatus.ACCEPTED)
  }

}