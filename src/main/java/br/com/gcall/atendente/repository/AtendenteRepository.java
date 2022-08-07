package br.com.gcall.atendente.repository;

import br.com.gcall.atendente.entity.Atendente;
import br.com.gcall.empresa.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtendenteRepository extends JpaRepository<Atendente, Long> {
    List<Atendente> findByEmpresa(Empresa empresa);
    Atendente findByEmail(String email);
}
