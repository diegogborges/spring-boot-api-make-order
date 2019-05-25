package br.com.order.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DemandProductPK implements Serializable {

	private static final long serialVersionUID = 7082027921814625341L;

	@JsonBackReference
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "idDemand")
    private Demand demand;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "idProduct")
    private Product product;

}
