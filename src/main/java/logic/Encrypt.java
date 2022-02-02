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
            System.out.println("ENC_KEY : " + DatatypeConverter.printHexBinary(secret.getEncoded()));

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE , secret , this.spec);





            byte[]name = pass.getNAME().getBytes(StandardCharsets.UTF_8);
            byte[]link = pass.getLINK().getBytes(StandardCharsets.UTF_8);
            byte[]date = pass.getDATE().getBytes(StandardCharsets.UTF_8);
            byte[]password = pass.getPASSWORD().getBytes(StandardCharsets.UTF_8);


            String encText =
                    DatatypeConverter.printHexBinary(cipher.doFinal(name)) + "!" +
                    DatatypeConverter.printHexBinary(cipher.doFinal(link)) + "!" +
                    DatatypeConverter.printHexBinary(cipher.doFinal(date)) + "!" +
                    DatatypeConverter.printHexBinary(cipher.doFinal(password));

            return "encText";




        }catch (Exception e){
            e.printStackTrace();
        }

        return "";
    }







}
