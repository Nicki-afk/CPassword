package logic;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Scanner;

public class Decrypt {



    private ArrayList<String> dataList;
    private String keyWord ;
    private byte[]salt ;

    private IvParameterSpec spec;

    private String encWord;



    public Decrypt(String keyWord , ArrayList<String>dataList ,byte[]salt , IvParameterSpec spec ){

        this.keyWord = keyWord;
        this.salt = salt;
        this.spec = spec;
        this.dataList = dataList;

    }

    public Decrypt(){}




    public ArrayList<String> format(ArrayList<String>list){


        ArrayList<String>local = new ArrayList<>();








        return null;



    }



    public String dec(){

        try {
//
//            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
//            KeySpec spec = new PBEKeySpec(this.keyWord.toCharArray(), salt, 65536, 256);
//            SecretKey tmp = factory.generateSecret(spec);
//            SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");
//            System.out.println("ENC_KEY : " + DatatypeConverter.printHexBinary(secret.getEncoded()));
//
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//            cipher.init(Cipher.DECRYPT_MODE , secret , this.spec);
//
//            byte[]cipWord = DatatypeConverter.parseHexBinary(this.word);
//
//           return new String(cipher.doFinal(cipWord));

           return "";



        }catch (Exception e){
            e.printStackTrace();
        }

        return "";

    }




}
