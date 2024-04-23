package bforbank.cagnotte.entities;

import jakarta.persistence.*;

/**
 * Entity class representing a transaction in the system.
 */
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Unique identifier for the transaction
    private Integer id;

    // Identifier of the client associated with the transaction
    private Integer clientId;

    // Amount of the transaction
    private Float amount;

    // Getter and setter methods

    /**
     * Gets the unique identifier of the transaction.
     *
     * @return The unique identifier of the transaction.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the transaction.
     *
     * @param id The unique identifier to set.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the identifier of the client associated with the transaction.
     *
     * @return The identifier of the client.
     */
    public Integer getClientId() {
        return clientId;
    }

    /**
     * Sets the identifier of the client associated with the transaction.
     *
     * @param clientId The client identifier to set.
     */
    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    /**
     * Gets the amount of the transaction.
     *
     * @return The amount of the transaction.
     */
    public Float getAmount() {
        return amount;
    }

    /**
     * Sets the amount of the transaction.
     *
     * @param amount The amount to set.
     */
    public void setAmount(Float amount) {
        this.amount = amount;
    }
}