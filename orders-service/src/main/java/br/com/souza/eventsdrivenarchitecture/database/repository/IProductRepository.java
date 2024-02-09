package br.com.souza.eventsdrivenarchitecture.database.repository;

import br.com.souza.eventsdrivenarchitecture.database.model.Product;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends MongoRepository<Product, UUID> {

    Optional<Product> findByName(String name);
}
