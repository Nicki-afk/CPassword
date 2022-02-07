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







    public ObservableList<Pass> dec(){

        try {


            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec spec = new PBEKeySpec(this.keyWord.toCharArray(), salt, 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");

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
            e.printStackTrace();
        }

        return null;

    }




}
