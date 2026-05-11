package ma.moussaoui.wahiba.services;

import ma.moussaoui.wahiba.dto.ContratDTO;
import ma.moussaoui.wahiba.mapper.ContratMapper;
import ma.moussaoui.wahiba.repositories.ContratAssuranceRepository;
import ma.moussaoui.wahiba.services.ContratService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContratServiceImpl implements ContratService {

    private final ContratAssuranceRepository repository;

    public ContratServiceImpl(ContratAssuranceRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ContratDTO> findAll() {

        return repository.findAll()
                .stream()
                .map(ContratMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ContratDTO findById(Long id) {

        return ContratMapper.toDTO(
                repository.findById(id).orElseThrow()
        );
    }
}