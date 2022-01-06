package managers;


import logic.Crypto;
import org.apache.commons.lang3.ArrayUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CryptoManager extends Crypto{




    private ArrayList<String>records = new ArrayList<>();

    private String data;
    private byte[]salt;



    public CryptoManager(String data){

        super(data);
        this.data = data;

    }
    public CryptoManager(){}


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



    public void init(){


        try {

            File file = new File("user");

            if (!file.exists()) {
                file.mkdir();
                file = new File("user\\base.txt");
                file.createNewFile();
                writeBytes("user/base.txt" , initSalt());
            }else{
                read("user/base.txt");
                salt = getItSalt();
            }

        }catch (Exception e){
            e.printStackTrace();
        }



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

    public void writeCryptoWord(){

        try(
                BufferedWriter writer = new BufferedWriter(new FileWriter("user/base.txt"))


                ){

            //records.add(encrypt(this.data ));

            writer.write("{");

            for(int x = 0; x < records.size(); x++){

                writer.write(records.get(x) + "/");

            }

            writer.write("}\n");
            writer.flush();


        }catch (Exception e){

            e.printStackTrace();
        }



    }


    public byte[] getSalt() {
        return salt;
    }
}
