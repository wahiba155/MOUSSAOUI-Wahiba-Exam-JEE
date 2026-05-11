package ma.moussaoui.wahiba.services;

import ma.moussaoui.wahiba.dto.ClientDTO;
import ma.moussaoui.wahiba.entities.Client;
import ma.moussaoui.wahiba.mapper.ClientMapper;
import ma.moussaoui.wahiba.repositories.ClientRepository;
import ma.moussaoui.wahiba.services.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDTO save(ClientDTO clientDTO) {

        Client client = ClientMapper.toEntity(clientDTO);

        return ClientMapper.toDTO(clientRepository.save(client));
    }

    @Override
    public List<ClientDTO> findAll() {

        return clientRepository.findAll()
                .stream()
                .map(ClientMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO findById(Long id) {

        Client client = clientRepository.findById(id)
                .orElseThrow();

        return ClientMapper.toDTO(client);
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}