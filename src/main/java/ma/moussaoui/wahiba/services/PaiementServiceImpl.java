package ma.moussaoui.wahiba.services;

import ma.moussaoui.wahiba.dto.PaiementDTO;
import ma.moussaoui.wahiba.entities.ContratAssurance;
import ma.moussaoui.wahiba.entities.Paiement;
import ma.moussaoui.wahiba.enums.TypePaiement;
import ma.moussaoui.wahiba.mapper.PaiementMapper;
import ma.moussaoui.wahiba.repositories.ContratAssuranceRepository;
import ma.moussaoui.wahiba.repositories.PaiementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class PaiementServiceImpl implements PaiementService {

    private PaiementRepository paiementRepository;
    private ContratAssuranceRepository contratRepository;
    private PaiementMapper paiementMapper;

    @Override
    public List<PaiementDTO> getAllPaiements() {
        return paiementRepository.findAll()
                .stream()
                .map(paiementMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PaiementDTO getPaiementById(Long id) {
        Paiement paiement = paiementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paiement non trouvé avec id: " + id));
        return paiementMapper.toDTO(paiement);
    }

    @Override
    public List<PaiementDTO> getPaiementsByContratId(Long contratId) {
        return paiementRepository.findByContratId(contratId)  // ✅
                .stream()
                .map(paiementMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaiementDTO> getPaiementsByType(TypePaiement type) {
        return paiementRepository.findByTypePaiement(type)
                .stream()
                .map(paiementMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Double getTotalPaiementsParContrat(Long contratId) {
        Double total = paiementRepository.sumMontantByContratId(contratId);
        return total != null ? total : 0.0;
    }

    @Override
    public PaiementDTO savePaiement(PaiementDTO paiementDTO) {
        // Vérifier que le contrat existe
        ContratAssurance contrat = contratRepository.findById(paiementDTO.getContratId())
                .orElseThrow(() -> new RuntimeException("Contrat non trouvé avec id: " + paiementDTO.getContratId()));

        Paiement paiement = paiementMapper.toEntity(paiementDTO);
        paiement.setContrat(contrat);
        Paiement saved = paiementRepository.save(paiement);
        return paiementMapper.toDTO(saved);
    }

    @Override
    public PaiementDTO updatePaiement(PaiementDTO paiementDTO) {
        // Vérifier que le paiement existe
        paiementRepository.findById(paiementDTO.getId())
                .orElseThrow(() -> new RuntimeException("Paiement non trouvé avec id: " + paiementDTO.getId()));

        Paiement paiement = paiementMapper.toEntity(paiementDTO);

        if (paiementDTO.getContratId() != null) {
            ContratAssurance contrat = contratRepository.findById(paiementDTO.getContratId())
                    .orElseThrow(() -> new RuntimeException("Contrat non trouvé"));
            paiement.setContrat(contrat);
        }

        Paiement updated = paiementRepository.save(paiement);
        return paiementMapper.toDTO(updated);
    }

    @Override
    public void deletePaiement(Long id) {
        if (!paiementRepository.existsById(id))
            throw new RuntimeException("Paiement non trouvé avec id: " + id);
        paiementRepository.deleteById(id);
    }
}