package ma.moussaoui.wahiba.repositories;

import ma.moussaoui.wahiba.entities.Paiement;
import ma.moussaoui.wahiba.enums.TypePaiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {

    List<Paiement> findByContratId(Long contratId);

    List<Paiement> findByTypePaiement(TypePaiement typePaiement);

    @Query("SELECT SUM(p.montant) FROM Paiement p WHERE p.contrat.id = :contratId")  // ✅ contrat pas contratAssurance
    Double sumMontantByContratId(@Param("contratId") Long contratId);
}