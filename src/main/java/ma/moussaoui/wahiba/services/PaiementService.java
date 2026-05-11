package ma.moussaoui.wahiba.services;

import ma.moussaoui.wahiba.dto.PaiementDTO;
import ma.moussaoui.wahiba.enums.TypePaiement;

import java.util.List;

public interface PaiementService {
    List<PaiementDTO> getAllPaiements();
    PaiementDTO getPaiementById(Long id);
    List<PaiementDTO> getPaiementsByContratId(Long contratId);
    List<PaiementDTO> getPaiementsByType(TypePaiement type);
    Double getTotalPaiementsParContrat(Long contratId);
    PaiementDTO savePaiement(PaiementDTO paiementDTO);
    PaiementDTO updatePaiement(PaiementDTO paiementDTO);
    void deletePaiement(Long id);
}