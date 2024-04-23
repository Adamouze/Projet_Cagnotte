package bforbank.cagnotte.repository;

import java.util.List;
import bforbank.cagnotte.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for handling transaction related database operations.
 * This interface extends JpaRepository which provides JPA related methods such as save, delete, and find.
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    /**
     * Finds all transactions associated with a specific client.
     *
     * @param clientId The id of the client whose transactions are to be retrieved.
     * @return A list of Transaction objects associated with the client.
     */
    List<Transaction> findByClientId(Integer clientId);

    /**
     * Counts the number of transactions associated with a specific client.
     *
     * @param clientId The id of the client whose transactions are to be counted.
     * @return The number of transactions associated with the client.
     */
    Integer countByClientId(Integer clientId);
}