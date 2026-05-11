package ma.moussaoui.wahiba.services;

import ma.moussaoui.wahiba.dto.PaiementDTO;

import java.util.List;

public interface PaiementService {

    List<PaiementDTO> findAll();
}