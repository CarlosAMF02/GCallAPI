package br.com.gcall.empresa.controller;

import br.com.gcall.empresa.entity.Empresa;
import br.com.gcall.empresa.models.EmpresaVM;
import br.com.gcall.empresa.services.EmpresaService;
import br.com.gcall.models.LoginModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Empresa> getCompany(@PathVariable(name = "id") long companyId) {
        Empresa empresa = empresaService.getCompanyById(companyId).orElse(null);

        if (empresa == null) ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(empresa);
    }

    @PostMapping()
    public ResponseEntity<EmpresaVM> createCompany(@RequestBody EmpresaVM empresaVM) {
        int responseStatus = empresaService.insertCompany(empresaVM);
        if (responseStatus == 3) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaVM);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmpresaVM> updateCompany(@RequestBody EmpresaVM company, @PathVariable(name = "id") long companyId) {
        int responseStatus = empresaService.updateCompany(company, companyId);

        if (responseStatus == 1) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(company);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteCompany(@PathVariable(name = "id") long companyId) {
        int responseStatus = empresaService.deleteCompany(companyId);

        if (responseStatus == 1) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login (@RequestBody LoginModel credentials) {
        int responseStatus = empresaService.login(credentials);

        if (responseStatus == 1) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        if (responseStatus == 2) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.ok(credentials);
    }
}
