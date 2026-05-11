package ma.moussaoui.wahiba.services;

import ma.moussaoui.wahiba.dto.PaiementDTO;
import ma.moussaoui.wahiba.mapper.PaiementMapper;
import ma.moussaoui.wahiba.repositories.PaiementRepository;
import ma.moussaoui.wahiba.services.PaiementService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaiementServiceImpl implements PaiementService {

    private final PaiementRepository repository;

    public PaiementServiceImpl(PaiementRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PaiementDTO> findAll() {

        return repository.findAll()
                .stream()
                .map(PaiementMapper::toDTO)
                .collect(Collectors.toList());
    }
}