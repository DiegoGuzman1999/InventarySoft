package com.inventarysoft.inventoryservice.shared.exception;

public class ConflictException extends RuntimeException {
  public ConflictException(String message) {
    super(message);
  }
}

