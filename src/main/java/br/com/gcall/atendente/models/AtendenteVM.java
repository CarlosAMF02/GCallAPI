package br.com.gcall.atendente.models;

public class AtendenteVM {
    private String name;
    private long cpf;
    private String email;
    private String password;
    private Long companyId;

    public AtendenteVM(String name, long cpf, String email, String password, Long companyId) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.companyId = companyId;
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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
