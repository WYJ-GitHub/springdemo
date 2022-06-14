package springdemo.core.annotation;

/**
 * @ClassName 外部字段（用来标记实体，如果被标记，则说明不是数据库中的字段，是为了配合前台或者其他原因所加的字段，相当于注释作用）
 * @Author zrx
 * @Date 2021/7/1 11:53
 */
public @interface ExternalField {
	String value() default "";
}
