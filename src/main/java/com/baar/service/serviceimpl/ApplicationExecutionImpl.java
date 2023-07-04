package com.baar.service.serviceimpl;

import com.baar.encryptdecrypt.AES256;
import com.baar.service.ApplicationExecution;
import com.baar.util.constant.execution.EncryptionSecret;
import com.baar.util.constant.execution.ExecutionFlag;

public class ApplicationExecutionImpl implements ApplicationExecution {

  final String userArgs[];

  public ApplicationExecutionImpl(final String[] args) {
    this.userArgs = args;
  }

  private String getEncryptedValue(String encryptValue) {
    try {
      return AES256.encrypt(encryptValue, EncryptionSecret.SECRET_KEY);
    } catch (Exception e) {
      System.out.println(e);
    }

    return "-1";
  }

  private String getDecryptedValue(String decryptValue) {
    try {
      return AES256.decrypt(decryptValue, EncryptionSecret.SECRET_KEY);
    } catch (Exception e) {
      System.out.println(e);
    }

    return "-1";
  }

  private String extractStringFromArgument(String[] args, int start) {
    final StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append(args[start].substring(args[start].indexOf("\"") + 1)).append(" ");
    for (int i = start + 1; i < args.length; i++) {
      if (args[i].endsWith("\"")) {
        stringBuilder.append(args[i].substring(0, args[i].length() - 1));
        break;
      } else {
        stringBuilder.append(args[i]).append(" ");
      }
    }

    return stringBuilder.toString();
  }

  private String noGUIExecuteParseFlags(final String[] args) {

    if (args[0].equals(ExecutionFlag.NO_GUI.getFlagName())) {
      if (args[1].equals(ExecutionFlag.ENCRYPT.getFlagName())) {
        if (args[2].startsWith(ExecutionFlag.ENCRYPT_VALUE.getFlagName())) {
          final String encryptValue = args[2].split(ExecutionFlag.ENCRYPT_VALUE.getFlagName())[1];
//          final String encryptValue = extractStringFromArgument(args, 2);
          return getEncryptedValue(encryptValue);
        }

      } else {
        if (args[1].equals(ExecutionFlag.DECRYPT.getFlagName())) {
          if (args[2].startsWith(ExecutionFlag.DECRYPT_VALUE.getFlagName())) {
            final String decryptValue = args[2].split(ExecutionFlag.DECRYPT_VALUE.getFlagName())[1];
//            final String decryptValue = extractStringFromArgument(args, 2);
            return getDecryptedValue(decryptValue);
          }
        }
      }
    }

    return "-1";
  }


  @Override
  public String execute() {
    String opValue;
    if (userArgs[0].equals(ExecutionFlag.GUI.getFlagName())) {
      final String[] convertedUserArgs = new ApplicationGUIExecutionImpl().execute();
//      System.out.println(Arrays.toString(convertedUserArgs));
      opValue = noGUIExecuteParseFlags(convertedUserArgs);

    } else {

      opValue = noGUIExecuteParseFlags(userArgs);
    }

    System.out.println(opValue);
    return opValue;
  }
}
