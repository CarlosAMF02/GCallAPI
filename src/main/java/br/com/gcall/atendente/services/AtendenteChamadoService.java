package br.com.gcall.atendente.services;

import br.com.gcall.atendente.entity.Atendente;
import br.com.gcall.atendente.entity.AtendenteChamado;
import br.com.gcall.atendente.models.AtendenteChamadoVM;
import br.com.gcall.atendente.models.AtendenteVM;
import br.com.gcall.atendente.repository.AtendenteChamadoRepository;
import br.com.gcall.chamado.entity.Chamado;
import br.com.gcall.chamado.repository.ChamadoRepository;
import br.com.gcall.chamado.services.ChamadoService;
import br.com.gcall.empresa.entity.Empresa;
import br.com.gcall.models.LoginModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class AtendenteChamadoService {
    @Autowired
    private AtendenteChamadoRepository atendenteChamadoRepository;
    @Autowired
    private ChamadoService chamadoService;
    @Autowired
    private AtendenteService atendenteService;

    public void insertCallToAttendant(Atendente atendente, Chamado chamado){
        try {
            AtendenteChamado atendenteChamado = new AtendenteChamado(atendente, chamado);
            atendenteChamadoRepository.save(atendenteChamado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int deleteAttendantCall (long attendantCallId) {
        try {
            if (!atendenteChamadoRepository.existsById(attendantCallId)) return 1;
            atendenteChamadoRepository.deleteById(attendantCallId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<AtendenteChamado> getAttendantsCalls () {
        List<AtendenteChamado> attendantsCalls = null;
        try {
            attendantsCalls = atendenteChamadoRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attendantsCalls;
    }

    public List<AtendenteChamado> getAttendantCallsById  (Atendente atendente) {
        List<AtendenteChamado> attendantCalls = null;
        try {
            attendantCalls = atendenteChamadoRepository.findByAtendente(atendente);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attendantCalls;
    }
}
