//package hw7;
class Timer extends Thread{
    private int time = 1;
    private boolean setting = false;

    public void run(){
        while(true){
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.print(time + " ");
            setTime(time + 1);
      
        }
    }
    public synchronized int getTime(){
        while(setting){
            try {
                wait(); 
            } catch (InterruptedException e) {  }
        }

        return time;
    }
    public synchronized void setTime(int t){
        setting = true;
        this.time = t;
        setting = false;
        notifyAll();
    }
}

class Timer5 extends Thread{
    Timer timer;
    public Timer5(Timer t){
        this.timer = t;
    }

    public void run(){
        synchronized(timer){
            while(true){

                try {
                    timer.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(timer.getTime() % 5 == 1){
                    System.out.print("\n5 Second Message\n");
                }

            }
        }
    }
}

class Timer11 extends Thread{
    Timer timer;
    public Timer11(Timer t){
        this.timer = t;
    }

    public void run(){
        synchronized(timer){
            while(true){

                try {
                    timer.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(timer.getTime() % 11 == 1){
                    System.out.print("\n11 Second Message\n");
                }

            }
        }
    }
}

public class Test2 {

    public static synchronized void main(String[] args) {

        Timer timer = new Timer();
        timer.start();

        Timer5 t5 = new Timer5(timer);
        t5.start();

        Timer11 t11 = new Timer11(timer);
        t11.start();

    }

}



