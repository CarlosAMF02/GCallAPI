package br.com.gcall.empresa.repository;

import br.com.gcall.cliente.entity.Cliente;
import br.com.gcall.empresa.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    Empresa findByCompanyEmail(String companyEmail);
}
