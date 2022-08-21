package br.com.gcall.empresa.entity;

import br.com.gcall.empresa.models.EmpresaVM;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity(name = "T_GC_EMPRESA")
public class Empresa {
    @Id
    @Column(name = "cd_empresa")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "nm_empresa", nullable = false)
    private String companyName;
    @Column(name = "nr_cnpj", unique = true, nullable = false)
    private long cnpj;
    @Column(name = "st_ativo", nullable = false)
    private boolean registerStatus;
    @Column(name = "ds_email", length = 100, unique = true, nullable = false)
    private String companyEmail;
    @Column(name = "ds_senha", length = 50, nullable = false)
    private String password;
    @Column(name = "dt_cadastro", nullable = false)
    private Date registerDate;
    @Column(name = "dt_atualizacao", nullable = false)
    private Date updateDate;

    public Empresa(long id, String companyName, long cnpj, boolean registerStatus, String companyEmail, String password, Date registerDate, Date updateDate) {
        this.id = id;
        this.companyName = companyName;
        this.cnpj = cnpj;
        this.registerStatus = registerStatus;
        this.companyEmail = companyEmail;
        this.password = password;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
    }

    public Empresa(String companyName, long cnpj, String companyEmail, String password, boolean registerStatus) {
        this.companyName = companyName;
        this.cnpj = cnpj;
        this.companyEmail = companyEmail;
        this.password = password;
        this.registerStatus = registerStatus;
        this.registerDate = Calendar.getInstance().getTime();
        this.updateDate = Calendar.getInstance().getTime();
    }

    public Empresa() {

    }

    public Empresa updateEmpresa (Empresa empresa, EmpresaVM empresaVM) {
        empresa.setCompanyEmail(empresaVM.getCompanyEmail());
        empresa.setCompanyName(empresaVM.getCompanyName());
        empresa.setCnpj(empresaVM.getCnpj());
        empresa.setPassword(empresaVM.getPassword());
        empresa.setUpdateDate(Calendar.getInstance().getTime());

        return empresa;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public long getCnpj() {
        return cnpj;
    }

    public void setCnpj(long cnpj) {
        this.cnpj = cnpj;
    }

    public boolean getRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(boolean registerStatus) {
        this.registerStatus = registerStatus;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
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
