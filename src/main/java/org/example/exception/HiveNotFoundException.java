package org.example.exception;

public class HiveNotFoundException extends RuntimeException {

    private final Integer hiveId;

    public HiveNotFoundException(Integer hiveId) {
        this.hiveId = hiveId;
    }

    public Integer getHiveId() {
        return hiveId;
    }
}
