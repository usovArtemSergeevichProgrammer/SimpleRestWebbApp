package utils;

import java.security.InvalidKeyException;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import org.apache.commons.codec.binary.Base64;

public class CryptoUtils {

    private static final String UNICODE_FORMAT;
    private static final String DESEDE_ENCRYPTION_SCHEME;
    private static final String MY_ENCRYPT_KEY;
    private static KeySpec ks;
    private static SecretKeyFactory skf;
    private static Cipher cipher;
    static byte[] arrayBytes;
    private static String myEncryptionScheme;
    static SecretKey key;

    static {
        UNICODE_FORMAT = AppSettings.getInstance().getProperty("UNICODE_FORMAT");
        DESEDE_ENCRYPTION_SCHEME = AppSettings.getInstance().getProperty("DESEDE_ENCRYPTION_SCHEME");
        MY_ENCRYPT_KEY = AppSettings.getInstance().getProperty("MY_ENCRYPT_KEY");
        try {
            myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
            arrayBytes = MY_ENCRYPT_KEY.getBytes(UNICODE_FORMAT);
            skf = SecretKeyFactory.getInstance(myEncryptionScheme);
            cipher = Cipher.getInstance(myEncryptionScheme);
            ks = new DESedeKeySpec(arrayBytes);
            key = skf.generateSecret(ks);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static String encrypt(String unencryptedString) {
        String encryptedString = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = new String(Base64.encodeBase64URLSafe(encryptedText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedString;
    }

    public static String decrypt(String encryptedString) {
        String decryptedText = null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedText = Base64.decodeBase64(encryptedString);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText = new String(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedText;
    }

    public static void main(String args[]) throws Exception {
        String target = "123";
        // String decrypted_test=td.decrypt(target);
        String encrypted = encrypt(target);
        String decrypted = decrypt(encrypted);
        System.out.println("String To Encrypt: " + target);
        System.out.println("Encrypted String:" + encrypted);
        System.out.println("Decrypted String:" + decrypted);

    }
}
