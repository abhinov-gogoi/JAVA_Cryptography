package org.example.asymmetricKeyEnc;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.util.Arrays;
import java.util.Base64;

public class LearnRSA {

    private static final String ALGO = "RSA/ECB/PKCS1Padding";
    PublicKey publicKey;
    PrivateKey privateKey;

    public LearnRSA() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = generator.generateKeyPair();

        publicKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();
    }

    public String encrypt(String message) throws Exception {
        byte[] messageToBytes = message.getBytes();
        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(messageToBytes);
        return encode(encryptedBytes);
    }

    public String decrypt(String encryptedMessage) throws Exception {
        byte[] encryptedBytes = decode(encryptedMessage);
        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

    public String encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }

    public static void main(String[] args) throws Exception {
        LearnRSA rsa = new LearnRSA();

        String confidential_message = "I am IronMan";
        System.out.println("original_message: "+confidential_message);

        String encrypted_message = rsa.encrypt(confidential_message);
        System.out.println("encrypted_message: "+encrypted_message);

        String decrypted_message = rsa.decrypt(encrypted_message);
        System.out.println("decrypted_message: "+decrypted_message);
    }
}
