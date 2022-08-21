package br.com.gcall.empresa.services;

import br.com.gcall.empresa.entity.Empresa;
import br.com.gcall.empresa.models.EmpresaVM;
import br.com.gcall.empresa.repository.EmpresaRepository;
import br.com.gcall.models.LoginModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository empresaRepository;

    public void insertCompany(EmpresaVM empresaVM) {
        try {
            Empresa empresa = new Empresa(empresaVM.getCompanyName(), empresaVM.getCnpj(), empresaVM.getCompanyEmail(), empresaVM.getPassword(), true);
            empresaRepository.save(empresa);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int updateCompany(EmpresaVM empresaVM, long companyId) {
        try {
            Empresa empresa = empresaRepository.findById(companyId).orElse(null);
            if (empresa == null) return 1;
            empresa = empresa.updateEmpresa(empresa, empresaVM);

            empresaRepository.save(empresa);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteCompany(long companyId) {
        try {
            if (!empresaRepository.existsById(companyId)) return 1;

            empresaRepository.deleteById(companyId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Optional<Empresa> getCompanyById(long companyId) {
        return empresaRepository.findById(companyId);
    }

    public List<Empresa> getCompanies() {
        List<Empresa> companyList = null;
        try {
            companyList = empresaRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return companyList;
    }

    public int login(LoginModel credentials) {
        try {
            Empresa empresa = empresaRepository.findByCompanyEmail(credentials.getEmail());
            if (empresa == null) return 1;
            if (!empresa.getPassword().equals(credentials.getPassword())) return 2;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
