package br.com.ucsal.reserva.controllers.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthCustomError {
    private String error;
    @JsonProperty("error_description")
    private String errorDescription;

    public AuthCustomError() {
    }

    public AuthCustomError(String error, String errorDescription) {
        this.error = error;
        this.errorDescription = errorDescription;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
