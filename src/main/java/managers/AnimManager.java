package managers;

import javafx.scene.Scene;
import javafx.scene.text.Text;


public class AnimManager extends Thread{


    private Text text;
    private int sum;

    public AnimManager(Text text , int sum){

        this.sum = sum;
        this.text = text;

    }



    public void playAnim(){


        try{

            int x = 0;
            int R = 255;
            int G = 30;
            int B = 24;

            int sharpness = 20;
            int increment = 0;
            if(sum < 20) {
                increment = 30;
            }else if(sum < 50){
                increment = 5;
            }else if(sum < 100){
                increment = 4;
            }else if(sum == 100){
                increment = 2;
            }



            while (x < sum){

                this.text.setText(x++ + "%");
                this.text.setStyle("-fx-fill : rgb(" + R + " , " + G + " , " + B + ");");
                sleep(sharpness);

                if(G != 255){
                    G+=5;
                }else{
                    R-=5;
                }
                sharpness+= increment;


            }




        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void run() {
        playAnim();
    }
}
