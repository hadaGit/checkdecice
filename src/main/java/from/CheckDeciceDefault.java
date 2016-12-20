package from;


import from.checkdecice.DecimalCheckDecice;
import from.checkdecice.EmailCheckDecice;
import from.checkdecice.IdCardCheckDecice;
import from.checkdecice.MoneyCheckDecice;
import from.checkdecice.NumberCheckDecice;
import from.checkdecice.PhoneCheckDecice;
import from.checkdecice.TextCheckDecice;

/**
 * 默认校验器
 * Created by soqi103 on 2016/12/16.
 */
public class CheckDeciceDefault extends CheckDeciceAbstract {
    /**
     * 默认校验器
     */
    public CheckDeciceDefault() {
        registerCheckDevice(new TextCheckDecice());//文本校验器
        registerCheckDevice(new NumberCheckDecice());//数字检验器
        registerCheckDevice(new EmailCheckDecice());//邮箱校验器
        registerCheckDevice(new MoneyCheckDecice());//金钱检验器
        registerCheckDevice(new DecimalCheckDecice());//浮点数检验器
        registerCheckDevice(new PhoneCheckDecice());//电话检验器
        registerCheckDevice(new IdCardCheckDecice());//身份证检验器
    }
}
