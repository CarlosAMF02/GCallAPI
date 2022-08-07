package br.com.gcall.atendente.entity;

import br.com.gcall.atendente.models.AtendenteVM;
import br.com.gcall.empresa.entity.Empresa;
import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Data
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

    public Atendente registerAttendant(AtendenteVM atendenteVM, Empresa empresa) {
        Atendente atendente = new Atendente();
        atendente.setName(atendenteVM.getName());
        atendente.setEmail(atendenteVM.getEmail());
        atendente.setCpf(atendenteVM.getCpf());
        atendente.setPassword(atendenteVM.getPassword());
        atendente.setEmpresa(empresa);
        atendente.setRegisterDate(Calendar.getInstance().getTime());
        atendente.setUpdateDate(Calendar.getInstance().getTime());
        atendente.setRegisterStatus(true);

        return atendente;
    }

    public Atendente(Empresa empresa, String name, long cpf, boolean registerStatus, String email, String password, Date registerDate, Date updateDate) {
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
}
