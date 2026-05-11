package ma.moussaoui.wahiba.mapper;

import ma.moussaoui.wahiba.dto.ContratDTO;
import ma.moussaoui.wahiba.entities.ContratAssurance;

public class ContratMapper {

    public static ContratDTO toDTO(ContratAssurance contrat) {

        ContratDTO dto = new ContratDTO();

        dto.setId(contrat.getId());
        dto.setDateSouscription(contrat.getDateSouscription());
        dto.setStatut(contrat.getStatut());
        dto.setMontantCotisation(contrat.getMontantCotisation());
        dto.setDureeContrat(contrat.getDureeContrat());
        dto.setTauxCouverture(contrat.getTauxCouverture());

        if (contrat.getClient() != null) {
            dto.setClientId(contrat.getClient().getId());
        }

        return dto;
    }
}