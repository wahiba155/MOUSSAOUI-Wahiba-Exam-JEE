package ma.moussaoui.wahiba.services;

import ma.moussaoui.wahiba.dto.ClientDTO;
import ma.moussaoui.wahiba.dto.ContratDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClientService {

    Page<ClientDTO> getAllClients(int page, int size, String keyword);
    ClientDTO getClientById(Long id);
    List<ClientDTO> searchClientsByNom(String nom);
    ClientDTO saveClient(ClientDTO clientDTO);
    ClientDTO updateClient(ClientDTO clientDTO);
    void deleteClient(Long id);
    List<ContratDTO> getContratsByClientId(Long clientId);
}