package ma.moussaoui.wahiba.repositories;

import ma.moussaoui.wahiba.entities.ContratAssurance;
import ma.moussaoui.wahiba.entities.ContratAutomobile;
import ma.moussaoui.wahiba.entities.ContratHabitation;
import ma.moussaoui.wahiba.entities.ContratSante;
import ma.moussaoui.wahiba.enums.StatutContrat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContratAssuranceRepository extends JpaRepository<ContratAssurance, Long> {

    // Par statut
    List<ContratAssurance> findByStatut(StatutContrat statut);

    // Par client

    // Pagination
    Page<ContratAssurance> findAll(Pageable pageable);


    // Par type (sous-classes)
}