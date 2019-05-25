package br.com.order.api.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Diego Gomes Borges
 * @date 22/03/2019
 */
@Entity
@Table(name="product_type")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductType implements Serializable {

	private static final long serialVersionUID = -1350257758904148625L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idProductType")
    private Long idProductType;

    @Column(name="name")
    private String name;

}
