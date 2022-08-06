package br.com.gcall.atendente.entity;

import br.com.gcall.chamado.entity.Chamado;

import javax.persistence.*;

@Entity(name = "T_GC_ATENDENTE_CHAMADO")
public class AtendenteChamado {
    @Id
    @Column(name = "cd_atendente_chamado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "cd_atendente")
    private Atendente atendente;
    @ManyToOne
    @JoinColumn(name = "cd_chamado")
    private Chamado chamado;

    public AtendenteChamado(long id, Atendente atendente, Chamado chamado) {
        this.id = id;
        this.atendente = atendente;
        this.chamado = chamado;
    }

    public AtendenteChamado() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    public Chamado getChamado() {
        return chamado;
    }

    public void setChamado(Chamado chamado) {
        this.chamado = chamado;
    }
}
