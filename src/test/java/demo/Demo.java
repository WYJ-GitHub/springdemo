package demo;

/**
 * @program: springdemo
 * @description
 * @author: 吴云杰
 * @create: 2022-06-15 09:36
 **/
public class Demo {
    public final int value = 4;
    public void doInt(){
        Runnable runnable = new Runnable() {
            int value = 5;
            @Override
            public void run() {
                int value = 10;
                System.out.println(this.value);
            }
        };
        runnable.run();
    }
    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.doInt();
    }

}
