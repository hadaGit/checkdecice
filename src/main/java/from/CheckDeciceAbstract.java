package from;



import from.checkdecice.CheckDevice;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 校验器超类
 * Created by soqi103 on 2016/12/16.
 */
public class CheckDeciceAbstract {
    public List<CheckDevice> cds = new ArrayList<CheckDevice>();

    public <T> FromCheckResult check(T obj) {
        Field fields[] = obj.getClass().getDeclaredFields();//获得对象所有属性
        Field field;
        FromCheckResult fromCheckResult = null;
        for (int i = 0; i < fields.length; i++) {
            field = fields[i];
            field.setAccessible(true);//修改访问权限
            ParamCheck annotation = field.getAnnotation(ParamCheck.class);
            if (annotation != null) {
                for (CheckDevice cd : cds) {//属性进行校验器迭代
                    fromCheckResult = new FromCheckResult();//默认校验通过
                    fromCheckResult.setResult(true);
                    fromCheckResult.setErrorMsg("校验通过");
                    Object value;
                    try {
                        value = field.get(obj);
                    } catch (IllegalAccessException e) {//遇到错误，结束校验迭代，并设置校验迭代结果 false
                        fromCheckResult.setResult(false);
                        fromCheckResult.setErrorMsg("获取属性出错");
                        break;
                    }
                    if (annotation.type().equals(cd.getType())) {//过滤校验器想要的值
                        fromCheckResult = cd.check(value, annotation.msg());
                        if (fromCheckResult == null) {//校验器校验结果返回null
                            fromCheckResult = new FromCheckResult();
                            fromCheckResult.setResult(false);
                            fromCheckResult.setErrorMsg("校验消息：" + annotation.msg() + "   校验类型：" + annotation.type() + "   当前属性值:" + value);
                            break;
                        }else{
                            if(!fromCheckResult.isResult()){//校验器校验结果，不通过，结束校验器迭代
                                break;
                            }
                        }
                    }
                }
                if (!fromCheckResult.isResult()) {//校验结束后，判断当前字段校验结果是否失败，失败直接返回失败结果，通过继续下一个字段校验
                    return fromCheckResult;
                }
            }
        }
        return fromCheckResult;
    }

    /**
     * 注册校验器
     * @param checkDevice
     * @return
     */
    public boolean registerCheckDevice(CheckDevice checkDevice) {
        return cds.add(checkDevice);
    }
}
