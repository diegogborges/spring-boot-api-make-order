package br.com.order.api.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Diego Gomes Borges
 * @date 22/03/2019
 */
@Entity
@Table(name="client")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idclient")
    private Long idClient;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="senha")
    private String senha;

    public Client() {
    }

    public Client(String name, String email, String senha) {
        this.name = name;
        this.email = email;
        this.senha = senha;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
