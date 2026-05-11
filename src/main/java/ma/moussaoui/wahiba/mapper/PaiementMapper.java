package ma.moussaoui.wahiba.mapper;

import ma.moussaoui.wahiba.dto.PaiementDTO;
import ma.moussaoui.wahiba.entities.Paiement;
import org.springframework.stereotype.Component;

@Component
public class PaiementMapper {

    public PaiementDTO toDTO(Paiement paiement) {
        PaiementDTO dto = new PaiementDTO();
        dto.setId(paiement.getId());
        dto.setDatePaiement(paiement.getDatePaiement());   // ✅ datePaiement
        dto.setMontant(paiement.getMontant());
        dto.setTypePaiement(paiement.getTypePaiement());
        if (paiement.getContrat() != null)                 // ✅ contrat
            dto.setContratId(paiement.getContrat().getId());
        return dto;
    }

    public Paiement toEntity(PaiementDTO dto) {
        Paiement paiement = new Paiement();
        paiement.setId(dto.getId());
        paiement.setDatePaiement(dto.getDatePaiement());   // ✅ datePaiement
        paiement.setMontant(dto.getMontant());
        paiement.setTypePaiement(dto.getTypePaiement());
        // contrat sera setté dans le Service
        return paiement;
    }
}