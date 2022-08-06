package br.com.gcall.cliente.entity;

import javax.persistence.*;

@Entity(name = "T_GC_CLIENTE")
public class Cliente {
    @Id
    @Column(name = "cd_cliente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "nr_cpf", unique = true, nullable = false)
    private long cpf;

    public Cliente(long id, long cpf) {
        this.id = id;
        this.cpf = cpf;
    }

    public Cliente() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }
}
