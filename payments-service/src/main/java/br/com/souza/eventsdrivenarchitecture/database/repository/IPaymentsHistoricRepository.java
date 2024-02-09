package br.com.souza.eventsdrivenarchitecture.database.repository;

import br.com.souza.eventsdrivenarchitecture.database.model.PaymentHistoric;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaymentsHistoricRepository extends MongoRepository<PaymentHistoric, UUID> {
}
