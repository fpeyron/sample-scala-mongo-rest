package fr.sysf.sample.service

import fr.sysf.sample.domain.Customer
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
trait CustomerRepository extends MongoRepository[Customer, ObjectId]