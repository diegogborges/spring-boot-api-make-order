package br.com.order.api.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Diego Gomes Borges
 * @date 22/03/2019
 */
@Entity
@Table(name="product_type")
public class ProductType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idProductType")
    private Long idProductType;

    @Column(name="name")
    private String name;

    public ProductType() {
    }

    public ProductType(String name) {
        this.name = name;
    }

    public Long getIdProductType() {
        return idProductType;
    }

    public void setIdProductType(Long idProductType) {
        this.idProductType = idProductType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductType{" +
                "idProductType=" + idProductType +
                ", name='" + name + '\'' +
                '}';
    }
}
