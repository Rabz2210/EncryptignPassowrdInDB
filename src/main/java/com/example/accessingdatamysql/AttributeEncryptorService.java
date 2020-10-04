package com.example.accessingdatamysql;
import java.io.UnsupportedEncodingException;

import javax.crypto.*;
import javax.persistence.*;
import javax.crypto.spec.*;

public class AttributeEncryptorService implements AttributeConverter<String, String> {

    private static final String EncrypAlgo = "Blowfish";
     

    private final Cipher encryptCipher;
    private final Cipher decryptCipher;

    public AttributeEncryptorService() throws Exception {
    	byte[] encryptionKey = "this-is-test-key".getBytes();
        SecretKeySpec keySpec = new SecretKeySpec(encryptionKey, EncrypAlgo);
        encryptCipher = Cipher.getInstance(EncrypAlgo);
        encryptCipher.init(Cipher.ENCRYPT_MODE, keySpec);
        decryptCipher = Cipher.getInstance(EncrypAlgo);
        decryptCipher.init(Cipher.DECRYPT_MODE, keySpec);
    }


    @Override
    public String convertToDatabaseColumn(String attribute) {
        try {
            return new String(encryptCipher.doFinal(attribute.getBytes("UTF-8")));
        } catch (IllegalBlockSizeException | BadPaddingException e) {
           
               System.out.println("Error convertToDatabaseColumn ");
               e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "DummyString";
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        try {
            return new String(decryptCipher.doFinal(dbData.getBytes("UTF-8")));
        } catch (IllegalBlockSizeException | BadPaddingException e) {
        	System.out.println("Error convertToEntityAttribute");
        	e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "DummyString";
    }
}
