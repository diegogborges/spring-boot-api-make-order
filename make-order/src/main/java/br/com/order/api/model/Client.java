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
@Table(name="client")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Client implements Serializable {

	private static final long serialVersionUID = 7768800425127272924L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idclient")
    private Long idClient;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

}
