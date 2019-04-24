package br.com.order.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class DemandProduct implements Serializable {

	private static final long serialVersionUID = -2600311542306529925L;

	@EmbeddedId
    @JsonIgnore
    private DemandProductPK pk;

    @Column(nullable = false)
    private Integer quantity;

    public DemandProduct() {
    }

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

    public DemandProductPK getPk() {
        return pk;
    }

    public void setPk(DemandProductPK pk) {
        this.pk = pk;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}