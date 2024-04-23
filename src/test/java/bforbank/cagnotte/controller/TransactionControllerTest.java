package bforbank.cagnotte.controller;

import bforbank.cagnotte.entities.ApiError;
import bforbank.cagnotte.entities.Client;
import bforbank.cagnotte.repository.TransactionRepository;
import bforbank.cagnotte.service.ClientService;
import bforbank.cagnotte.entities.Transaction;
import bforbank.cagnotte.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * This class contains unit tests for the TransactionController class.
 * Each method tests a specific scenario for a specific method in the TransactionController class.
 */
public class TransactionControllerTest {

    private ClientService clientService;

    private TransactionRepository transactionRepository;
    private TransactionService transactionService;
    private TransactionController transactionController;

    /**
     * This method sets up the mocks and the class to be tested.
     * It is executed before each test.
     */
    @BeforeEach
    public void setup() {
        clientService = Mockito.mock(ClientService.class);
        transactionRepository = Mockito.mock(TransactionRepository.class);
        transactionService = Mockito.mock(TransactionService.class);
        transactionController = new TransactionController(transactionService);
    }

    /**
     * This test checks the scenario where a transaction is made successfully.
     */
    @Test
    public void makeTransactionSuccessfully() {
        Integer clientId = 1;
        Float amount = 100.0f;
        Transaction transaction = new Transaction();
        transaction.setClientId(clientId);
        transaction.setAmount(amount);

        when(transactionService.makeTransaction(clientId, amount)).thenReturn(transaction);

        ResponseEntity<?> response = transactionController.makeTransaction(clientId, amount);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(transaction, response.getBody());
    }

    /**
     * This test checks the scenario where a transaction fails because the amount is invalid.
     */
    @Test
    public void makeTransactionWithInvalidAmount() {
        Integer clientId = 1;
        Float amount = -100.0f;

        when(transactionService.makeTransaction(clientId, amount)).thenThrow(new IllegalArgumentException("Bad request"));

        ResponseEntity<?> response = transactionController.makeTransaction(clientId, amount);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Bad request", ((ApiError) Objects.requireNonNull(response.getBody())).getError());
    }

    /**
     * This test checks the scenario where a transaction fails because the client id is null.
     */
    @Test
    public void makeTransactionWithNullClientId() {
        Integer clientId = null;
        Float amount = 100.0f;

        when(transactionService.makeTransaction(clientId, amount)).thenThrow(new IllegalArgumentException("Clientid cannot be null"));

        ResponseEntity<?> response = transactionController.makeTransaction(clientId, amount);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Clientid cannot be null", ((ApiError) Objects.requireNonNull(response.getBody())).getError());
    }

    /**
     * This test checks the scenario where a transaction fails because the amount is null.
     */
    @Test
    public void makeTransactionWithNullAmount() {
        Integer clientId = 1;
        Float amount = 100.0f;

        when(transactionService.makeTransaction(clientId, amount)).thenThrow(new IllegalArgumentException("Amount cannot be null"));

        ResponseEntity<?> response = transactionController.makeTransaction(clientId, amount);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Amount cannot be null", ((ApiError) Objects.requireNonNull(response.getBody())).getError());
    }

    /**
     * This test checks the scenario where transactions are retrieved successfully.
     */
    @Test
    public void getTransactionsSuccessfully() {
        Integer clientId = 1;
        Transaction transaction1 = new Transaction();
        transaction1.setClientId(clientId);
        transaction1.setAmount(100.0f);
        Transaction transaction2 = new Transaction();
        transaction2.setClientId(clientId);
        transaction2.setAmount(200.0f);
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2);

        when(transactionService.getTransactions(clientId)).thenReturn(transactions);

        ResponseEntity<?> response = transactionController.getTransactions(clientId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transactions, response.getBody());
    }

    /**
     * This test checks the scenario where transactions retrieval fails because the client id is invalid.
     */
    @Test
    public void getTransactionsWithInvalidClientId() {
        Integer clientId = -1;

        when(transactionService.getTransactions(clientId)).thenThrow(new IllegalArgumentException("Bad request"));

        ResponseEntity<?> response = transactionController.getTransactions(clientId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Bad request", ((ApiError) Objects.requireNonNull(response.getBody())).getError());
    }

    /**
     * This test checks the scenario where transactions retrieval fails because the client id is null.
     */
    @Test
    public void getTransactionsWithNullClientId() {
        Integer clientId = null;

        when(transactionService.getTransactions(clientId)).thenThrow(new IllegalArgumentException("Clientid cannot be null"));

        ResponseEntity<?> response = transactionController.getTransactions(clientId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Clientid cannot be null", ((ApiError) Objects.requireNonNull(response.getBody())).getError());
    }


    /**
     * This test checks the scenario where the availability of a cagnotte fails to be checked because the client id is null.
     */
    @Test
    public void isCagnotteAvailableWithNullClientId() {
        Integer clientId = null;

        when(transactionService.isCagnotteAvailable(clientId)).thenThrow(new IllegalArgumentException("Clientid cannot be null"));

        ResponseEntity<?> response = transactionController.isCagnotteAvailable(clientId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Clientid cannot be null", ((ApiError) Objects.requireNonNull(response.getBody())).getError());
    }

    /**
     * This test checks the scenario where the availability of a cagnotte is checked successfully.
     */
    @Test
    public void isCagnotteAvailableSuccessfully() {
        Integer clientId = 1;

        when(transactionService.isCagnotteAvailable(clientId)).thenReturn(true);

        ResponseEntity<?> response = transactionController.isCagnotteAvailable(clientId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
    }

    /**
     * This test checks the scenario where the availability of a cagnotte fails to be checked because the client id is invalid.
     */
    @Test
    public void isCagnotteAvailableWithInvalidClientId() {
        Integer clientId = -1;

        when(transactionService.isCagnotteAvailable(clientId)).thenThrow(new IllegalArgumentException("Bad request"));

        ResponseEntity<?> response = transactionController.isCagnotteAvailable(clientId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Bad request", ((ApiError) Objects.requireNonNull(response.getBody())).getError());
    }

    /**
     * This test checks the scenario where the availability of a cagnotte is checked with less than three transactions.
     * The cagnotte should not be available in this case.
     */
    @Test
    public void testCagnotteAvailableWithLessThanThreeTransactions() {
        Integer clientId = 1;
        String name = "Test1";
        Client client = new Client();
        client.setId(clientId);
        client.setName(name);
        client.setCagnotte(10.0f);
        when(clientService.getClientByIdOrName(clientId,name)).thenReturn(client);
        when(transactionRepository.countByClientId(clientId)).thenReturn(2);

        ResponseEntity<?> response = transactionController.isCagnotteAvailable(clientId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(false, response.getBody());
    }

    /**
     * This test checks the scenario where the availability of a cagnotte is checked with less than ten in cagnotte.
     * The cagnotte should not be available in this case.
     */
    @Test
    public void testCagnotteAvailableWithLessThanTenInCagnotte() {
        Integer clientId = 1;
        String name = "Test1";
        Client client = new Client();
        client.setId(clientId);
        client.setName(name);
        client.setCagnotte(9.0f);
        when(clientService.getClientByIdOrName(clientId,name)).thenReturn(client);
        when(transactionRepository.countByClientId(clientId)).thenReturn(3);

        ResponseEntity<?> response = transactionController.isCagnotteAvailable(clientId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(false, response.getBody());
    }
}