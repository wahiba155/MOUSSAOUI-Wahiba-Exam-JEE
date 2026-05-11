package ma.moussaoui.wahiba.repositories;

import ma.moussaoui.wahiba.entities.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
}