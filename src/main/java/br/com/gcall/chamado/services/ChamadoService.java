package br.com.gcall.chamado.services;

import br.com.gcall.chamado.entity.Chamado;
import br.com.gcall.chamado.entity.Status;
import br.com.gcall.chamado.models.ChamadoVM;
import br.com.gcall.chamado.repository.ChamadoRepository;
import br.com.gcall.cliente.entity.Cliente;
import br.com.gcall.cliente.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChamadoService {
    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private StatusService statusService;
    @Autowired
    private ClienteService clienteService;

    public int insertCall(ChamadoVM chamadoVM, long statusId, long clienteId) {
        try {
            Status status = statusService.getStatusById(statusId);
            Cliente cliente = clienteService.findClientById(clienteId);
            if (cliente == null || status == null) throw new Exception();
            Chamado chamado = new Chamado().registerCall(chamadoVM, status, cliente);
            chamadoRepository.save(chamado);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    public int updateCall(ChamadoVM chamadoVM, long chamadoId) {
        try {
            Chamado chamado = chamadoRepository.findById(chamadoId).orElse(null);
            if (chamado == null) return 2;
            if (chamadoVM.getClienteId() != 0L) {
                Cliente cliente = clienteService.findClientById(chamadoVM.getClienteId());
                if (cliente == null) return 2;
                chamado.setCliente(cliente);
            }
            if (chamadoVM.getStatusId() != 0L) {
                Status status = statusService.getStatusById(chamadoVM.getStatusId());
                if (status == null) return 2;
                chamado.setStatus(status);
            }
            if (chamadoVM.getDepartamentName() != null) chamado.setDepartamentName(chamadoVM.getDepartamentName());
            if (chamadoVM.getDescription() != null) chamado.setDescription(chamadoVM.getDescription());
            chamadoRepository.save(chamado);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    public int deleteCall(long chamadoId) {
        try {
            if (!chamadoRepository.existsById(chamadoId)) {
                return 2;
            }
            chamadoRepository.deleteById(chamadoId);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    public Chamado getCallById(long chamadoId) {
        Chamado chamado = null;
        try {
            chamado = chamadoRepository.findById(chamadoId).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chamado;
    }

    public List<Chamado> getCalls() {
        List<Chamado> callList = null;
        try {
            callList = chamadoRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return callList;
    }

    public List<Chamado> getCallsByClientId(long clientId) {
        List<Chamado> callList = null;
        try {
            Cliente cliente = clienteService.findClientById(clientId);
            if (cliente == null) return null;
            callList = chamadoRepository.findByCliente(cliente);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return callList;
    }
}
