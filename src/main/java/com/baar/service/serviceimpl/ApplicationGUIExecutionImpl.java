package com.baar.service.serviceimpl;

import com.baar.service.ApplicationGUIExecution;
import com.baar.util.constant.execution.ExecutionFlag;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ApplicationGUIExecutionImpl implements ApplicationGUIExecution {

  final List<String> userArgs;

  public ApplicationGUIExecutionImpl() {
    userArgs = new ArrayList<>();
  }

  private void displayRootMenu() {

    System.out.println("options:");
    System.out.println("1. encrypt");
    System.out.println("2. decrypt");
  }

  private void userMenu() {

    displayRootMenu();

    final Scanner userInput = new Scanner(System.in);
    final String userRootOption = userInput.nextLine();

    userArgs.add(ExecutionFlag.NO_GUI.getFlagName());

    switch (userRootOption) {
      // encrypt
      case "1":
        final String plainText = userInput.nextLine();
        userArgs.add(ExecutionFlag.ENCRYPT.getFlagName());
        userArgs.add(
            String.format("%s%s", ExecutionFlag.ENCRYPT_VALUE.getFlagName(), plainText));
        break;

      // decrypt
      case "2":
        final String encryptedText = userInput.nextLine();
        userArgs.add(ExecutionFlag.DECRYPT.getFlagName());
        userArgs.add(
            String.format("%s%s", ExecutionFlag.DECRYPT_VALUE.getFlagName(), encryptedText));
        break;

      default:
        System.out.println("OPTIONS MISMATCH!!!");
        break;
    }
  }

  public String[] execute() {
    userMenu();
    return userArgs.stream().toArray(String[]::new);
  }
}
