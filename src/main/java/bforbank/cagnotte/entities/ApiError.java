package bforbank.cagnotte.entities;

/**
 * Entity class representing an API error.
 */
public class ApiError {
    // The error message
    private String error;

    /**
     * Constructor for ApiError.
     *
     * @param error The error message.
     */
    public ApiError(String error) {
        this.error = error;
    }

    // Getter and setter methods

    /**
     * Gets the error message.
     *
     * @return The error message.
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the error message.
     *
     * @param error The error message to set.
     */
    public void setError(String error) {
        this.error = error;
    }
}