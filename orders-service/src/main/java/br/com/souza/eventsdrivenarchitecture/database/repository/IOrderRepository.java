package br.com.souza.eventsdrivenarchitecture.database.repository;

import br.com.souza.eventsdrivenarchitecture.database.model.Order;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends MongoRepository<Order, UUID> {
}
