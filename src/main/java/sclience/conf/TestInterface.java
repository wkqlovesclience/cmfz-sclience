package sclience.conf;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//何时生效  Runtime 运行时生效 SOURCE 生效  CLASS 生效
@Retention(RetentionPolicy.RUNTIME)
//在哪里加入注解
@Target({ElementType.METHOD})
public @interface TestInterface {
    String value();
}
