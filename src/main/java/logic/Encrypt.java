package logic;

import managers.FileManager;

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

    public Encrypt(String data){

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


    public boolean encrypt(){

        FileManager manager = new FileManager();
        manager.write(code_word);
        return true;

    }

    public boolean decrypt(String word){


        byte[]salt = new byte[32];
        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.update(salt); FileManager fileManager = new FileManager();
            Byte[] inWord = ArrayUtils.toObject(digest.digest(word.getBytes(StandardCharsets.UTF_8)));
            ArrayList<Byte>bytes = new ArrayList<>();

            Scanner scanner = new Scanner(fileManager.read()).useDelimiter("/");

            while (scanner.hasNext()){

                bytes.add(scanner.nextByte());

            }


            Byte[]codeWord = bytes.toArray(new Byte[bytes.size()]);

            return Arrays.equals(inWord , codeWord);






        }catch (Exception e){
            e.printStackTrace();
        }


        return false;

    }





}
