package logic;


import org.apache.commons.lang3.ArrayUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.plaf.PanelUI;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.xml.bind.DatatypeConverter;
import javax.xml.crypto.Data;
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


    protected String encrypt(Cipher cipher , SecretKey key , Pass pass){


         try {

             cipher.init(Cipher.ENCRYPT_MODE, key);
             String namePass = DatatypeConverter.printHexBinary(cipher.doFinal(pass.getNAME().getBytes(StandardCharsets.UTF_8)));
             String link  = DatatypeConverter.printHexBinary(cipher.doFinal(pass.getLINK().getBytes(StandardCharsets.UTF_8)));
             String date = DatatypeConverter.printHexBinary(cipher.doFinal(pass.getDATE().getBytes(StandardCharsets.UTF_8)));
             String password = DatatypeConverter.printHexBinary(cipher.doFinal(pass.getPASSWORD().getBytes(StandardCharsets.UTF_8)));

             String mainCipher = namePass + "!" + link + "!" + date + "!" + password;

             return mainCipher;

         }catch (Exception e){
             e.printStackTrace();
         }

         return null;

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







    public String getRecord(byte[]arr){

         String record = "{";

         for(byte i : arr){

             record += i + "/";

         }

         record += "}";

         return record;

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

