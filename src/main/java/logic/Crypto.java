package logic;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.ArrayList;

public abstract class Crypto {


    /**

     @date : 09:02:2022
     @author : Niki-afk
     @info :

     The Crypto class is the parent class of the CryptoManager class.
     This class contains part of the abstracted logic that is implemented in the
     heir class, as well as part of the logic (mainly encryption) that is implemented
     directly in this class



     */




   // private String data;
    private byte[]salt = new byte[32];
    private SecureRandom random = new SecureRandom();

     public Crypto(){}




    protected  byte[] initSalt(){

        byte[]salt = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);

        return salt;
    }





    // ENCRYPT METHOD
    public String enc(String keyWord ,  Pass pass , byte[]salt , IvParameterSpec spec){

        try {

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec keySpec = new PBEKeySpec(keyWord.toCharArray(), salt, 65536, 256);
            SecretKey tmp = factory.generateSecret(keySpec);
            SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE , secret , spec);

            String enc = DatatypeConverter.printHexBinary(cipher.doFinal(pass.format().getBytes(StandardCharsets.UTF_8)));


            return enc;

        }catch (Exception e){
            System.err.println("DATA ENCRYPTION ERROR (" + e + "):(" + this.getClass() + ")");
        }

        return "";
    }


   // DECRYPT METHOD
   public ObservableList<Pass> dec(String keyWord , ArrayList<String> dataList , byte[]salt , IvParameterSpec spec ){


       try {


           SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");  // Create KeyFactory
           KeySpec keySpec = new PBEKeySpec(keyWord.toCharArray(), salt, 65536, 256); //Creating a key by code word
           SecretKey tmp = factory.generateSecret(keySpec);    // Generate Key
           SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES"); // init secret key

           Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
           cipher.init(Cipher.DECRYPT_MODE , secret , spec);

           ObservableList<Pass>list = FXCollections.observableArrayList();



           for(int x = 0; x < dataList.size(); x++){

               byte[]loc = DatatypeConverter.parseHexBinary(dataList.get(x));
               String s = new String(cipher.doFinal(loc));
               list.add(new Pass(s));

           }



           return list;


       }catch (Exception e){
           System.out.println("DATA DECRYPTION ERROR (" + e + "):(" + this.getClass() + ")");
       }

       return null;
    }



    // HESH METHOD
    protected byte[] encrypt(String inWord , byte[]salt){

        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.update(salt);


            return digest.digest(inWord.getBytes(StandardCharsets.UTF_8));
        }catch (Exception e){

            System.err.println("ENCRYPTION ERROR (" + e + "):(" + this.getClass() + ")");

        }

        return new byte[]{0};


    }



    // abstract methods

    protected abstract boolean isCodeWord(String word);
    protected abstract void writeBytes(String file, byte[] data);
    protected abstract byte[]getItSalt();
    protected abstract void init();
    protected abstract void writeCryptoWord();
    protected abstract void writeCryptoPass(Pass pass);

}


