package com.baar.util.constant.execution;

public enum ExecutionFlag {
  NO_GUI("--no-gui"), GUI("--gui"),
  ENCRYPT("--encrypt"), DECRYPT("--decrypt"),
  ENCRYPT_VALUE("--encrypt-value="), DECRYPT_VALUE("--decrypt-value=");

  private final String flagName;

  private ExecutionFlag(final String flagName) {
    this.flagName = flagName;
  }

  public String getFlagName() {
    return flagName;
  }

  @Override
  public String toString() {
    return flagName;
  }
}