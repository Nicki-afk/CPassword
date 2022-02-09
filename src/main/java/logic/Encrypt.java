package logic;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;

public class Encrypt {


    /**

     @date : 09:02:2022
     @author : Niki-afk
     @info :

     This class is intended for password encryption, as well as the Decrypt class will probably be removed



     */




    private Pass pass;
    private String keyWord;
    private byte[]salt ;

    private IvParameterSpec spec;

    private String encWord;



    public Encrypt(String keyWord ,  Pass pass , byte[]salt , IvParameterSpec spec){

        this.keyWord = keyWord;
        this.pass = pass;
        this.salt = salt;
        this.spec = spec;


    }

    public String enc(){

        try {

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec spec = new PBEKeySpec(this.keyWord.toCharArray(), salt, 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE , secret , this.spec);



            String enc = DatatypeConverter.printHexBinary(cipher.doFinal(pass.format().getBytes(StandardCharsets.UTF_8)));


            return enc;




        }catch (Exception e){


            System.err.println("DATA ENCRYPTION ERROR (" + e + "):(" + this.getClass() + ")");
        }

        return "";
    }







}
