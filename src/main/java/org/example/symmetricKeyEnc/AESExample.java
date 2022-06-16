package org.example.symmetricKeyEnc;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AESExample {
    private static final String ALGO = "AES";
    private byte[] keyAsBytes;

    public AESExample(String key) {
        this.keyAsBytes = key.getBytes();
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
        return new SecretKeySpec(keyAsBytes, ALGO);
    }

    public static void main(String[] args) throws Exception {
        String confidential_data = "This is a confidential message";
        AESExample aes = new AESExample("qwertyuiopasdfgh"); // set 16 digit AES key

        // encrypt
        String encrypted = aes.encrypt(confidential_data);
        System.out.println("encrypted: "+encrypted);

        // decrypt
        String decrypted = aes.decrypt(encrypted);
        System.out.println("decrypted: "+decrypted);
    }

}
