package org.example;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class DESExample {
    public static final String UNICODE_FORMAT = "UTF-8";
    private static final String ALGO = "DES";

    private static Cipher cipher;
    private static SecretKey key;
    byte[] keyAsBytes;

    public DESExample(String myEncryptionKey) throws Exception {
        keyAsBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
        KeySpec keySpec = new DESKeySpec(keyAsBytes);
        SecretKeyFactory mySecretKeyFactory = SecretKeyFactory.getInstance(ALGO);
        cipher = Cipher.getInstance(ALGO);
        key = mySecretKeyFactory.generateSecret(keySpec);
    }

    public static String encrypt(String dataToEncrypt) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] dataToEncrypt_InBytes = dataToEncrypt.getBytes(UNICODE_FORMAT);
        byte[] encryptedData_inBytes = cipher.doFinal(dataToEncrypt_InBytes);
        return Base64.getEncoder().encodeToString(encryptedData_inBytes);
    }

    public static String decrypt(String dataToDecrypt) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedData = Base64.getDecoder().decode(dataToDecrypt);
        byte[] deryptedData_inBytes = cipher.doFinal(decodedData);
        return new String(deryptedData_inBytes);
    }

    public static void main(String[] args) throws Exception {
        String confidential_data = "This is a confidential message";
        DESExample des = new DESExample("This is some key");

        String encrypted = encrypt(confidential_data);
        System.out.println("encrypted: "+encrypted);

        String decrypted = decrypt(encrypted);
        System.out.println("decrypted: "+decrypted);
    }
}
