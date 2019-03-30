package br.com.order.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="demand")
public class Demand implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="iddemand")
    private Long idDemand;

    @Column(name="quantity")
    private int quantity;

    @Column(name="date_demand")
    private Date dateDemand;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_idclient", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_idproduct", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Product product;

    public Demand(int quantity, Date dateDemand, Client client, Product product) {
        this.quantity = quantity;
        this.dateDemand = dateDemand;
        this.client = client;
        this.product = product;
    }

    public Long getIdDemand() {
        return idDemand;
    }

    public void setIdDemand(Long idDemand) {
        this.idDemand = idDemand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDateDemand() {
        return dateDemand;
    }

    public void setDateDemand(Date dateDemand) {
        this.dateDemand = dateDemand;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Demand{" +
                "idDemand=" + idDemand +
                ", quantity=" + quantity +
                ", dateDemand=" + dateDemand +
                ", client=" + client +
                ", product=" + product +
                '}';
    }
}
