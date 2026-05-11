package ma.moussaoui.wahiba.repositories;

import ma.moussaoui.wahiba.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    // Recherche par nom (pagination)
    Page<Client> findByNomContainingIgnoreCase(String nom, Pageable pageable);

    // Recherche par nom (liste simple)
    List<Client> findByNomContainingIgnoreCase(String nom);
}