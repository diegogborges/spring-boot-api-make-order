package br.com.order.api.controller;

import br.com.order.api.exception.ResourceNotFoundException;
import br.com.order.api.model.Product;
import br.com.order.api.model.ProductType;
import br.com.order.api.repository.ProductRepository;
import br.com.order.api.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @GetMapping("/product")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(productRepository.findAll(), null, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> findProductById(@PathVariable Long id) {
        return new ResponseEntity<>(productRepository.findById(id), null, HttpStatus.OK);
    }

    @PostMapping("/product_type/{productTypeId}/product")
    public Product save(@PathVariable Long productTypeId,
                                  @RequestBody @Valid Product product) {

        return productTypeRepository.findById(productTypeId)
                .map(productType -> {
                    product.setProductType(productType);
                    return productRepository.save(product);
                }).orElseThrow(() -> new ResourceNotFoundException("ProductType not found with id " + productTypeId));
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> edit(@RequestBody @Valid Product product, @PathVariable long id) {

        Optional<Product> productOptional = productRepository.findById(id);

        if (!productOptional.isPresent())
            throw new ResourceNotFoundException("product not found for this id :: " + id);

        product.setIdProduct(id);

        return new ResponseEntity<>(productRepository.save(product), null, HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    public Map<String, Boolean> delete(@PathVariable Long id) throws ResourceNotFoundException {
        Product product= productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("product not found for this id :: " + id));

        productRepository.delete(product);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
