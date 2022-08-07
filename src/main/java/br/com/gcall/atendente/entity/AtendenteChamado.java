package br.com.gcall.atendente.entity;

import br.com.gcall.chamado.entity.Chamado;
import lombok.Data;

import javax.persistence.*;

@Data
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

    public AtendenteChamado(Atendente atendente, Chamado chamado) {
        this.atendente = atendente;
        this.chamado = chamado;
    }



    public AtendenteChamado() {

    }

}
