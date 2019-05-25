package br.com.order.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product implements Serializable {

	private static final long serialVersionUID = -7853343810134512970L;

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

}
