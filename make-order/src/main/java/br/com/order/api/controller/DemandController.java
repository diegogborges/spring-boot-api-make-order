package br.com.order.api.controller;

import br.com.order.api.dto.DemandProductDto;
import br.com.order.api.exception.ResourceNotFoundException;
import br.com.order.api.model.Client;
import br.com.order.api.model.Demand;
import br.com.order.api.model.DemandProduct;
import br.com.order.api.model.ProductType;
import br.com.order.api.repository.*;
import br.com.order.api.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class DemandController {

    @Autowired
    private DemandRepository demandRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DemandProductRepository demandProductRepository;

    @Autowired
    private ClientRepository clientRepository;

    ProductService productService;
    
    @GetMapping("/demand")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(demandRepository.findAll(), null, HttpStatus.OK);
    }

    @GetMapping("/demand/{id}")
    public ResponseEntity<?> findProductTypeById(@PathVariable Long id) {
        return new ResponseEntity<>(demandRepository.findById(id), null, HttpStatus.OK);
    }

    @PostMapping("/client/{clientId}/demand")
    public ResponseEntity<Demand> create(@PathVariable Long clientId, @RequestBody OrderForm form) {
        List<DemandProductDto> formDtos = form.getProductOrders();
        validateProductsExistence(formDtos);
        Demand demand = new Demand();
        demand.setStatus("");
        demand = this.demandRepository.save(demand);

        List<DemandProduct> orderProducts = new ArrayList<>();
        for (DemandProductDto dto : formDtos) {
            orderProducts.add(demandProductRepository.save(new DemandProduct(demand, productService.getProduct(dto
              .getProduct()
              .getIdProduct()), dto.getQuantity())));
        }

        demand.setDemandProducts(orderProducts);

        this.demandRepository.save(demand);

        String uri = ServletUriComponentsBuilder
          .fromCurrentServletMapping()
          .path("/orders/{id}")
          .buildAndExpand(demand.getIdDemand())
          .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(demand, headers, HttpStatus.CREATED);
    }

    private void validateProductsExistence(List<DemandProductDto> demandProducts) {
    	
    	System.out.println(demandProducts);
    	
        List<DemandProductDto> list = demandProducts
          .stream()
          .filter(op -> Objects.isNull(productService.getProduct(op
            .getProduct()
            .getIdProduct())))
          .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(list)) {
            new ResourceNotFoundException("Product not found");
        }
    }

    public static class OrderForm {

        private List<DemandProductDto> productOrders;

        public List<DemandProductDto> getProductOrders() {
            return productOrders;
        }

        public void setProductOrders(List<DemandProductDto> productOrders) {
            this.productOrders = productOrders;
        }
    }
}
