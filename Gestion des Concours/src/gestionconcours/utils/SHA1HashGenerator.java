/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1HashGenerator {
    public static String toSHA1(String input) {
        try {
            // Créer une instance de SHA-1
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(input.getBytes());

            // Convertir les octets en une représentation hexadécimale
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erreur : Algorithme SHA-1 introuvable", e);
        }
    }

    public static void main(String[] args) {
        String text = "123";
        String sha1Hash = toSHA1(text);
        System.out.println("SHA-1 Hash de \"" + text + "\": " + sha1Hash);
    }
}

