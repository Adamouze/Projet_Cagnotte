package bforbank.cagnotte.controller;

import bforbank.cagnotte.entities.ApiError;
import bforbank.cagnotte.entities.Transaction;
import bforbank.cagnotte.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for handling transaction related requests.
 */
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    /**
     * Constructor for TransactionController.
     *
     * @param transactionService The service to handle transaction related operations.
     */
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Endpoint for making a transaction.
     *
     * @param clientid The id of the client making the transaction. It connot be null.
     * @param amount The amount of the transaction.
     * @return A ResponseEntity containing the created Transaction object or an ApiError.
     *        Returns HTTP status 201 (Created) if the transaction is successfully created.
     *        Returns HTTP status 400 (Bad Request) with an ApiError object if the amount is null.
     *        Returns HTTP status 404 (Not Found) with an ApiError object if a client with the provided id is not found.
     */
    @PostMapping("/makeTransaction")
    public ResponseEntity<?> makeTransaction(@RequestParam Integer clientid, @RequestParam Float amount) {
        try {
            Transaction transaction = transactionService.makeTransaction(clientid, amount);
            return new ResponseEntity<>(transaction, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ApiError(e.getMessage()),HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ApiError(e.getMessage()),HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint for retrieving all transactions for a client.
     *
     * @param clientid The id of the client whose transactions are to be retrieved. It connot be null.
     * @return A ResponseEntity containing a list of Transaction objects or an ApiError.
     *       Returns HTTP status 200 (OK) if the transactions are successfully retrieved.
     *       Returns HTTP status 404 (Not Found) with an ApiError object if a client with the provided id is not found.
     *       Returns HTTP status 400 (Bad Request) with an ApiError object if the clientid is null.
     */
    @GetMapping("/getTransactions")
    public ResponseEntity<?> getTransactions(@RequestParam Integer clientid) {
        try {
            Iterable<Transaction> transactions = transactionService.getTransactions(clientid);
            return ResponseEntity.ok(transactions);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ApiError(e.getMessage()),HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ApiError(e.getMessage()),HttpStatus.NOT_FOUND);
        }

    }

    /**
     * Endpoint for checking if a client's cagnotte is available.
     *
     * @param clientid The id of the client to check. It connot be null.
     * @return A ResponseEntity containing a boolean indicating if the cagnotte is available or an ApiError.
     *      Returns HTTP status 200 (OK) if the cagnotte is available.
     *      Returns HTTP status 400 (Bad Request) with an ApiError object if the clientid is null.
     *      Returns HTTP status 404 (Not Found) with an ApiError object if a client with the provided id is not found.
     */
    @GetMapping("/isCagnotteAvailable")
    public ResponseEntity<?> isCagnotteAvailable(@RequestParam Integer clientid) {
        try {
            boolean isAvailable = transactionService.isCagnotteAvailable(clientid);
            return ResponseEntity.ok(isAvailable);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ApiError(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(new ApiError(e.getMessage()),HttpStatus.NOT_FOUND);
        }
    }
}