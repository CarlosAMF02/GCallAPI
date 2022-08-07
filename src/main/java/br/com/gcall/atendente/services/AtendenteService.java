package br.com.gcall.atendente.services;

import br.com.gcall.atendente.entity.Atendente;
import br.com.gcall.atendente.models.AtendenteVM;
import br.com.gcall.atendente.repository.AtendenteRepository;
import br.com.gcall.empresa.entity.Empresa;
import br.com.gcall.empresa.services.EmpresaService;
import br.com.gcall.models.LoginModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class AtendenteService {
    @Autowired
    private AtendenteRepository atendenteRepository;
    @Autowired
    private EmpresaService empresaService;

    public int insertAttendant(AtendenteVM atendenteVM){
        try {
            Empresa empresa = empresaService.getCompanyById(atendenteVM.getCompanyId());
            if (empresa == null) throw new Exception();
            Atendente atendente = new Atendente().registerAttendant(atendenteVM, empresa);
            atendenteRepository.save(atendente);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    public int updateAttendant(AtendenteVM atendenteVM, long attendantId){
        try {
            Atendente atendente = atendenteRepository.findById(attendantId).orElse(null);
            if (atendente==null) return 2;
            if (atendenteVM.getCompanyId()!= 0L) {
                Empresa empresa = empresaService.getCompanyById(atendenteVM.getCompanyId());
                if(empresa==null) return 2;
                atendente.setEmpresa(empresa);
            }
            if (atendenteVM.getEmail()!=null) atendente.setEmail(atendenteVM.getEmail());
            if (atendenteVM.getPassword()!=null) atendente.setPassword(atendenteVM.getPassword());
            if (atendenteVM.getName()!=null) atendente.setName(atendenteVM.getName());
            if (atendenteVM.getCpf()!= 0L) atendente.setCpf(atendenteVM.getCpf());
            atendente.setUpdateDate(Calendar.getInstance().getTime());
            atendenteRepository.save(atendente);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    public int deleteAttendant (long attendantId) {
        try {
            if (!atendenteRepository.existsById(attendantId)) return 2;
            atendenteRepository.deleteById(attendantId);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    public Atendente getAttendantById (long attendantId) {
        Atendente atendente = null;
        try {
            atendente = atendenteRepository.findById(attendantId).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return atendente;
    }

    public List<Atendente> getAttendants () {
        List<Atendente> atendentes = null;
        try {
            atendentes = atendenteRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return atendentes;
    }

    public List<Atendente> getAttendantsByCompanyId  (long companyId) {
        List<Atendente> atendentes = null;
        try {
            Empresa empresa = empresaService.getCompanyById(companyId);
            if (empresa == null) throw new Exception();
            atendentes = atendenteRepository.findByEmpresa(empresa);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return atendentes;
    }

    public boolean login(LoginModel credentials) {
        try {
            Atendente atendente = atendenteRepository.findByEmail(credentials.getEmail());
            if (atendente == null || atendente.getPassword() != credentials.getPassword()) return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
