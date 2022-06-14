/**
 * @program: springdemo
 * @description
 * @author: 吴云杰
 * @create: 2022-06-10 17:36
 **/
public class Novisibilitty {
    private static boolean ready;
    private static int number;
    private static class ReaderThread extends Thread{
        int i =1 ;
        @Override
        public void run() {
            while (!ready){
                Thread.yield();
                System.out.println("i= " + i + ": "+ number);
            }
        }
    }
    public static void main(String[] args) {

        new ReaderThread().start();
        number = 42;
        ready = false;
    }
}
