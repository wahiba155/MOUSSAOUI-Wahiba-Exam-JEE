package ma.moussaoui.wahiba.services;

import ma.moussaoui.wahiba.dto.*;
import ma.moussaoui.wahiba.enums.StatutContrat;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ContratService {
    // Génériques
    Page<ContratDTO> getAllContrats(int page, int size);
    ContratDTO getContratById(Long id);
    List<ContratDTO> getContratsByStatut(StatutContrat statut);
    void deleteContrat(Long id);
    ContratDTO validerContrat(Long id);
    ContratDTO resilierContrat(Long id);

    // Automobile
    List<ContratAutomobileDTO> getAllContratsAutomobile();
    ContratAutomobileDTO saveContratAutomobile(ContratAutomobileDTO dto);
    ContratAutomobileDTO updateContratAutomobile(ContratAutomobileDTO dto);

    // Habitation
    List<ContratHabitationDTO> getAllContratsHabitation();
    ContratHabitationDTO saveContratHabitation(ContratHabitationDTO dto);
    ContratHabitationDTO updateContratHabitation(ContratHabitationDTO dto);

    // Santé
    List<ContratSanteDTO> getAllContratsSante();
    ContratSanteDTO saveContratSante(ContratSanteDTO dto);
    ContratSanteDTO updateContratSante(ContratSanteDTO dto);
}