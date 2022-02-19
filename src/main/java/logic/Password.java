package logic;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import managers.AnimManager;

import java.util.Random;


public class Password {


    /**

     @date : 09:02:2022
     @author : Niki-afk
     @info  :
     This class is a descendant of the abstract class Cpassword . The class
     uses the data of the parent class for password generation (may be rewritten)





     */









    private Scene scene;
    private TextField filed;
    private int percentage;
    private Text text;
    private AnimManager animManager;

    private char[]pAl = "QWERTYUIOPASDFGHJKLZXCVBNM".toCharArray();
    private char[]lAl = "qwertyuiopasdfghjklzxcvbnm".toCharArray();
    private char[]spc = "!@@###$&*__".toCharArray();
    private char[]nums = "1234567890".toCharArray();





    public Password(){}





    public String generatePassword(String word , boolean sp , boolean num){


        StringBuffer buffer = new StringBuffer(word);

        Random random = new Random();
        String paste = "";

        if(sp && num){




            paste+= spc[random.nextInt(spc.length-1)];
            paste+= spc[random.nextInt(spc.length-1)];
            paste+=  nums[random.nextInt(nums.length-1)];
            paste+=  nums[random.nextInt(nums.length-1)];


            buffer.insert(random.nextInt(word.length()-1) , paste);

            return buffer.toString();

        }else if(sp && !num){



            paste += spc[random.nextInt(spc.length-1)];
            paste += spc[random.nextInt(spc.length-1)];

            buffer.insert(random.nextInt(word.length()-1) , paste);

            return buffer.toString();



        }else if(num && !sp){



             paste += nums[random.nextInt(nums.length-1)];
             paste += nums[random.nextInt(nums.length-1)];

            buffer.insert(random.nextInt(word.length()-1) , paste);

            return buffer.toString();

        }else{


            paste += lAl[random.nextInt(nums.length-1)];
            paste += pAl[random.nextInt(nums.length-1)];

            buffer.insert(random.nextInt(word.length()-1) , paste);

            return buffer.toString();

        }

    }


    public String generatePassword(boolean sp , boolean num , int len){


        Random random = new Random();
        String pass = "";

        if(sp && num){


            for(int x = 0; x < len; x++){

                switch (random.nextInt(4)){

                    case 0 : pass+= pAl[random.nextInt(pAl.length-1)]; break;
                    case 1 : pass+= lAl[random.nextInt(lAl.length-1)]; break;
                    case 2 : pass+= nums[random.nextInt(nums.length-1)]; break;
                    case 3 : pass+= spc[random.nextInt(spc.length-1)]; break;

                    default: pass+=""; break;

                }
            }

        }else if(sp && !num){

            for(int x = 0; x < len; x++){

                switch (random.nextInt(3)){

                    case 0 : pass+= pAl[random.nextInt(pAl.length-1)]; break;
                    case 1 : pass+= lAl[random.nextInt(lAl.length-1)]; break;
                    case 2 : pass+= spc[random.nextInt(spc.length-1)]; break;
                    default: pass+=""; break;

                }
            }

        }else if(num && !sp){


            for(int x = 0; x < len; x++){

                switch (random.nextInt(3)){

                    case 0 : pass+= pAl[random.nextInt(pAl.length-1)]; break;
                    case 1 : pass+= lAl[random.nextInt(lAl.length-1)]; break;
                    case 2 : pass+= nums[random.nextInt(nums.length-1)]; break;
                    default: pass+=""; break;

                }
            }


        }else{

            for(int x = 0; x < len; x++){

                switch (random.nextInt(2)){

                    case 0 : pass+= pAl[random.nextInt(pAl.length-1)]; break;
                    case 1 : pass+= lAl[random.nextInt(lAl.length-1)]; break;
                    default: pass+=""; break;

                }
            }

        }




        return pass;

    }





    public String generatePassword() {


        Random random = new Random();


        String pass = "";

        for(int x = 0; x < 12; x++){


            switch (random.nextInt(4)){

                case 0 : pass+= pAl[random.nextInt(pAl.length-1)]; break;
                case 1 : pass+= lAl[random.nextInt(lAl.length-1)]; break;
                case 2 : pass+= nums[random.nextInt(nums.length-1)]; break;
                case 3 : pass+= spc[random.nextInt(spc.length-1)]; break;

                default: pass+=""; break;

            }

        }




        return pass;

    }




    public int getPercentage() {
        return percentage;
    }


}
