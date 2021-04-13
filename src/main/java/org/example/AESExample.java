package org.example;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

//www.youtube.com/watch?v=uxyGJMBs2dI&t=28s
public class AESExample {
    private static final String ALGO = "AES";
    private byte[] keyValue;

    public AESExample(String key) {
        this.keyValue = key.getBytes();
    }

    public String encrypt(String data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedValue = c.doFinal(data.getBytes());
        String encodedValue = Base64.getEncoder().encodeToString(encryptedValue);
        return encodedValue;
    }

    public String decrypt(String encryptedData) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedValue = c.doFinal(decodedValue);
        return new String(decryptedValue);
    }

    private Key generateKey() {
        return new SecretKeySpec(keyValue, ALGO);
    }

    public static void main(String[] args) throws Exception {
        String confidential_data = "AbhinovGogoi";
        AESExample aes = new AESExample("qwertyuiopasdfgh"); // set 16 digit AES key

        // encrypt
        String encrypted_confidential_data = aes.encrypt(confidential_data);
        System.out.println("encrypted_confidential_data: "+encrypted_confidential_data);

        // decrypt
        String decrypted_confidential_data = aes.decrypt(encrypted_confidential_data);
        System.out.println("decrypted_confidential_data: "+decrypted_confidential_data);
    }

}
