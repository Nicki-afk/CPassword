package logic;

import managers.FileManager;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import org.apache.commons.lang3.ArrayUtils;

public class Encrypt {


    private String data;
    private byte[]code_word;
    private MessageDigest digest ;
    private byte[]salt = new byte[32];
    private static Encrypt encrypt;

    private Encrypt(String data){

        this.data = data;

        try{

            digest = MessageDigest.getInstance("SHA-512");
            digest.update(salt);
            code_word = digest.digest(data.getBytes(StandardCharsets.UTF_8));

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public Encrypt(){}


    public static Encrypt getInstance(String word){

        return encrypt == null ? encrypt = new Encrypt(word) : encrypt;

    }







    public boolean encrypt(){


        FileManager manager = new FileManager();
        manager.write(code_word);
        return true;

    }

    public boolean decrypt(String word){

        if(!new FileManager().filesExists()){
            return false;
        }else {


            byte[] salt = new byte[32];
            try {

                MessageDigest digest = MessageDigest.getInstance("SHA-512");
                digest.update(salt);
                FileManager fileManager = new FileManager();
                Byte[] inWord = ArrayUtils.toObject(digest.digest(word.getBytes(StandardCharsets.UTF_8)));
                ArrayList<Byte> bytes = new ArrayList<>();

                Scanner scanner = new Scanner(fileManager.read()).useDelimiter("/");

                while (scanner.hasNext()) {

                    bytes.add(scanner.nextByte());

                }


                Byte[] codeWord = bytes.toArray(new Byte[bytes.size()]);


                if(Arrays.equals(inWord, codeWord)){
                    this.code_word = ArrayUtils.toPrimitive(codeWord);
                    return true;

                }else{
                    return false;
                }



            } catch (Exception e) {


                return false;
            }

        }



    }





}
