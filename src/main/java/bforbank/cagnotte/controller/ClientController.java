package bforbank.cagnotte.controller;

import bforbank.cagnotte.entities.Client;
import bforbank.cagnotte.entities.ApiError;
import bforbank.cagnotte.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling client related requests.
 */
@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    /**
     * Constructor for ClientController.
     *
     * @param clientService The service to handle client related operations.
     */
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Handles the POST request to create a new client.
     *
     * @param name     The name of the client to be created. It cannot be null.
     * @param cagnotte The initial amount in the client's cagnotte. If not provided, it defaults to 0.0f.
     * @return A ResponseEntity containing the created Client object and HTTP status.
     *         Returns HTTP status 201 (Created) if the client is successfully created.
     *         Returns HTTP status 400 (Bad Request) with an ApiError object if the name is null or blank.
     *         Returns HTTP status 409 (Conflict) with an ApiError object if a client with the same name already exists.
     *
     */
    @PostMapping("/createClient")
    public ResponseEntity<?> createClient(@RequestParam String name, @RequestParam(required = false) Float cagnotte) {
        try {
            Client savedClient = clientService.createClient(name, cagnotte);
            return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ApiError(e.getMessage()),HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ApiError(e.getMessage()),HttpStatus.CONFLICT);
        }
    }

    /**
     * Handles the GET request to retrieve a client.
     *
     * @param id   The id of the client to be retrieved. If not provided, the name parameter is used.
     * @param name The name of the client to be retrieved. If not provided, the id parameter is used.
     * @return A ResponseEntity containing the retrieved Client object and HTTP status.
     *         Returns HTTP status 200 (OK) if the client is successfully retrieved.
     *         Returns HTTP status 404 (Not Found) with an ApiError object if a client with the provided id or name is not found.
     *         Returns HTTP status 400 (Bad Request) with an ApiError object if both id and name are null.
     */
    @GetMapping("/getClient")
    public ResponseEntity<?> getClient(@RequestParam(required = false) Integer id, @RequestParam(required = false) String name) {
        try {

            Client client = clientService.getClientByIdOrName(id, name);
            return new ResponseEntity<>(client, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ApiError(e.getMessage()),HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ApiError(e.getMessage()),HttpStatus.NOT_FOUND);
        }
    }
}