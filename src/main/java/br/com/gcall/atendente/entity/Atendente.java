package br.com.gcall.atendente.entity;

import br.com.gcall.empresa.entity.Empresa;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "T_GC_ATENDENTE")
public class Atendente {
    @Id
    @Column(name = "cd_atendente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "cd_empresa")
    private Empresa empresa;
    @Column(name = "nm_atendente", length = 50,nullable = false)
    private String name;
    @Column(name = "nr_cpf", length = 11, unique = true, nullable = false)
    private long cpf;
    @Column(name = "st_ativo", nullable = false)
    private boolean registerStatus;
    @Column(name = "ds_email", length = 100,unique = true, nullable = false)
    private String email;
    @Column(name = "ds_senha", length = 50, nullable = false)
    private String password;
    @Column(name = "dt_cadastro", nullable = false)
    private Date registerDate;
    @Column(name = "dt_atualizacao", nullable = false)
    private Date updateDate;

    public Atendente(long id, Empresa empresa, String name, long cpf, boolean registerStatus, String email, String password, Date registerDate, Date updateDate) {
        this.id = id;
        this.empresa = empresa;
        this.name = name;
        this.cpf = cpf;
        this.registerStatus = registerStatus;
        this.email = email;
        this.password = password;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
    }

    public Atendente() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public boolean getRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(boolean registerStatus) {
        this.registerStatus = registerStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
