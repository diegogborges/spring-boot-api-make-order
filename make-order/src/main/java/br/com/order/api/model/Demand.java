package br.com.order.api.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name="demand")
public class Demand implements Serializable {

	private static final long serialVersionUID = -5217722155551245904L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="iddemand")
    private Long idDemand;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name="date_demand")
    private Date dateDemand;

    @Column(name="status")
    private String status;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_idclient", nullable = false)
    private Client client;

    @JsonManagedReference
    @OneToMany(mappedBy = "pk.demand")
    @Valid
    private List<DemandProduct> demandProducts = new ArrayList<>();

    @Transient
    public Double getTotalOrderPrice() {
        double sum = 0D;
        List<DemandProduct> demandProducts = getDemandProducts();
        for (DemandProduct dp : demandProducts) {
            sum += dp.getTotalPrice();
        }
        return sum;
    }
 
    @Transient
    public int getNumberOfProducts() {
        return this.demandProducts.size();
    }
    
    public Long getIdDemand() {
        return idDemand;
    }

    public void setIdDemand(Long idDemand) {
        this.idDemand = idDemand;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DemandProduct> getDemandProducts() {
		return demandProducts;
	}

	public void setDemandProducts(List<DemandProduct> demandProducts) {
		this.demandProducts = demandProducts;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Demand demand = (Demand) o;
        return Objects.equals(idDemand, demand.idDemand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDemand);
    }
}
