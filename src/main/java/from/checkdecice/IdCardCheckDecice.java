package from.checkdecice;


import from.CheckParamUtil;
import from.FromCheckResult;

/**
 * 身份证校验器    <br>
 * 大陆身份证
 * Created by soqi103 on 2016/12/19.
 */
public class IdCardCheckDecice implements CheckDevice {
    @Override
    public FromCheckResult check(Object value, String annotationMsg) {
        FromCheckResult fromCheckResult = new FromCheckResult();
        if (!CheckParamUtil.isStringOnly((String) value)) {
            fromCheckResult.setErrorMsg(annotationMsg + "不能为空");
            fromCheckResult.setResult(false);
            return fromCheckResult;
        }
        if (!CheckParamUtil.isIdCardFormatLegal((String) value)) {
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
        return "idCard";
    }
}
