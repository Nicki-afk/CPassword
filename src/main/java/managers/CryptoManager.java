package managers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.Crypto;
import logic.Pass;
import javax.crypto.spec.IvParameterSpec;
import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;


public class CryptoManager extends Crypto{




    // Nicki-afk


    /**

     @date : 09:02:2022
     @author : Niki-afk
     @info:

     This class is responsible for formatting information and writing this information
     to system files, as well as reading and converting information to its original form.
     The class actively works with the file system, namely, it writes and reads data
     from files. This class inherits from the parent class Crypto , this is done in order
     to unload the class and shift some of the responsibility for encryption to the parent
     class, and also to comply with OOP conventions. Also, this class is single and uses the
     singleton pattern, since this class is used in various other classes of the program, and
     contains common information for all auxiliary classes




     */





    private ArrayList<String>records = new ArrayList<>();
    private ArrayList<String>passwords = new ArrayList<>();
    private ObservableList<Pass>passwordsList = FXCollections.observableArrayList();

    private String data;
    private String word;
    private byte[]salt;
    private  byte[] ivParameters;

    public static CryptoManager cryptoManager;




    private CryptoManager(String data){

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


            // CHECK SYSTEM FILES
            if (!file.exists()) {


                // CREATE SYS FILES

                file.mkdir();
                file = new File("user\\base.txt");
                file.createNewFile();
                file = new File("user\\basep.txt");
                file.createNewFile();
                writeBytes("user/base.txt" , salt);
                writeBytes("user/base.txt" , ivParameters);
                readRecords("user/base.txt" , records);


            }else{

                // READ SYS FILES

                readRecords("user/base.txt" , records);
                readRecords("user/basep.txt" , passwords);
                salt = getItSalt();
                this.ivParameters = DatatypeConverter.parseHexBinary(records.get(1));




            }

        }catch (Exception e){

            System.err.println("ERROR WHEN READING OR CREATING SYSTEM FILES ( " + e + " ):(" + this.getClass() + ")");
        }

    }


    @Override
    public boolean isCodeWord(String word) {

        try{


            // CHECKING CODE WORD

            String codeWord = records.get(2);
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.update(salt);
            String inWord = DatatypeConverter.printHexBinary(digest.digest(word.getBytes(StandardCharsets.UTF_8)));
            this.word = codeWord.equals(inWord) ? word : null;



            return codeWord.equals(inWord);


        }catch (Exception e){


            System.err.println("CODEWORD VERIFICATION ERROR (" + e + "):(" + this.getClass() + ")");
        }



        return false;
    }




    // WRITE METHOD

    @Override
    public void writeBytes(String file , byte[]data){


        /**

         @explanation :

         I made the process of reading and writing to the file in different blocks, because
         if they are initialized in the same try-width-resources block, the write stream will
         run faster anyway, thereby deleting all data from the file.


         */


        // READ BLOCK
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


            // WRITE BLOCK
            try(

                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));

                    ){

                for (String s : local){

                    writer.write(s + "\n");

                }

                writer.flush();


            }




        }catch (Exception e){


            System.err.println("ERROR WHEN WRITING DATA TO FILE (" + e + "):(" + this.getClass() + ")");

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

        // WRITING CODE-WORD

        try(
                BufferedWriter writer = new BufferedWriter(new FileWriter("user/base.txt"))


        ){



            byte[]recordBytes = encrypt(this.word , salt);   // Method encrypt() is method Parent class Crypto

            records.add(DatatypeConverter.printHexBinary(recordBytes));


            for(int x = 0; x < records.size(); x++){

                writer.write(records.get(x) + "\n");

            }

            writer.flush();

        }catch (Exception e){


            System.out.println("ERROR WHEN WRITING CODEWORD TO FILE (" + e + "):(" + this.getClass() + ")");

        }
    }

    @Override
    public void writeCryptoPass(Pass pass){

        try(

                BufferedWriter writer = new BufferedWriter(new FileWriter("user/basep.txt"))


                ){



         //   Encrypt encrypt = new Encrypt(this.word , pass , this.salt , new IvParameterSpec(ivParameters));

            passwords.add(enc(this.word , pass , this.salt , new IvParameterSpec(ivParameters)));

            for(String i : passwords){

                writer.write(i + "\n");
            }

            writer.flush();

        }catch (Exception e){


            System.err.println("ERROR WRITE PASSWORD TO FILE (" + e + "):(" + this.getClass() + ")");
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

            System.err.println("ERROR READING FILE (" + filePath + ")(" + e + ")(" + this.getClass() + ")");


        }
    }



    public boolean transform(){


       // Decrypt decrypt = new Decrypt(this.word , passwords , this.salt , new IvParameterSpec(ivParameters));
        ObservableList<Pass>local = dec(this.word , passwords , this.salt , new IvParameterSpec(ivParameters));

        if(local != null){

            passwordsList = local;
            return true;
        }

        return false;


    }


    public byte[] getSalt() {
        return salt;
    }


    public void setData(String word) {
        this.word =  word;
        //setCryptoData(word);
    }


    public void  setCodeWord(String word){
        this.word = word;
    }




    public ObservableList<Pass>getPasswordsList(){

        return passwordsList;
    }




    public int passwordSize(){

        return passwords.size();

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