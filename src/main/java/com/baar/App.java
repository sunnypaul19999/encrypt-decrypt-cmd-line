package com.baar;


import com.baar.service.ApplicationExecution;
import com.baar.service.serviceimpl.ApplicationExecutionImpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class App {

  private static void test() {

    final List<String> encrypt = new ArrayList<>();
    Collections.addAll(encrypt, "--no-gui", "--encrypt");
    final List<String> encryptArg2 = Arrays.asList(
            String.format("--encrypt-value=\"%s\"", "this is my private string").split(" ")).stream()
        .map(String::toString).collect(
            Collectors.toList());
    encrypt.addAll(encryptArg2);
    System.out.println(encrypt);
    final ApplicationExecution encryptExecution = new ApplicationExecutionImpl(
        encrypt.stream().toArray(String[]::new));
    final String encryptedValue = encryptExecution.execute();

    final List<String> decrypt = new ArrayList<>();
    Collections.addAll(decrypt, "--no-gui", "--decrypt");
    final List<String> decryptArg2 = Arrays.asList(
            String.format("--decrypt-value=\"%s\"", encryptedValue).split(" ")).stream()
        .map(String::toString).collect(
            Collectors.toList());
    decrypt.addAll(decryptArg2);
    System.out.println(decrypt);
    final ApplicationExecution decryptExecution = new ApplicationExecutionImpl(
        decrypt.stream().toArray(String[]::new));
    decryptExecution.execute();

    String[] gui = new String[]{"--gui"};
    final ApplicationExecution guiExecution = new ApplicationExecutionImpl(gui);
    guiExecution.execute();
  }

  public static void main(String[] args) throws Exception {
//    test();
//    System.out.println(Arrays.toString(args));
//    System.out.println(args[2].split(ExecutionFlag.ENCRYPT_VALUE.getFlagName())[1]);
    final ApplicationExecution applicationExecution = new ApplicationExecutionImpl(args);
    applicationExecution.execute();
  }
}
