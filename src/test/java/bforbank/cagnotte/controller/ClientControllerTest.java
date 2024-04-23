package bforbank.cagnotte.controller;

import bforbank.cagnotte.entities.Client;
import bforbank.cagnotte.entities.ApiError;
import bforbank.cagnotte.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * This class contains unit tests for the ClientController class.
 * Each method tests a specific scenario for a specific method in the ClientController class.
 */
public class ClientControllerTest {

    private ClientService clientService;
    private ClientController clientController;

    /**
     * This method sets up the mocks and the class to be tested.
     * It is executed before each test.
     */
    @BeforeEach
    public void setup() {
        clientService = Mockito.mock(ClientService.class);
        clientController = new ClientController(clientService);
    }

    /**
     * This test checks the scenario where a client is created successfully.
     */
    @Test
    public void createClientSuccessfully() {
        String name = "Test";
        Float cagnotte = 100.0f;
        Client client = new Client();
        client.setName(name);
        client.setCagnotte(cagnotte);

        when(clientService.createClient(name, cagnotte)).thenReturn(client);

        ResponseEntity<?> response = clientController.createClient(name, cagnotte);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(client, response.getBody());
    }

    /**
     * This test checks the scenario where a client creation fails because a client with the same name already exists.
     */
    @Test
    public void createClientWithExistingName() {
        String name = "Test";
        Float cagnotte = 100.0f;

        when(clientService.createClient(name, cagnotte)).thenThrow(new RuntimeException("Client with the same name already exists"));

        ResponseEntity<?> response = clientController.createClient(name, cagnotte);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Client with the same name already exists", ((ApiError) Objects.requireNonNull(response.getBody())).getError());
    }

    /**
     * This test checks the scenario where a client creation fails because the client name is blank.
     */
    @Test
    public void createClientWithBlankName() {
        String name = "";
        Float cagnotte = 100.0f;

        when(clientService.createClient(name, cagnotte)).thenThrow(new IllegalArgumentException("Client name cannot be blank"));

        ResponseEntity<?> response = clientController.createClient(name, cagnotte);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Client name cannot be blank", ((ApiError) Objects.requireNonNull(response.getBody())).getError());
    }

    /**
     * This test checks the scenario where a client is retrieved successfully by id.
     */
    @Test
    public void getClientByIdSuccessfully() {
        Integer id = 1;
        Client client = new Client();
        client.setId(id);

        when(clientService.getClientByIdOrName(id, null)).thenReturn(client);

        ResponseEntity<?> response = clientController.getClient(id, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(client, response.getBody());
    }

    /**
     * This test checks the scenario where a client is retrieved successfully by name.
     */
    @Test
    public void getClientByNameSuccessfully() {
        String name = "Test";
        Client client = new Client();
        client.setName(name);

        when(clientService.getClientByIdOrName(null, name)).thenReturn(client);

        ResponseEntity<?> response = clientController.getClient(null, name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(client, response.getBody());
    }

    /**
     * This test checks the scenario where a client retrieval fails because the client with the given id does not exist.
     */
    @Test
    public void getClientWithNonExistingId() {
        Integer id = 1;

        when(clientService.getClientByIdOrName(id, null)).thenThrow(new RuntimeException("Client not found"));

        ResponseEntity<?> response = clientController.getClient(id, null);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Client not found", ((ApiError) Objects.requireNonNull(response.getBody())).getError());
    }

    /**
     * This test checks the scenario where a client retrieval fails because the client with the given name does not exist.
     */
    @Test
    public void getClientWithNonExistingName() {
        String name = "Test";

        when(clientService.getClientByIdOrName(null, name)).thenThrow(new RuntimeException("Client not found"));

        ResponseEntity<?> response = clientController.getClient(null, name);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Client not found", ((ApiError) Objects.requireNonNull(response.getBody())).getError());
    }

    /**
     * This test checks the scenario where a client retrieval fails because both id and name are null.
     */
    @Test
    public void getClientWithNullIdAndName() {
        Integer id = null;
        String name = null;

        when(clientService.getClientByIdOrName(id, name)).thenThrow(new IllegalArgumentException("Client id and name cannot be null"));

        ResponseEntity<?> response = clientController.getClient(id, name);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Client id and name cannot be null", ((ApiError) Objects.requireNonNull(response.getBody())).getError());
    }

    /**
     * This test checks the scenario where a client retrieval fails because the name is blank.
     */
    @Test
    public void getClientWithBlankName() {
        Integer id = null;
        String name = "";

        when(clientService.getClientByIdOrName(id, name)).thenThrow(new IllegalArgumentException("Client id and name cannot be null or blank"));

        ResponseEntity<?> response = clientController.getClient(id, name);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Client id and name cannot be null or blank", ((ApiError) Objects.requireNonNull(response.getBody())).getError());
    }

}