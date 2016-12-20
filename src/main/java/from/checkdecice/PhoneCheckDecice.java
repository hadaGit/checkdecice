package from.checkdecice;


import from.CheckParamUtil;
import from.FromCheckResult;

/**
 * 电话校验器    <br>
 *     目前支持,大陆电话
 * Created by soqi103 on 2016/12/19.
 */
public class PhoneCheckDecice implements CheckDevice {
    @Override
    public FromCheckResult check(Object value, String annotationMsg) {
        FromCheckResult fromCheckResult = new FromCheckResult();
        if (!CheckParamUtil.isStringOnly((String) value)) {
            fromCheckResult.setErrorMsg(annotationMsg + "不能为空");
            fromCheckResult.setResult(false);
            return fromCheckResult;
        }else if(!CheckParamUtil.isMobileFormatLegal((String) value)){
            fromCheckResult.setErrorMsg(annotationMsg + "格式错误");
            fromCheckResult.setResult(false);
            return fromCheckResult;
        }
        fromCheckResult.setResult(true);
        fromCheckResult.setErrorMsg("校验通过");
        return fromCheckResult;
    }

    @Override
    public String getType() {
        return "phone";
    }
}
