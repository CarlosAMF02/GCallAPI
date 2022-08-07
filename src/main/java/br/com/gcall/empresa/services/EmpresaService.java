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

    public int insertCompany(EmpresaVM empresaVM) {
        try {
            Empresa empresa = new Empresa(empresaVM.getCompanyName(), empresaVM.getCnpj(), empresaVM.getCompanyEmail(), empresaVM.getPassword(), true);
            empresa.setRegisterDate(Calendar.getInstance().getTime());
            empresa.setUpdateDate(Calendar.getInstance().getTime());
            empresaRepository.save(empresa);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    public int updateCompany(EmpresaVM empresaVM, long companyId) {
        try {
            Empresa empresa = empresaRepository.findById(companyId).orElse(null);
            if (empresa == null) return 2;
            if (empresaVM.getCompanyEmail() != null) empresa.setCompanyEmail(empresaVM.getCompanyEmail());
            if (empresaVM.getCompanyName() != null) empresa.setCompanyName(empresaVM.getCompanyName());
            if (empresaVM.getCnpj() != 0L) empresa.setCnpj(empresaVM.getCnpj());
            if (empresaVM.getPassword() != null) empresa.setPassword(empresaVM.getPassword());
            empresa.setUpdateDate(Calendar.getInstance().getTime());
            empresaRepository.save(empresa);
        }
        catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    public int deleteCompany(long companyId) {
        try {
            if (!empresaRepository.existsById(companyId)) {
                return 2;
            }
            empresaRepository.deleteById(companyId);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    public Empresa getCompanyById(long companyId) {
        Empresa company = null;
        try {
            company = empresaRepository.findById(companyId).orElse(null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return company;
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

    public boolean login(LoginModel credentials) {
        try {
            Empresa empresa = empresaRepository.findByCompanyEmail(credentials.getEmail());
            if (empresa == null || !empresa.getPassword().equals(credentials.getPassword())) return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
