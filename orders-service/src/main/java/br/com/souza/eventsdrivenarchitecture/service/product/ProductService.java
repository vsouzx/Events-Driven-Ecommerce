package br.com.souza.eventsdrivenarchitecture.service.product;

import br.com.souza.eventsdrivenarchitecture.database.model.Product;
import br.com.souza.eventsdrivenarchitecture.database.repository.IProductRepository;
import br.com.souza.eventsdrivenarchitecture.dto.ProductRequest;
import br.com.souza.eventsdrivenarchitecture.exceptions.ProductNameAlreadyExistsException;
import br.com.souza.eventsdrivenarchitecture.exceptions.ProductNotFoundException;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final IProductRepository iProductRepository;

    public ProductService(IProductRepository iProductRepository) {
        this.iProductRepository = iProductRepository;
    }

    public void saveNewProduct(ProductRequest request) throws Exception {
        if (iProductRepository.findByName(request.getName()).isPresent()) {
            throw new ProductNameAlreadyExistsException();
        }

        iProductRepository.save(new Product(request));
    }

    public void updateProduct(Product product) throws Exception {
        getProductById(product.getId());
        iProductRepository.save(product);
    }

    public Product getProductById(UUID id) throws Exception{
        return iProductRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
    }

    public List<Product> getAllProducts() {
        return iProductRepository.findAll();
    }

    public void deleteProduct(UUID id) throws Exception{
        iProductRepository.delete(getProductById(id));
    }

}
