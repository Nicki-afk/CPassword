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
import java.security.spec.KeySpec;
import java.util.ArrayList;

public class Decrypt {


    /**



     @date : 09:02:2022
     @author : Niki-afk
     @info :
     This class is used to encrypt the password and turn it into a formatted
     string that the Pass object accepts (possibly removed soon)




     */



    private ArrayList<String> dataList;
    private String keyWord ;
    private byte[]salt ;

    private IvParameterSpec spec;

    private String encWord;


   // At the entrance is a sheet with encrypted passwords, as well as the parameters necessary for decryption
    public Decrypt(String keyWord , ArrayList<String>dataList ,byte[]salt , IvParameterSpec spec ){

        this.keyWord = keyWord;
        this.salt = salt;
        this.spec = spec;
        this.dataList = dataList;

    }

    public Decrypt(){}



    public ObservableList<Pass> dec(){

        try {


            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");  // Create KeyFactory
            KeySpec spec = new PBEKeySpec(this.keyWord.toCharArray(), salt, 65536, 256); //Creating a key by code word
            SecretKey tmp = factory.generateSecret(spec);    // Generate Key
            SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES"); // init secret key

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE , secret , this.spec);

            ObservableList<Pass>list = FXCollections.observableArrayList();



            for(int x = 0; x < this.dataList.size(); x++){

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




}
