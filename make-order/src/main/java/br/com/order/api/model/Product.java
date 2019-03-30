package br.com.order.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Diego Gomes Borges
 * @date 22/03/2019
 */
@Entity
@Table(name="product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idProduct")
    private Long idProduct;

    @Column(columnDefinition = "varchar(150)")
    private String name;

    @Column(columnDefinition = "numeric(20,4)")
    private double price;

    @Column(name="number")
    private int number;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idproduct_type", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private ProductType productType;

    public Product() {
    }

    public Product(String name, double price, int number) {
        this.name = name;
        this.price = price;
        this.number = number;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", number=" + number +
                '}';
    }
}
