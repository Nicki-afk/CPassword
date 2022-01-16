package logic;


import org.apache.commons.lang3.ArrayUtils;

import javax.swing.plaf.PanelUI;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Crypto {


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

        byte[]salt = new byte[32];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);

        return salt;
    }


    // Add methods
    // - byte[] getEncryptCodeWord();
    // - boolean isCodeWord();


    // rewrite ...
    // return statement remake to byte[]arr




    protected byte[] encrypt(String inWord , byte[]salt){

        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.update(salt);


            return digest.digest(inWord.getBytes(StandardCharsets.UTF_8));
        }catch (Exception e){
            e.printStackTrace();
        }

        return new byte[]{0};


    }


    public byte[] transformFormat(String s){

         String st = s.substring(1 , s.length()-1);

        Scanner scanner = new Scanner(st).useDelimiter("/");
        ArrayList<Byte>bytes = new ArrayList<>();

        while (scanner.hasNext()){

            bytes.add(scanner.nextByte());

        }

        byte[]arr = ArrayUtils.toPrimitive(bytes.toArray(new Byte[bytes.size()]));

        return arr;

    }


    // abstract methods

    protected abstract boolean isCodeWord(String word);
    protected abstract void writeBytes(String file, byte[] data);
    protected abstract byte[]getItSalt();
    protected abstract void init();
    protected abstract void writeCryptoWord();
    protected abstract void writeCryptoPass();

    public void setCryptoData(String data) {
        this.data = data;
    }
}


//        if(!new FileManager().filesExists()){
//            return false;
//        }else {
//
//
//           - byte[] salt = new byte[32]; -\
//            try {
//
//                MessageDigest digest = MessageDigest.getInstance("SHA-512");
//                digest.update(this.salt);
//                FileManager fileManager = new FileManager();
//                Byte[] inWord = ArrayUtils.toObject(digest.digest(word.getBytes(StandardCharsets.UTF_8)));
//             -   ArrayList<Byte> bytes = new ArrayList<>();

// if(this.records.size() < 2)
//
//                // this string to FileManager
//                Scanner scanner = new Scanner(fileManager.read()).useDelimiter("/");
//
//                while (scanner.hasNext()) {
//
//                    bytes.add(scanner.nextByte());
//
//                }
//
//
//                Byte[] codeWord = bytes.toArray(new Byte[bytes.size()]);
//
//
//                if(Arrays.equals(inWord, codeWord)){
//                   this.code_word = ArrayUtils.toPrimitive(codeWord);
//                    return true;
//
//                }else{
//                    return false;
//                }
//
//
//
//            } catch (Exception e) {
//
//
//                return false;
//            }
//
//        }

