package com.example.accessingdatamysql;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Base64;

import javax.crypto.*;


import org.springframework.stereotype.Component;

import javax.crypto.spec.*;

@Component
public class DBencryptor {
	

//    private Cipher getCipher(final int cypherMode) throws GeneralSecurityException{
//        final SecretKeySpec keySpec = new SecretKeySpec("tempString".getBytes(), "Blowfish");
//
//        Cipher cipher = Cipher.getInstance("Blowfish");
//        cipher.init(cypherMode, keySpec);
//
//        return cipher;
//    }
//
//    public String Encrypt(final String value){
//        String encryptedString = null;
//        try {
//            final Cipher cipher = getCipher(Cipher.ENCRYPT_MODE);
//            byte[] data = value.getBytes();
//            byte[] encrypted = cipher.doFinal(data);
//            encryptedString = Base64.getEncoder().encodeToString(encrypted);
//        } catch (final GeneralSecurityException e) {
//            e.printStackTrace();
//        }
//        return encryptedString;
//    }
//
//    public String Decrypt(final String Encryptedvalue){
//            String DecryptedString=null;
//        try {
//            final Cipher cipher = getCipher(Cipher.DECRYPT_MODE);
//            byte[] data = Base64.getDecoder().decode(Encryptedvalue);
//            byte[] decrypted = cipher.doFinal(data);
//            DecryptedString = new String((decrypted));
//            
//
//        } catch (GeneralSecurityException e) {
//        	e.printStackTrace();
//        }
//        return DecryptedString;
//    }

	

	    private static final String EncrypAlgo = "Blowfish";


	    private final Cipher encryptCipher;
	    private final Cipher decryptCipher;


	    public DBencryptor() throws Exception {

	        byte[] encryptionKey = "testString".getBytes("UTF-8");
	        SecretKeySpec keySpec = new SecretKeySpec(encryptionKey, EncrypAlgo);
	        encryptCipher = Cipher.getInstance(EncrypAlgo);
	        encryptCipher.init(Cipher.ENCRYPT_MODE, keySpec);
	        decryptCipher = Cipher.getInstance(EncrypAlgo);
	        decryptCipher.init(Cipher.DECRYPT_MODE, keySpec);

	    }

	    public String Encrypt(String unEncryptedToken) throws Exception {
	        try {
	            return Base64.getEncoder().encodeToString(encryptCipher.doFinal(unEncryptedToken.getBytes("UTF-8")));
	        } catch (UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException e) {
	            
	            throw new Exception(e);
	        }
	    }

	    public String Decrypt(String encryptedToken) throws Exception {
	        try {
	            return new String(decryptCipher.doFinal(Base64.getDecoder().decode(encryptedToken)));
	        } catch (IllegalBlockSizeException | BadPaddingException e) {
	         
	          throw new Exception(e);
	          
	        }
	    }

	
	
}