package ma.moussaoui.wahiba.services;

import ma.moussaoui.wahiba.dto.ClientDTO;
import ma.moussaoui.wahiba.dto.ContratDTO;
import ma.moussaoui.wahiba.entities.Client;
import ma.moussaoui.wahiba.mapper.ClientMapper;
import ma.moussaoui.wahiba.mapper.ContratMapper;
import ma.moussaoui.wahiba.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    private ClientMapper clientMapper;
    private ContratMapper contratMapper;

    @Override
    public Page<ClientDTO> getAllClients(int page, int size, String keyword) {
        Page<Client> clients = clientRepository
                .findByNomContainingIgnoreCase(keyword, PageRequest.of(page, size));
        return clients.map(clientMapper::toDTO);
    }

    @Override
    public ClientDTO getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvé avec id: " + id));
        return clientMapper.toDTO(client);
    }

    @Override
    public List<ClientDTO> searchClientsByNom(String nom) {
        return clientRepository.findByNomContainingIgnoreCase(nom)
                .stream()
                .map(clientMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        Client client = clientMapper.toEntity(clientDTO);
        return clientMapper.toDTO(clientRepository.save(client));
    }

    @Override
    public ClientDTO updateClient(ClientDTO clientDTO) {
        clientRepository.findById(clientDTO.getId())
                .orElseThrow(() -> new RuntimeException("Client non trouvé avec id: " + clientDTO.getId()));
        Client client = clientMapper.toEntity(clientDTO);
        return clientMapper.toDTO(clientRepository.save(client));
    }

    @Override
    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id))
            throw new RuntimeException("Client non trouvé avec id: " + id);
        clientRepository.deleteById(id);
    }

    @Override
    public List<ContratDTO> getContratsByClientId(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client non trouvé avec id: " + clientId));
        return client.getContrats()
                .stream()
                .map(contratMapper::toDTO)
                .collect(Collectors.toList());
    }
}