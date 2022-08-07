package br.com.gcall.atendente.repository;

import br.com.gcall.atendente.entity.Atendente;
import br.com.gcall.atendente.entity.AtendenteChamado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtendenteChamadoRepository extends JpaRepository<AtendenteChamado, Long> {
    List<AtendenteChamado> findByAtendente(Atendente atendente);
}
