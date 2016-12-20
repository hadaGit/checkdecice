package from.checkdecice;


import from.CheckParamUtil;
import from.FromCheckResult;

/**
 * 文本校验器    <br>
 *     判断不为空
 * Created by soqi103 on 2016/12/19.
 */
public class TextCheckDecice implements CheckDevice {
    @Override
    public FromCheckResult check(Object value, String annotationMsg) {
        FromCheckResult fromCheckResult = new FromCheckResult();
        if (!CheckParamUtil.isStringOnly((String) value)) {
            fromCheckResult.setErrorMsg(annotationMsg + "不能为空");
            fromCheckResult.setResult(false);
            return fromCheckResult;
        }
        fromCheckResult.setResult(true);
        fromCheckResult.setErrorMsg("校验通过");
        return fromCheckResult;
    }

    @Override
    public String getType() {
        return "text";
    }
}
