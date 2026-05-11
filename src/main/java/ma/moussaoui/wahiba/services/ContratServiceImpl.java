package ma.moussaoui.wahiba.services;

import ma.moussaoui.wahiba.dto.*;
import ma.moussaoui.wahiba.entities.*;
import ma.moussaoui.wahiba.enums.StatutContrat;
import ma.moussaoui.wahiba.mapper.ContratMapper;
import ma.moussaoui.wahiba.repositories.ClientRepository;
import ma.moussaoui.wahiba.repositories.ContratAssuranceRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ContratServiceImpl implements ContratService {

    private ContratAssuranceRepository contratRepository;
    private ClientRepository clientRepository;
    private ContratMapper contratMapper;

    // ─── helper : récupérer le client ─────────────────────────────────────────
    private Client getClient(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client non trouvé id: " + clientId));
    }

    @Override
    public Page<ContratDTO> getAllContrats(int page, int size) {
        return contratRepository.findAll(PageRequest.of(page, size))
                .map(contratMapper::toDTO);
    }

    @Override
    public ContratDTO getContratById(Long id) {
        ContratAssurance c = contratRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrat non trouvé id: " + id));
        return contratMapper.toDTO(c);
    }

    @Override
    public List<ContratDTO> getContratsByStatut(StatutContrat statut) {
        return contratRepository.findByStatut(statut)
                .stream().map(contratMapper::toDTO).collect(Collectors.toList());
    }

    // ─── Automobile ────────────────────────────────────────────────────────────
    @Override
    public List<ContratAutomobileDTO> getAllContratsAutomobile() {
        return contratRepository.findAll().stream()
                .filter(c -> c instanceof ContratAutomobile)
                .map(c -> contratMapper.toAutomobileDTO((ContratAutomobile) c))
                .collect(Collectors.toList());
    }

    @Override
    public ContratAutomobileDTO saveContratAutomobile(ContratAutomobileDTO dto) {
        ContratAutomobile entity = contratMapper.toAutomobileEntity(dto);
        entity.setClient(getClient(dto.getClientId()));
        return contratMapper.toAutomobileDTO(
                (ContratAutomobile) contratRepository.save(entity));
    }

    @Override
    public ContratAutomobileDTO updateContratAutomobile(ContratAutomobileDTO dto) {
        contratRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Contrat non trouvé id: " + dto.getId()));
        ContratAutomobile entity = contratMapper.toAutomobileEntity(dto);
        entity.setClient(getClient(dto.getClientId()));
        return contratMapper.toAutomobileDTO(
                (ContratAutomobile) contratRepository.save(entity));
    }

    // ─── Habitation ────────────────────────────────────────────────────────────
    @Override
    public List<ContratHabitationDTO> getAllContratsHabitation() {
        return contratRepository.findAll().stream()
                .filter(c -> c instanceof ContratHabitation)
                .map(c -> contratMapper.toHabitationDTO((ContratHabitation) c))
                .collect(Collectors.toList());
    }

    @Override
    public ContratHabitationDTO saveContratHabitation(ContratHabitationDTO dto) {
        ContratHabitation entity = contratMapper.toHabitationEntity(dto);
        entity.setClient(getClient(dto.getClientId()));
        return contratMapper.toHabitationDTO(
                (ContratHabitation) contratRepository.save(entity));
    }

    @Override
    public ContratHabitationDTO updateContratHabitation(ContratHabitationDTO dto) {
        contratRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Contrat non trouvé id: " + dto.getId()));
        ContratHabitation entity = contratMapper.toHabitationEntity(dto);
        entity.setClient(getClient(dto.getClientId()));
        return contratMapper.toHabitationDTO(
                (ContratHabitation) contratRepository.save(entity));
    }

    // ─── Santé ─────────────────────────────────────────────────────────────────
    @Override
    public List<ContratSanteDTO> getAllContratsSante() {
        return contratRepository.findAll().stream()
                .filter(c -> c instanceof ContratSante)
                .map(c -> contratMapper.toSanteDTO((ContratSante) c))
                .collect(Collectors.toList());
    }

    @Override
    public ContratSanteDTO saveContratSante(ContratSanteDTO dto) {
        ContratSante entity = contratMapper.toSanteEntity(dto);
        entity.setClient(getClient(dto.getClientId()));
        return contratMapper.toSanteDTO(
                (ContratSante) contratRepository.save(entity));
    }

    @Override
    public ContratSanteDTO updateContratSante(ContratSanteDTO dto) {
        contratRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Contrat non trouvé id: " + dto.getId()));
        ContratSante entity = contratMapper.toSanteEntity(dto);
        entity.setClient(getClient(dto.getClientId()));
        return contratMapper.toSanteDTO(
                (ContratSante) contratRepository.save(entity));
    }

    // ─── Actions métier ────────────────────────────────────────────────────────
    @Override
    public ContratDTO validerContrat(Long id) {
        ContratAssurance c = contratRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrat non trouvé id: " + id));
        c.setStatut(StatutContrat.VALIDE);
        c.setDateValidation(new Date());
        return contratMapper.toDTO(contratRepository.save(c));
    }

    @Override
    public ContratDTO resilierContrat(Long id) {
        ContratAssurance c = contratRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrat non trouvé id: " + id));
        c.setStatut(StatutContrat.RESILIE);
        return contratMapper.toDTO(contratRepository.save(c));
    }

    @Override
    public void deleteContrat(Long id) {
        if (!contratRepository.existsById(id))
            throw new RuntimeException("Contrat non trouvé id: " + id);
        contratRepository.deleteById(id);
    }
}