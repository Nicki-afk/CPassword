package managers;


import logic.Crypto;
import logic.Decrypt;
import logic.Encrypt;
import logic.Pass;
import javax.crypto.spec.IvParameterSpec;
import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;


public class CryptoManager extends Crypto{




    // Nicki-afk

    private ArrayList<String>records = new ArrayList<>();
    private ArrayList<String>passwords = new ArrayList<>();

    private String data;
    private String word;
    private byte[]salt;
    private  byte[] ivParameters;

    public static CryptoManager cryptoManager;




    private CryptoManager(String data){

        super(data);
        this.data = data;
        this.salt = initSalt();
        this.ivParameters = initSalt();

    }

    private CryptoManager(){
        super();
        salt = initSalt();
        this.ivParameters = initSalt();

    }


    public static CryptoManager getInstance(String data){

        return cryptoManager == null ? cryptoManager = new CryptoManager(data) : cryptoManager;
    }

    public static CryptoManager getInstance(){

        return cryptoManager == null ? cryptoManager = new CryptoManager() : cryptoManager;
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
                writeBytes("user/base.txt" , ivParameters);
                readRecords("user/base.txt" , records);
            }else{
                readRecords("user/base.txt" , records);
                readRecords("user/basep.txt" , passwords);
                salt = getItSalt();
                this.ivParameters = DatatypeConverter.parseHexBinary(records.get(1));



                // test
                transformPass();

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Override
    public boolean isCodeWord(String word) {

        try{


            String codeWord = records.get(2);
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.update(salt);
            String inWord = DatatypeConverter.printHexBinary(digest.digest(word.getBytes(StandardCharsets.UTF_8)));
            this.word = codeWord.equals(inWord) ? word : null;



            return codeWord.equals(inWord);


        }catch (Exception e){

            e.printStackTrace();
        }



        return false;
    }




    @Override
    public void writeBytes(String file , byte[]data){


        try(
                BufferedReader reader = new BufferedReader(new FileReader(file))


                ){

            String hesh = DatatypeConverter.printHexBinary(data);

            ArrayList<String>local = new ArrayList<>();

            String i  = "";
            while ((i = reader.readLine()) != null){

                local.add(i);

            }
            local.add(hesh);

            try(

                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));

                    ){

                for (String s : local){

                    writer.write(s + "\n");

                }

                writer.flush();


            }




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
    public void writeCryptoWord(){

        try(
                BufferedWriter writer = new BufferedWriter(new FileWriter("user/base.txt"))


        ){


            byte[]recordBytes = encrypt(this.data , salt);

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

        try(

                BufferedWriter writer = new BufferedWriter(new FileWriter("user/basep.txt"))


                ){


            System.out.println("'" + this.word + "'");

            Encrypt encrypt = new Encrypt(this.word , pass , this.salt , new IvParameterSpec(ivParameters));

            passwords.add(encrypt.enc());

            for(String i : passwords){

                writer.write(i + "\n");
            }

            writer.flush();

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public void readRecords(String filePath , ArrayList<String>list){


        try(
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
        ){

            String i = "";
            while ((i = reader.readLine()) != null){

                list.add(i);
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public void transformPass(){


        Decrypt decrypt = new Decrypt("DOG" , passwords , this.salt , new IvParameterSpec(ivParameters));

        decrypt.dec();


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