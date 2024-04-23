package bforbank.cagnotte.service;

import bforbank.cagnotte.entities.Client;
import bforbank.cagnotte.entities.Transaction;
import bforbank.cagnotte.repository.ClientRepository;
import bforbank.cagnotte.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for handling transaction related operations.
 */
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final ClientRepository clientRepository;

    /**
     * Constructor for TransactionService.
     *
     * @param transactionRepository The repository to handle transaction related database operations.
     * @param clientRepository The repository to handle client related database operations.
     */
    public TransactionService(TransactionRepository transactionRepository, ClientRepository clientRepository) {
        this.transactionRepository = transactionRepository;
        this.clientRepository = clientRepository;
    }

    /**
     * Makes a transaction for a client.
     *
     * @param clientid The id of the client making the transaction. Cannot be null.
     * @param amount The amount of the transaction. Cannot be null.
     * @return The created Transaction object.
     * @throws IllegalArgumentException if the amount is null.
     * @throws RuntimeException if a client with the provided id is not found.
     */
    public Transaction makeTransaction(Integer clientid, Float amount) {
        if (clientid == null) {
            throw new IllegalArgumentException("Clientid cannot be null");
        }
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
        Client client = clientRepository.findById(clientid).orElseThrow(() -> new RuntimeException("Client not found"));
        client.setCagnotte(client.getCagnotte() + amount);
        clientRepository.save(client);
        Transaction transaction = new Transaction();
        transaction.setClientId(clientid);
        transaction.setAmount(amount);
        return transactionRepository.save(transaction);
    }

    /**
     * Retrieves all transactions for a client.
     *
     * @param clientid The id of the client whose transactions are to be retrieved. Cannot be null.
     * @return A list of Transaction objects.
     * @throws RuntimeException if a client with the provided id is not found.
     */
    public List<Transaction> getTransactions(Integer clientid) {
        if (clientid == null) {
            throw new IllegalArgumentException("Clientid cannot be null");
        }
        clientRepository.findById(clientid).orElseThrow(() -> new RuntimeException("Client not found"));
        return  transactionRepository.findByClientId(clientid);
    }

    /**
     * Checks if a client's cagnotte is available.
     *
     * @param clientId The id of the client to check. Cannot be null.
     * @return true if the client has made at least 3 transactions and their cagnotte is at least 10, false otherwise.
     * @throws RuntimeException if a client with the provided id is not found.
     */
    public boolean isCagnotteAvailable(Integer clientId) {
        if (clientId == null) {
            throw new IllegalArgumentException("Clientid cannot be null");
        }
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client not found"));
        long transactionCount = transactionRepository.countByClientId(clientId);
        return transactionCount >= 3 && client.getCagnotte() >= 10;
    }
}