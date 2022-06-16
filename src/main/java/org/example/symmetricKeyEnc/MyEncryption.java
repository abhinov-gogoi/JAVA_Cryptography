package org.example.symmetricKeyEnc;

public class MyEncryption {

    public static void main(String[] args) {
        String confidential_message = "I am IronMan";
        System.out.println("original_message: "+confidential_message);
        int KEY = 8;

        String encrypted_message = encrypt(KEY, confidential_message);
        System.out.println("encrypted_message: "+encrypted_message);

        String decrypted_message = decrypt(KEY, encrypted_message);
        System.out.println("decrypted_message: "+decrypted_message);

    }

    private static String decrypt(int key, String encrypted_message) {
        StringBuilder original_message = new StringBuilder("");
        for (int i=0; i<encrypted_message.length(); i++) {
            char c = (char) (encrypted_message.charAt(i) - key);
            original_message.append(c);
        }
        return String.valueOf(original_message);
    }

    public static String encrypt(int key,String message) {
        StringBuilder encrypted_message = new StringBuilder("");
        for (int i=0; i<message.length(); i++) {
            char c = (char) (message.charAt(i) + key);
            encrypted_message.append(c);
        }
        return String.valueOf(encrypted_message);
    }
}
