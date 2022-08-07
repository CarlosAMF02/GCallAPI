package br.com.gcall.chamado.entity;

import br.com.gcall.chamado.models.ChamadoVM;
import br.com.gcall.cliente.entity.Cliente;
import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Data
@Entity(name = "T_GC_CHAMADO")
public class Chamado {
    @Id
    @Column(name = "cd_chamado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "cd_status")
    private Status status;
    @ManyToOne
    @JoinColumn(name = "cd_cliente")
    private Cliente cliente;
    @Column(name = "nm_area", length = 30, nullable = false)
    private String departamentName;
    @Column(name = "ds_chamado", length = 1024, nullable = false)
    private String description;
    @Column(name = "st_ativo", nullable = false)
    private boolean registerStatus;
    @Column(name = "dt_cadastro", nullable = false)
    private Date registerDate;
    @Column(name = "dt_atualizacao", nullable = false)
    private Date updateDate;

    public Chamado registerCall(ChamadoVM chamadoVM, Status status, Cliente cliente){
        Chamado chamado = new Chamado();
        chamado.setStatus(status);
        chamado.setCliente(cliente);
        chamado.setDepartamentName(chamadoVM.getDepartamentName());
        chamado.setDescription(chamadoVM.getDescription());
        chamado.setRegisterStatus(true);
        chamado.setRegisterDate(Calendar.getInstance().getTime());
        chamado.setUpdateDate(Calendar.getInstance().getTime());
        return chamado;
    }

    public Chamado updateCall(ChamadoVM chamadoVM, Status status, Cliente cliente, long chamadoId){
        Chamado chamado = new Chamado();
        chamado.setId(chamadoId);
        chamado.setStatus(status);
        chamado.setCliente(cliente);
        chamado.setDepartamentName(chamadoVM.getDepartamentName());
        chamado.setDescription(chamadoVM.getDescription());
        chamado.setRegisterStatus(true);
        chamado.setRegisterDate(Calendar.getInstance().getTime());
        chamado.setUpdateDate(Calendar.getInstance().getTime());
        return chamado;
    }

    public Chamado(long id, Status status, Cliente cliente, String departamentName, String description, boolean registerStatus, Date registerDate, Date updateDate) {
        this.id = id;
        this.status = status;
        this.cliente = cliente;
        this.departamentName = departamentName;
        this.description = description;
        this.registerStatus = registerStatus;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
    }

    public Chamado() { }
}
