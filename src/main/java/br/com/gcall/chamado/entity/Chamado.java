package br.com.gcall.chamado.entity;

import br.com.gcall.cliente.entity.Cliente;

import javax.persistence.*;
import java.util.Date;

@Entity("T_GC_CHAMADO")
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getDepartamentName() {
        return departamentName;
    }

    public void setDepartamentName(String departamentName) {
        this.departamentName = departamentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(boolean registerStatus) {
        this.registerStatus = registerStatus;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
