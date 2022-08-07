package br.com.gcall.chamado.repository;

import br.com.gcall.chamado.entity.Chamado;
import br.com.gcall.cliente.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
    List<Chamado> findByCliente(Cliente cliente);
}
