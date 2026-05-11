package ma.moussaoui.wahiba.repositories;

import ma.moussaoui.wahiba.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}