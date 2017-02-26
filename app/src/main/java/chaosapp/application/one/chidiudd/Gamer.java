package chaosapp.application.one.chidiudd;

import java.util.Random;

/**
 * Created by nishtha jain on 15-05-2016.
 */
public class Gamer implements Runnable{

    Random randomIndex=new Random();
    String name;
    int size=Animals.getSize();
    boolean flag=false;
    boolean noTouch=false;
    int i=0;
    int randomInt;
    int count=0;

    void updateItem() {
        while (flag){
            randomInt = randomIndex.nextInt(size);
            name=Animals.getName(randomInt);

            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            i++;
            if(count!=i){
                flag=false;
                noTouch=true;
            }

        }


    }
    public void incCount(){
        count++;
    }
    public boolean isOverT(){
        if(noTouch==true){
            return true;
        }else
            return false;
    }
    public boolean isOver(){
        if(flag==false){
            return true;
        }else
            return false;
    }
    public void start(){
       // flag=true;
        noTouch=false;
    }

    public void stop(){
        flag=false;
    }

    public int getIndex()
    {
        return randomInt;
    }

    public String getName()
    {
        return name;
    }

    public int getScore(){

       return count;
    }

    @Override
    public void run() {

        flag=true;

        //i=-1;
        //count=-1;
       this.updateItem();

    }
}
