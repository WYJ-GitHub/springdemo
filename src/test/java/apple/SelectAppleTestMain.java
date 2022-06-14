package apple;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: springdemo
 * @description
 * @author: 吴云杰
 * @create: 2022-06-14 15:22
 **/
public class SelectAppleTestMain {


    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            Apple apple = new Apple();
            if (i < 20 || i > 80 && i < 160) {
                apple.setColor("red");
            } else {
                apple.setColor("green");
            }
            if (i < 10 || i > 50 && i < 80 || i > 122 && i < 156 || i > 189) {
                apple.setName("红富士");
            } else {
                apple.setName("金冠");
            }
            apple.setWeight(i);
            apples.add(apple);
        }
        //1. 将条件参数化，自己定义条件，将条件处理结果返回即可
        AppleCondition appleCondition = new AppleCondition() {
            @Override
            public boolean condition(Apple apple) {
                if (apple.getColor().equals("red")
                        && apple.getName().equals("红富士")
                        && apple.getWeight() > 100) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        AppleFilter appleFilter = new AppleFilter();
        apples = appleFilter.AppleFilter(apples, appleCondition);
        /*
        2. 此种方法不适用，遇见int具有初始值，筛选则走不通
        Apple apple = new Apple();
        apple.setColor("red");
        apple.setName("红富士");
        AppleFilter appleFilter = new AppleFilter();
        apples = appleFilter.AppleFilter(apples,apple);
         */
        for (Apple a :
                apples) {
            System.out.println(a.toString());
        }
    }
}
