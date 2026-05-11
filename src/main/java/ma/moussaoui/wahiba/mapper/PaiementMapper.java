package ma.moussaoui.wahiba.mapper;

import ma.moussaoui.wahiba.dto.PaiementDTO;
import ma.moussaoui.wahiba.entities.Paiement;

public class PaiementMapper {

    public static PaiementDTO toDTO(Paiement paiement) {

        PaiementDTO dto = new PaiementDTO();

        dto.setId(paiement.getId());
        dto.setDatePaiement(paiement.getDatePaiement());
        dto.setMontant(paiement.getMontant());
        dto.setTypePaiement(paiement.getTypePaiement());

        if (paiement.getContrat() != null) {
            dto.setContratId(paiement.getContrat().getId());
        }

        return dto;
    }
}