package br.com.gcall.empresa.models;

import javax.persistence.Column;
import java.util.Date;

public class EmpresaVM {
    private String companyName;
    private long cnpj;
    private String companyEmail;
    private String password;

    public EmpresaVM(String companyName, long cnpj, String companyEmail, String password) {
        this.companyName = companyName;
        this.cnpj = cnpj;
        this.companyEmail = companyEmail;
        this.password = password;
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
}
