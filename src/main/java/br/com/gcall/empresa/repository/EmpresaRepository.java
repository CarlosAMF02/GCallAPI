package br.com.gcall.empresa.repository;

import br.com.gcall.cliente.entity.Cliente;
import br.com.gcall.empresa.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    Optional<Empresa> findByCompanyEmail(String companyEmail);
}
