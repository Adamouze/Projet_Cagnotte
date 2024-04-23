package bforbank.cagnotte.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import bforbank.cagnotte.entities.Client;

import java.util.Optional;

/**
 * Repository interface for handling client related database operations.
 * This interface extends JpaRepository which provides JPA related methods such as save, delete, and find.
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    /**
     * Finds a client by their name.
     *
     * @param name The name of the client to be retrieved.
     * @return An Optional containing the Client object if found, or an empty Optional if not found.
     */
    Optional<Client> findByName(String name);
}