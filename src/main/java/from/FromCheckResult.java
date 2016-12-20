package from;

/**
 * 表单校验结果
 * Created by soqi103 on 2016/12/14.
 */
public class FromCheckResult {
    /**
     * 表单校验结果 true校验通过，false校验失败
     */
    private boolean result;
    /**
     * 校验失败时失败的信息
     */
    private String errorMsg;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
