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
import java.util.Optional;

@Service
public class AtendenteService {
    @Autowired
    private AtendenteRepository atendenteRepository;
    @Autowired
    private EmpresaService empresaService;

    public int insertAttendant(AtendenteVM atendenteVM){
        try {
            Empresa empresa = empresaService.getCompanyById(atendenteVM.getCompanyId()).orElse(null);
            if (empresa == null) return 1;
            Atendente atendente = atendenteRepository.findByEmail(atendenteVM.getEmail()).orElse(null);
            if (atendente != null) return 3;
            atendente = new Atendente().registerAttendant(atendenteVM, empresa);
            atendenteRepository.save(atendente);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int updateAttendant(AtendenteVM atendenteVM, long attendantId){
        try {
            Atendente atendente = atendenteRepository.findById(attendantId).orElse(null);
            if (atendente==null) throw new Exception();

            Empresa empresa = empresaService.getCompanyById(atendenteVM.getCompanyId()).orElse(null);
            if(empresa==null) throw new Exception();

            atendente.setEmpresa(empresa);
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
            if (!atendenteRepository.existsById(attendantId)) throw new Exception();
            atendenteRepository.deleteById(attendantId);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    public Optional<Atendente> getAttendantById (long attendantId) {

        return atendenteRepository.findById(attendantId);
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
            Empresa empresa = empresaService.getCompanyById(companyId).orElse(null);
            if (empresa == null) throw new Exception();
            atendentes = atendenteRepository.findByEmpresa(empresa);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return atendentes;
    }

    public int login(LoginModel credentials) {
        try {
            Atendente atendente = atendenteRepository.findByEmail(credentials.getEmail()).orElse(null);

            if (atendente == null) return 1;
            if(!atendente.getPassword().equals(credentials.getPassword())) return 2;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
