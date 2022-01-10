package managers;


import logic.Crypto;
import org.apache.commons.lang3.ArrayUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CryptoManager extends Crypto{




    // Nicki-afk

    private ArrayList<String>records = new ArrayList<>();

    private String data;
    private byte[]salt;

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
    protected boolean isCodeWord(String word) {
        return false;
    }

    @Override
    public void writeBytes(String file , byte[]data){


        try(

                BufferedWriter writer = new BufferedWriter(new FileWriter(file))


        ){
            writer.write("{");

            for(int i = 0; i < data.length; i++){

                writer.write(data[i] + "/");


            }

            writer.write("}\n");
            writer.flush();

        }catch (Exception e){

            e.printStackTrace();
        }

    }

    @Override
    public byte[] getItSalt(){


        ArrayList<Byte>bytes = new ArrayList<>();
        String salt = records.get(0).substring(1 , records.get(0).length()-1);
        System.out.println(salt);
        Scanner scanner = new Scanner(salt).useDelimiter("/");

        while (scanner.hasNext()){

            bytes.add(scanner.nextByte());
        }

        byte[]saltBytes = ArrayUtils.toPrimitive(bytes.toArray(new Byte[bytes.size()]));

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
                // writeBytes("user/base.txt" , initSalt());
                writeBytes("user/base.txt" , salt);
            }else{
                read("user/base.txt");
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



            // add method genRecord()
            byte[]recordBytes = encrypt(this.data , salt);
            String record = "{";

            for(byte i : recordBytes){

                record += i + "/";
            }

            record+= "}\n";

            records.add(record);



            for(int x = 0; x < records.size(); x++){

                writer.write(records.get(x) + "\n");

            }

            writer.flush();


        }catch (Exception e){

            e.printStackTrace();
        }



    }

    @Override
    public void writeCryptoPass(){


        // ...
    }







    public void read(String filePath){


        try(
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
        ){

            String i = "";
            while ((i = reader.readLine()) != null){

                System.out.println(i);
                records.add(i);
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
