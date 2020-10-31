package dev.pinter.jfxapp.core;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

public class Cryptography {
    private static final Cryptography INSTANCE = new Cryptography();
    private KeyPair pair;


    static {
        System.out.println("block1 "+INSTANCE);
    }

    {
        System.out.println("block2"+pair);
    }

    public static Cryptography getInstance() throws NoSuchAlgorithmException {
        INSTANCE.keyPairGen();
        return INSTANCE;
    }
    private Cryptography(){}

    private void keyPairGen() throws NoSuchAlgorithmException {
        if(pair == null) {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(4096, new SecureRandom());
            pair = generator.generateKeyPair();
        }
    }

    public String encrypt(String plainText) throws CryptoException {
        System.out.println(pair);
        Cipher encryptCipher = null;
        try {
            encryptCipher = Cipher.getInstance("RSA");
            encryptCipher.init(Cipher.ENCRYPT_MODE, pair.getPublic());
            byte[] cipherText = encryptCipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(cipherText);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | IllegalArgumentException e) {
            throw new CryptoException("Error Encrypting Message", e);
        }


    }

    public String decrypt(String cipherText) throws CryptoException {
        System.out.println(pair);


        Cipher decriptCipher = null;
        try {
            byte[] bytes = Base64.getDecoder().decode(cipherText);
            decriptCipher = Cipher.getInstance("RSA");
            decriptCipher.init(Cipher.DECRYPT_MODE, pair.getPrivate());
            return new String(decriptCipher.doFinal(bytes), StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException | IllegalArgumentException e) {
            throw new CryptoException("Error Decryption Message", e);
        }


    }
}

