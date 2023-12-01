package org.example.exception;

public class BeeNotFoundException extends RuntimeException {
    private final Integer beeId;

    public BeeNotFoundException(Integer beeId) {
        this.beeId = beeId;
    }

    public Integer getBeeId() {
        return beeId;
    }
}
