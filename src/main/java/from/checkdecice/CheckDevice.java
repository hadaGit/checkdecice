package from.checkdecice;


import from.FromCheckResult;

/**
 * 校验器接口
 * Created by soqi103 on 2016/12/16.
 */
public interface CheckDevice {
    /**
     * 校验方法
     * @param value
     * @param annotationMsg
     * @return
     */
    FromCheckResult check(Object value, String annotationMsg);

    /**
     * 校验类型
     * @return
     */
    String getType();
}
