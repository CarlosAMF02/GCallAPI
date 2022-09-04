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
import java.util.Optional;

@Service
public class ChamadoService {
    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private StatusService statusService;
    @Autowired
    private ClienteService clienteService;

    public int insertCall(ChamadoVM chamadoVM) {
        try {
            Status status = statusService.getStatusById(chamadoVM.getStatusId()).orElse(null);
            Cliente cliente = clienteService.findClientById(chamadoVM.getClienteId()).orElse(null);
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
            Cliente cliente = clienteService.findClientById(chamadoVM.getClienteId()).orElse(null);
            Status status = statusService.getStatusById(chamadoVM.getStatusId()).orElse(null);

            if (chamado == null || cliente == null || status == null) return 1;

            chamado = chamado.updateCall(chamado, chamadoVM, status, cliente, chamadoId);

            chamadoRepository.save(chamado);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteCall(long chamadoId) {
        try {
            if (!chamadoRepository.existsById(chamadoId)) {
                return 1;
            }
            chamadoRepository.deleteById(chamadoId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Optional<Chamado> getCallById(long chamadoId) {

        return chamadoRepository.findById(chamadoId);
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

    public List<Chamado> getCallsByClientId(Cliente cliente) {
        List<Chamado> callList = null;
        try {
            callList = chamadoRepository.findByCliente(cliente);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return callList;
    }
}
