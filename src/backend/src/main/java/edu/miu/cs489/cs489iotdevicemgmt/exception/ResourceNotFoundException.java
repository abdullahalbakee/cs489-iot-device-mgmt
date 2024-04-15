package edu.miu.cs489.cs489iotdevicemgmt.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("Resource not found!");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
