package bforbank.cagnotte.service;

import bforbank.cagnotte.entities.Client;
import bforbank.cagnotte.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for handling client related operations.
 */
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    /**
     * Constructor for ClientService.
     *
     * @param clientRepository The repository to handle client related database operations.
     */
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Creates a new client.
     *
     * @param name     The name of the client to be created.
     * @param cagnotte The initial amount in the client's cagnotte.
     * @return The created Client object.
     * @throws RuntimeException if a client with the same name already exists.
     * @throws IllegalArgumentException if the name is null or blank.
     */
    public Client createClient(String name, Float cagnotte) {
        if (name == null || name.trim().isEmpty() || name.contains(",")) {
            throw new IllegalArgumentException("Client name cannot be null or blank");
        }
        if (cagnotte == null) {
            cagnotte=0.0f;
        }
        Optional<Client> existingClient = clientRepository.findByName(name);
        if (existingClient.isPresent()) {
            throw new RuntimeException("Client already exists");
        }
        Client client = new Client();
        client.setName(name);
        client.setCagnotte(cagnotte);
        return clientRepository.save(client);
    }

    /**
     * Retrieves a client by id or name.
     *
     * @param id   The id of the client to be retrieved. If not provided, the name parameter is used.
     * @param name The name of the client to be retrieved. If not provided, the id parameter is used.
     * @return The retrieved Client object.
     * @throws RuntimeException if a client with the provided id or name is not found or if both id and name are null.
     * @throws IllegalArgumentException if the client is null or blank.
     */
    public Client getClientByIdOrName(Integer id, String name) {
        Client client = null;
        if(id==null && name==null || id==null && name.trim().isEmpty() || id==null && name.contains(",")){
            throw new IllegalArgumentException("Client cannot be null or blank");
        }
        if (id != null) {
            client = clientRepository.findById(id).orElse(null);
        }
        if (client == null && name != null) {
            client = clientRepository.findByName(name).orElse(null);
        }
        if (client == null) {
            throw new RuntimeException("Client not found");
        }
        return client;
    }
}