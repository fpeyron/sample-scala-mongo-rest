package fr.sysf.sample.service

import fr.sysf.sample.domain.Hotel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(collectionResourceRel = "hotel", path = "hotels")
trait HotelRepository extends MongoRepository[Hotel, String]