package br.com.order.api.controller;

import br.com.order.api.exception.ResourceNotFoundException;
import br.com.order.api.model.ProductType;
import br.com.order.api.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductTypeController {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @GetMapping("/product_type")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(productTypeRepository.findAll(), null, HttpStatus.OK);
    }

    @GetMapping("/product_type/{id}")
    public ResponseEntity<?> findProductTypeById(@PathVariable Long id) {
        return new ResponseEntity<>(productTypeRepository.findById(id), null, HttpStatus.OK);
    }

    @PostMapping("/product_type")
    public ResponseEntity<?> save(@RequestBody @Valid ProductType productType, BindingResult result) {

        if (result.hasErrors())
            return ResponseEntity.badRequest().body("Erro");

        return new ResponseEntity<>(productTypeRepository.save(productType), null, HttpStatus.OK);
    }

    @PutMapping("/product_type/{id}")
    public ResponseEntity<?> edit(@RequestBody @Valid ProductType productType, @PathVariable long id) {

        Optional<ProductType> productTypeOptional = productTypeRepository.findById(id);

        if (!productTypeOptional.isPresent())
            throw new ResourceNotFoundException("productType not found for this id :: " + id);

        productType.setIdProductType(id);

        return new ResponseEntity<>(productTypeRepository.save(productType), null, HttpStatus.OK);
    }

    @DeleteMapping("/product_type/{id}")
    public Map<String, Boolean> delete(@PathVariable Long id) throws ResourceNotFoundException {
        ProductType productType = productTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("productType not found for this id :: " + id));

        productTypeRepository.delete(productType);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
