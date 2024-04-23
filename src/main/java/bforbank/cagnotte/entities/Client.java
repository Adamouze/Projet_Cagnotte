package bforbank.cagnotte.entities;

import jakarta.persistence.*;

/**
 * Entity class representing a client in the system.
 */
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Unique identifier for the client
    private Integer id;

    // Name of the client
    private String name;

    // Amount in the client's cagnotte
    private Float cagnotte;

    // Getter and setter methods

    /**
     * Gets the unique identifier of the client.
     *
     * @return The unique identifier of the client.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the client.
     *
     * @param id The unique identifier to set.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the name of the client.
     *
     * @return The name of the client.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the client.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the amount in the client's cagnotte.
     *
     * @return The amount in the client's cagnotte.
     */
    public Float getCagnotte() {
        return cagnotte;
    }

    /**
     * Sets the amount in the client's cagnotte.
     *
     * @param cagnotte The amount to set in the client's cagnotte.
     */
    public void setCagnotte(Float cagnotte) {
        this.cagnotte = cagnotte;
    }
}