package br.com.gcall.empresa.controller;

import br.com.gcall.empresa.entity.Empresa;
import br.com.gcall.empresa.models.EmpresaVM;
import br.com.gcall.empresa.services.EmpresaService;
import br.com.gcall.models.LoginModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
    @Autowired
    EmpresaService empresaService;
    @GetMapping
    public List<Empresa> getAllCompanies() {
        return empresaService.getCompanies();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Empresa getCompany(@PathVariable(name = "id") long companyId) {
        return empresaService.getCompanyById(companyId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int createCompany(@RequestBody EmpresaVM empresaVM) {
        return empresaService.insertCompany(empresaVM);
    }

    @PostMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public int updateCompany(@RequestBody EmpresaVM company, @PathVariable(name = "id") long companyId) {
        return empresaService.updateCompany(company, companyId);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public int deleteCompany(@PathVariable(name = "id") long companyId) {
        return empresaService.deleteCompany(companyId);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public boolean login (LoginModel credentials) {
        return empresaService.login(credentials);
    }
}
