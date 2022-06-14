package springdemo.utils;

import java.util.Collection;
import java.util.Optional;

/**
 * @program: springdemo
 * @description
 * @author: 吴云杰
 * @create: 2022-06-14 16:02
 **/
public class CommonUtils {
    public static <E> boolean isNull(E optional){
        return optional == null || "".equals(optional);
    }
    public static <E> boolean isNotNull(E e){
        return !isNull(e);
    }
    public static boolean isNull(Optional optional){
        return optional == null || "".equals(optional);
    }
    public static boolean isNull(String str){
        return str == null || "".equals(str.trim());
    }
    public static boolean isNull(Optional[] optionals){
        return optionals == null || optionals.length == 0;
    }
    public static boolean isNull(Collection<?> collection){
        return collection == null || collection.size() == 0;
    }
    public static boolean isNull(int[] array) {
        return array == null || array.length == 0;
    }
    public static boolean isNull(byte[] array) {
        return array == null || array.length == 0;
    }
    public static boolean isNull(short[] array) {
        return array == null || array.length == 0;
    }
    public static boolean isNull(double[] array) {
        return array == null || array.length == 0;
    }
    public static boolean isNull(float[] array) {
        return array == null || array.length == 0;
    }
    public static boolean isNull(char[] array) {
        return array == null || array.length == 0;
    }
    public static boolean isNotNull(Optional optional){
        return !isNull(optional);
    }
    public static boolean isNotNull(String str){
        return !isNull(str);
    }
    public static boolean isNotNull(Optional[] optionals){
        return !isNull(optionals);
    }
    public static boolean isNotNull(Collection<?> collection){
        return !isNull(collection);
    }
    public static boolean isNotNull(int[] array) {
        return !isNull(array);
    }
    public static boolean isNotNull(byte[] array) {
        return !isNull(array);
    }
    public static boolean isNotNull(short[] array) {
        return !isNull(array);
    }
    public static boolean isNotNull(double[] array) {
        return !isNull(array);
    }
    public static boolean isNotNull(float[] array) {
        return !isNull(array);
    }
    public static boolean isNotNull(char[] array) {
        return !isNull(array);
    }

}
