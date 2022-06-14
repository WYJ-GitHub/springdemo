package apple;

import springdemo.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: springdemo
 * @description
 * @author: 吴云杰
 * @create: 2022-06-14 15:23
 **/
public class AppleFilter {

    /**
     * @param apples    待筛选集合
     * @param appleCondition         筛选的条件
     * @return
     */
    public List<Apple> AppleFilter(List<Apple> apples,AppleCondition appleCondition){
        List<Apple> list = new ArrayList<>();
        for (Apple apple : apples) {
            if (appleCondition.condition(apple)){
                list.add(apple);
            }
        }
        return list;
    }

    /**此方法不适用，遇见int具有初始值，筛选则走不通
     * @param apples    待筛选集合
     * @param a         筛选的条件
     * @return
     */
    public List<Apple> AppleFilter(List<Apple> apples,Apple a){
        if (CommonUtils.isNull(a)){
            return apples;
        }
        List<Apple> list = new ArrayList<>();
        for (Apple apple : apples) {
            boolean flag = true;
            if (CommonUtils.isNotNull(a.getName()) && !a.getName().equals(apple.getName())){
                flag = false;
            }
            if (CommonUtils.isNotNull(a.getColor()) && !a.getColor().equals(apple.getColor())){
                flag = false;
            }
            if (CommonUtils.isNotNull(a.getWeight()) && a.getWeight()!= apple.getWeight()){
                flag = false;
            }
            if (flag){
                list.add(apple);
            }
        }
        return list;
    }
}
