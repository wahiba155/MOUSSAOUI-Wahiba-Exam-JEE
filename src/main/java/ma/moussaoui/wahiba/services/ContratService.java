package ma.moussaoui.wahiba.services;

import ma.moussaoui.wahiba.dto.ContratDTO;

import java.util.List;

public interface ContratService {

    List<ContratDTO> findAll();

    ContratDTO findById(Long id);
}