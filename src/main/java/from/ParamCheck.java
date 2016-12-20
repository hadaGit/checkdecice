package from;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 属性注解                                                                 <br>
 * type ["text"|"","number","email","phone","money","decimal","idCard"]     <br>
 * &nbsp;&nbsp; number -- 数字类型                                          <br>
 * &nbsp;&nbsp; email -- 邮箱                                               <br>
 * &nbsp;&nbsp; phone -- 电话                                               <br>
 * &nbsp;&nbsp; money -- 金额，不能为负数金额                               <br>
 * &nbsp;&nbsp; decimal -- 可以为负数的金额                                 <br>
 * &nbsp;&nbsp; idCard -- 省份证                                            <br>
 * msg 错误时提示信息                                                       <br>
 * @author lwd
 * @date 2016年12月15日20:36:59
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamCheck {
    String type() default "text";
    String msg() default "提示消息";
}
