package com.baar;


import com.baar.encryptdecrypt.AES256;

import java.util.Scanner;


public class App {
    
    private static String keyString = "DCB";
    
    public void run() {
        
        userMenu();
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
        
        
        switch (userRootOption) {
            case "1":
                final String plainText = userInput.nextLine();
                System.out.println(encrypt(plainText));
                break;
            case "2":
                final String encryptedText = userInput.nextLine();
                System.out.println(decrypt(encryptedText));
                break;
            default:
                System.out.println("OPTIONS MISMATCH!!!");
                break;
        }
    }
    
    private String encrypt(final String text) {
        
        try {
            return AES256.encrypt(text, keyString);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return null;
    }
    
    private String decrypt(final String encryptedText) {
        
        try {
            return AES256.decrypt(encryptedText, keyString);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return null;
    }
    
    public static void main(String[] args) throws Exception {
        
        App app = new App();
        app.run();
    }
    
}
