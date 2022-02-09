package logic;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;

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




    private String data;
    private byte[]code_word;
    private MessageDigest digest ;
    private byte[]salt = new byte[32];
    private SecureRandom random = new SecureRandom();
    private static Crypto crypto;

     public Crypto(String data){

        this.data = data;

     }
     public Crypto(){}




    protected  byte[] initSalt(){

        byte[]salt = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);

        return salt;
    }





    // CIPHER METHOD
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

    public void setCryptoData(String data) {
        this.data = data;
    }
}


