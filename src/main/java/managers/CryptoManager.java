package managers;


import logic.Crypto;
import logic.Pass;
import logic.Password;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Arrays;

public class CryptoManager extends Crypto{




    // Nicki-afk

    private ArrayList<String>records = new ArrayList<>();
    private ArrayList<String>passwords = new ArrayList<>();

    private String data;
    private String word;
    private byte[]salt;
    private SecretKeySpec keySpec ;

    public static CryptoManager cryptoManager;




    private CryptoManager(String data){

        super(data);
        this.data = data;
        salt = initSalt();

    }

    private CryptoManager(){
        super();
        salt = initSalt();
    }


    public static CryptoManager getInstance(String data){

        return cryptoManager == null ? cryptoManager = new CryptoManager(data) : cryptoManager;
    }

    public static CryptoManager getInstance(){

        return cryptoManager == null ? cryptoManager = new CryptoManager() : cryptoManager;
    }


    @Override
    public boolean isCodeWord(String word) {

        try{


            String codeWord = records.get(1);
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.update(salt);
            String inWord = DatatypeConverter.printHexBinary(digest.digest(word.getBytes(StandardCharsets.UTF_8)));
            System.out.println("[IN_WORD_IS] : " + inWord);
            System.out.println("[WORD_IS_  ] : " + codeWord);

            this.word = codeWord.equals(inWord) ? inWord : null;

            return codeWord.equals(inWord);


        }catch (Exception e){

            e.printStackTrace();
        }



        return false;
    }

    @Override
    public void writeBytes(String file , byte[]data){


        try(

                BufferedWriter writer = new BufferedWriter(new FileWriter(file))


        ){
//            writer.write("{");
//
//            for(int i = 0; i < data.length; i++){
//
//                writer.write(data[i] + "/");
//
//
//            }
//
//            writer.write("}\n");
//            writer.flush();


            // C947D5BC934554B2B8CB9056EE6BDBD8F5E7114A07EAC655945F8CEA6C3AF8D5
            // 67,57,52,55,68,53,66,67,57,51,52,53,53,52,66,50,66,56,67,66,57,48,53,54,69,69,54,66,68,66,68,56,70,53,69,55,49,49,52,65,48,55,69,65,67,54,53,53,57,52,53,70,56,67,69,65,54,67,51,65,70,56,68,53,
            //  67,57,52,55,68,53,66,67,57,51,52,53,53,52,66,50,66,56,67,66,57,48,53,54,69,69,54,66,68,66,68,56,70,53,69,55,49,49,52,65,48,55,69,65,67,54,53,53,57,52,53,70,56,67,69,65,54,67,51,65,70,56,68,53,
            String hesh = DatatypeConverter.printHexBinary(data);

            // test
            byte[]word_bytes = hesh.getBytes(StandardCharsets.UTF_8);
            System.out.println("DATA_HESH       : " + hesh);
            System.out.print("DATA_BYTES      : ");
            for (byte i : word_bytes){
                System.out.print(i + ",");
            }
            System.out.println();


            // writing to file
            System.out.println("WRITING TO FILE...");
            writer.write(hesh + "\n");
            writer.flush();


        }catch (Exception e){

            e.printStackTrace();
        }

    }

    @Override
    public byte[] getItSalt(){


        ArrayList<Byte>bytes = new ArrayList<>();
        byte[]saltBytes = DatatypeConverter.parseHexBinary(records.get(0));


        return saltBytes;

    }

    @Override
    public void init(){


        try {

            File file = new File("user");


            if (!file.exists()) {
                file.mkdir();
                file = new File("user\\base.txt");
                file.createNewFile();
                file = new File("user\\basep.txt");
                file.createNewFile();
                writeBytes("user/base.txt" , salt);
                readRecords("user/base.txt" , records);
            }else{
                readRecords("user/base.txt" , records);
                readRecords("user/basep.txt" , passwords);
                salt = getItSalt();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void writeCryptoWord(){

        try(
                BufferedWriter writer = new BufferedWriter(new FileWriter("user/base.txt"))


        ){


            byte[]recordBytes = encrypt(this.data , salt);

            System.out.println("CRYPTO_WORD_IS : " + DatatypeConverter.printHexBinary(recordBytes));
            records.add(DatatypeConverter.printHexBinary(recordBytes));




            for(int x = 0; x < records.size(); x++){

                writer.write(records.get(x) + "\n");

            }

            writer.flush();

        }catch (Exception e){

            e.printStackTrace();
        }
    }

    @Override
    public void writeCryptoPass(Pass pass){


        // [NAME_PASS](LINK_){PASS}

        try(

                BufferedWriter writer = new BufferedWriter(new FileWriter("user/basep.txt"))


                ){

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec spec = new PBEKeySpec(this.word.toCharArray(), salt, 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");

            String encryptData = encrypt(pass , secret);

            passwords.add(encryptData);

            for(int x = 0; x < passwords.size(); x++){

                writer.write(passwords.get(x) + "\n");

            }

            writer.flush();

        }catch (Exception e){
            e.printStackTrace();
        }


        // ...
    }







    public void readRecords(String filePath , ArrayList<String>list){


        try(
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
        ){

            String i = "";
            while ((i = reader.readLine()) != null){

                System.out.println(i);
                list.add(i);
            }
            System.out.println("list size : " + list.size());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void readPasswords(){

        File file = new File("/user/basep.txt");

        try(

                BufferedReader reader = new BufferedReader(new FileReader(file));

                ){


            String i = "";
            while ((i = reader.readLine()) != null){
                passwords.add(i);
            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }









    public byte[] getSalt() {
        return salt;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
        setCryptoData(data);
    }

    public void setKeySpec(String keySpec){

        this.keySpec = new SecretKeySpec(keySpec.getBytes(StandardCharsets.UTF_8) , "AES");

    }






    public ArrayList<String> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<String> records) {
        this.records = records;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }
}
