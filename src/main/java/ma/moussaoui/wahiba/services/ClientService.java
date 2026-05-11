package ma.moussaoui.wahiba.services;

import ma.moussaoui.wahiba.dto.ClientDTO;

import java.util.List;

public interface ClientService {

    ClientDTO save(ClientDTO clientDTO);

    List<ClientDTO> findAll();

    ClientDTO findById(Long id);

    void delete(Long id);
}