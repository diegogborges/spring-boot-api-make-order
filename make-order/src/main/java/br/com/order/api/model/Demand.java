package br.com.order.api.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name="demand")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
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

}
