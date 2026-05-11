package ma.moussaoui.wahiba.mapper;

import ma.moussaoui.wahiba.dto.*;
import ma.moussaoui.wahiba.entities.*;
import org.springframework.stereotype.Component;

@Component
public class ContratMapper {

    // ─── Entity → DTO générique ────────────────────────────────────────────────
    public ContratDTO toDTO(ContratAssurance contrat) {
        if (contrat instanceof ContratAutomobile)
            return toAutomobileDTO((ContratAutomobile) contrat);
        if (contrat instanceof ContratHabitation)
            return toHabitationDTO((ContratHabitation) contrat);
        if (contrat instanceof ContratSante)
            return toSanteDTO((ContratSante) contrat);
        return mapChampsCommuns(contrat, new ContratDTO());
    }

    // ─── Automobile ────────────────────────────────────────────────────────────
    public ContratAutomobileDTO toAutomobileDTO(ContratAutomobile c) {
        ContratAutomobileDTO dto = new ContratAutomobileDTO();
        mapChampsCommuns(c, dto);
        dto.setNumeroImmatriculation(c.getNumeroImmatriculation());
        dto.setMarqueVehicule(c.getMarqueVehicule());
        dto.setModeleVehicule(c.getModeleVehicule());
        dto.setTypeContrat("AUTOMOBILE");
        return dto;
    }

    public ContratAutomobile toAutomobileEntity(ContratAutomobileDTO dto) {
        ContratAutomobile c = new ContratAutomobile();
        mapChammunsDTOtoEntity(dto, c);
        c.setNumeroImmatriculation(dto.getNumeroImmatriculation());
        c.setMarqueVehicule(dto.getMarqueVehicule());
        c.setModeleVehicule(dto.getModeleVehicule());
        return c;
    }

    // ─── Habitation ────────────────────────────────────────────────────────────
    public ContratHabitationDTO toHabitationDTO(ContratHabitation c) {
        ContratHabitationDTO dto = new ContratHabitationDTO();
        mapChampsCommuns(c, dto);
        dto.setTypeLogement(c.getTypeLogement());
        dto.setAdresseLogement(c.getAdresseLogement());
        dto.setSuperficie(c.getSuperficie());
        dto.setTypeContrat("HABITATION");
        return dto;
    }

    public ContratHabitation toHabitationEntity(ContratHabitationDTO dto) {
        ContratHabitation c = new ContratHabitation();
        mapChammunsDTOtoEntity(dto, c);
        c.setTypeLogement(dto.getTypeLogement());
        c.setAdresseLogement(dto.getAdresseLogement());
        c.setSuperficie(dto.getSuperficie());
        return c;
    }

    // ─── Santé ─────────────────────────────────────────────────────────────────
    public ContratSanteDTO toSanteDTO(ContratSante c) {
        ContratSanteDTO dto = new ContratSanteDTO();
        mapChampsCommuns(c, dto);
        dto.setNiveauCouverture(c.getNiveauCouverture());
        dto.setNombrePersonnesCouvertes(c.getNombrePersonnesCouvertes());
        dto.setTypeContrat("SANTE");
        return dto;
    }

    public ContratSante toSanteEntity(ContratSanteDTO dto) {
        ContratSante c = new ContratSante();
        mapChammunsDTOtoEntity(dto, c);
        c.setNiveauCouverture(dto.getNiveauCouverture());
        c.setNombrePersonnesCouvertes(dto.getNombrePersonnesCouvertes());
        return c;
    }

    // ─── Helpers privés ────────────────────────────────────────────────────────
    private ContratDTO mapChampsCommuns(ContratAssurance entity, ContratDTO dto) {
        dto.setId(entity.getId());
        dto.setDateSouscription(entity.getDateSouscription());   // ✅
        dto.setStatut(entity.getStatut());
        dto.setDateValidation(entity.getDateValidation());
        dto.setMontantCotisation(entity.getMontantCotisation());
        dto.setDureeContrat(entity.getDureeContrat());
        dto.setTauxCouverture(entity.getTauxCouverture());
        if (entity.getClient() != null)
            dto.setClientId(entity.getClient().getId());         // ✅
        return dto;
    }

    private void mapChammunsDTOtoEntity(ContratDTO dto, ContratAssurance entity) {
        entity.setId(dto.getId());
        entity.setDateSouscription(dto.getDateSouscription());   // ✅
        entity.setStatut(dto.getStatut());
        entity.setDateValidation(dto.getDateValidation());
        entity.setMontantCotisation(dto.getMontantCotisation());
        entity.setDureeContrat(dto.getDureeContrat());
        entity.setTauxCouverture(dto.getTauxCouverture());
        // client sera setté dans le Service via clientId
    }
}