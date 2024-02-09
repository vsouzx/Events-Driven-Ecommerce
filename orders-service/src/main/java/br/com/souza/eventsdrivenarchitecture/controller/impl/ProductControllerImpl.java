package br.com.souza.eventsdrivenarchitecture.controller.impl;

import br.com.souza.eventsdrivenarchitecture.database.model.Product;
import br.com.souza.eventsdrivenarchitecture.dto.ProductRequest;
import br.com.souza.eventsdrivenarchitecture.service.product.ProductService;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/product")
public class ProductControllerImpl {

    private final ProductService productService;

    public ProductControllerImpl(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createNewProduct(@RequestBody ProductRequest request) throws Exception {
        productService.saveNewProduct(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateProduct(@RequestBody Product product) throws Exception {
        productService.updateProduct(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProductById(@PathVariable("id") UUID id) throws Exception {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") UUID id) throws Exception {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
