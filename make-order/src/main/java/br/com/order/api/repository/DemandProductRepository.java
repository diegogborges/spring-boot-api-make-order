package br.com.order.api.repository;

import br.com.order.api.model.DemandProduct;
import br.com.order.api.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandProductRepository extends JpaRepository<DemandProduct, Long> {

}
