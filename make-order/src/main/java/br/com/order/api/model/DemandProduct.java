package br.com.order.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DemandProduct implements Serializable {

	private static final long serialVersionUID = -2600311542306529925L;

	@EmbeddedId
    @JsonIgnore
    private DemandProductPK pk;

    @Column(nullable = false)
    private Integer quantity;

    public DemandProduct(Demand demand, Product product, Integer quantity) {
        pk = new DemandProductPK();
        pk.setDemand(demand);
        pk.setProduct(product);
        this.quantity = quantity;
    }

    @Transient
    public Product getProduct() {
        return this.pk.getProduct();
    }

    @Transient
    public Double getTotalPrice() {
        return getProduct().getPrice() * getQuantity();
    }

}