package from;


import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckParamUtil {
	/**
	 * 检测多参数都不为空  当有一个参数为空时 返回当前为空的参数的下标的负值
	 * @param strings
	 * @return  都不为空 返回 1 ,否则 返回当前为空的参数的下标的负值;
	 */
	public static int isNotEmptyParams(String... strings){
		int result = 1;
		for (int i = 0; i < strings.length; i++) {
			if(!isStringOnly(strings[i])){
				result = (i+1) * (-1);
				break;
			}
		}
		return result;
	}

	/**
	 * 字符串不为空
	 * 检查是否存字符串,可以包括数字、字母、各种符号
	 * @param param
	 * @return
	 */
	public static boolean isStringOnly(String param){
		return StringUtils.isNotBlank(param);
	}

	/**
	 * is (not empty text) && (text > length) 
	 * @param text
	 * @param length
	 * @return
	 */
	public static boolean isStringLongerThan(String text, int length){
		if(StringUtils.isNotBlank(text)){
			return text.trim().length() > length;
		}
		return false;
	}

	/**
	 * is (not empty text) && (text < length) 
	 * @param text
	 * @param length
	 * @return
	 */
	public static boolean isStringShorterThan(String text, int length){
		if(StringUtils.isNotBlank(text)){
			return text.trim().length() < length;
		}
		return false;
	}
	
	/**
	 * is (not empty text) && (minLength <= text.trim() <= maxLength) 
	 * @param text
	 * @param minLength 兼容从0开始
	 * @param maxLength
	 * @return
	 */
	public static boolean isStringLengthIn(String text, int minLength, int maxLength){
		if(minLength > 0 && StringUtils.isBlank(text)){
			return false;
		}
		int i = (text == null ? "" : text.trim()).length();
		return minLength <= i && i <= maxLength;
	}
	
	/**
	 * is text format as number only 
	 * @param text
	 * @return
	 */
	public static boolean isNumberOnly(String text){
		return StringUtils.isNotBlank(text) && Pattern.compile("^\\d*$").matcher(text).matches();
	}
	
	/**
	 * is (text format as number only) && (text.length > length) 
	 * @param text
	 * @param length
	 * @return
	 */
	public static boolean isNumberTextLongerThan(String text, int length){
		return isNumberOnly(text) && text.length() > length;
	}
	
	/**
	 * is (text format as number only) && (text.length < length)
	 * @param text
	 * @param length
	 * @return
	 */
	public static boolean isNumberTextShorterThan(String text, int length){
		return isNumberOnly(text) && text.length() < length;
	}
	
	/**
	 * is (text format as number only) && (minLength <= text.length <= maxLength)
	 * @param text
	 * @param minLength
	 * @param maxLength
	 * @return
	 */
	public static boolean isNumberTextLengthIn(String text, int minLength, int maxLength){
		return isNumberOnly(text) 
				&& minLength <= text.length() 
				&& text.length() <= maxLength;
	}

	/**
	 * 文本过滤
	 * @param param
	 * @return
	 * @modify by hyh, replace --> replaceAll
	 */
	public static String filterText(String param){
		if(StringUtils.isNotBlank(param)){
			param = param.trim();
			param = param.replaceAll("&", "&amp;");
			param = param.replaceAll("\"","&quot;");
			param = param.replaceAll("<", "&lt;");
			param = param.replaceAll(">", "&gt;");
		}
		return param;
	}
	
	/**
	 * 截取银行卡账户长度
	 * @param card
	 * @param length
	 * @return
	 */
	public static String subBankCard(String card, int length){
		if(StringUtils.isNotBlank(card) && card.length() > length){
			card = card.substring(card.length() - length, card.length());
		}
		return card;
	}
	
	/**
	 * 电话号码中间四位数字替换型号
	 * @param mobile
	 * @return
	 */
	public static String subMobile(String mobile){
		return subStr(mobile, 3, 4);
	}
	/**
	 * 电话号码中间四位数字替换型号
	 * @param param
	 * @param index
	 * @param length
	 * @return
	 */
	public static String subStr(String param, int index, int length){
		if(StringUtils.isNotBlank(param) && param.length() > (index + length)){
			String before = param.substring(0, index);
			String after = param.substring(index + length);
			return before + "****" + after;
		}
		return null;
	}

	/**
	 * 校验金额，不能为负数金额
	 * @param money
	 * @return
     */
	public static boolean isMoneyFormatLegal(String money){
		if(StringUtils.isBlank(money)) return false;
		Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){1,2})?$");
		Matcher matcher = pattern.matcher(money);
		return matcher.matches();
	}

	/**
	 * 检查身份证是否合法
	 * @param icard
	 * @return
	 */
	public static boolean isIdCardFormatLegal(String icard){
		boolean flag = false;
		String regexStr = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";
		if(StringUtils.isNotBlank(icard)){
			if(icard.matches(regexStr)){
				flag = true;
			}
		}
		if(!flag) {
			flag = checkGATIdCard(icard);
		}
		return flag;
	}

	/**
	 * 检查邮箱格式是否合法
	 * @param mail
	 * @return
	 */
	public static boolean isMailForamtLegal(String mail){
		if(StringUtils.isBlank(mail)) return false;
		Pattern pattern = Pattern.compile("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$");
		Matcher matcher = pattern.matcher(mail);
		return matcher.matches();
	}
	
	/**
	 * 校验电话号码格式是否正确	<br>
	 * 仅大陆那种1开头，11位的	<br>
	 * 不包括其它地区（港澳台）等	<br>
	 * 港澳台格式参见			<br> 
	 * 台湾：isTaiWanMobile	<br>
	 * 香港：isHongKongMobile	<br>
	 * 澳门：isAoMenMobile	<br>
	 * @param mobile
	 * @return
	 */
	public static boolean isMobileFormatLegal(String mobile){
		if(mobile==null || mobile.length() != 11) {
			return false;
		}
		return Pattern.compile("[1][3,4,5,7,8][0-9]{9}").matcher(mobile).matches();
	}
	
	/**
	 * 校验电话号码格式是否正确				<br>
	 * 仅台湾地区的，以00860开头，后面9位的		<br>
	 * 不包括香港、澳门、大陆地区的格式			<br>
	 * @param mobile
	 * @return
	 */
	public static boolean isTaiWanMobile(String mobile){
		if(mobile==null || mobile.length() != 14) {
			return false;
		}
		return Pattern.compile("^(00860)[0,9][0-9]{8}$").matcher(mobile).matches();
	}
	
	/**
	 * TODO
	 * 校验电话号码格式是否正确				<br>
	 * 仅澳门地区的，以00860开头，后面9位的		<br>
	 * 不包括台湾、香港、大陆地区的格式			<br>
	 */
	public static boolean isAoMenMobile(String mobile){
		if(mobile==null || mobile.length() != 13) {
			return false;
		}
		return Pattern.compile("^(00853)[6]\\d{7}$").matcher(mobile).matches();
	}
	
	/**
	 * TODO
	 * 校验电话号码格式是否正确				<br>
	 * 仅香港地区的，以00860开头，后面9位的		<br>
	 * 不包括台湾、澳门、大陆地区的格式			<br>
	 */
	public static boolean isHongKongMobile(String mobile){
		if(mobile==null || mobile.length() != 13) {
			return false;
		}
		return Pattern.compile("^(00852)([6|9])\\d{7}$").matcher(mobile).matches();
	}
	
	/**
	 * @todo 校验登录密码格式是否正确
	 * @param login_pwd
	 * @return
	 */
	public static boolean isLoginPwdLegal(String login_pwd){
		if(login_pwd==null|| login_pwd.length()<6) return false;
		return Pattern.compile("[_\\da-zA-Z]*").matcher(login_pwd).matches();
	}
	
	/**
	 * @todo 校验支付密码格式是否正确
	 * @param pay_pwd
	 * @return
	 */
	public static boolean isPayPwdLegal(String pay_pwd){
		if(pay_pwd==null|| pay_pwd.length()<6) return false;
		return Pattern.compile("^[0-9]{6}$").matcher(pay_pwd).matches();
	}
	
	/**
	 * 校验数字字符串的范围
	 * <p>用途，某些controller类型参数只需要一位数，有时0和1就够了，原来方法会漏掉3456等的校验<br>
	 * @param numstr like "3"
	 * @param max like 2
	 * @notice is "0" in "0-0" 就不要调用这个了
	 * @return "3" in "012"?
	 * @author hyh
	 */
	public static boolean isNumberStrInGivenEdge(String numstr, int max){
		if(numstr == null || numstr.length() != 1 || max < 1 || max>9){
			return false;
		}
		switch(max){
		case 1:
			return numstr.matches("^[01]$");
		case 2:
			return numstr.matches("^[012]$");
		case 3:
			return numstr.matches("^[0123]$");
		case 4:
			return numstr.matches("^[01234]$");
		case 5:
			return numstr.matches("^[012345]$");
		case 6:
			return numstr.matches("^[0123456]$");
		case 7:
			return numstr.matches("^[01234567]$");
		case 8:
			return numstr.matches("^[012345678]$");
		case 9:
			return numstr.matches("^[0-9]$");
		default : 
			return false;
		}
	}
	
	/**
	 * 用于校验是否为正浮点数，<br>
	 * 校验金额请用isMoneyFormatLegal
	 * @param decimal
	 * @return
	 */
	public static boolean isDecimalFormatLegal(String decimal){
		return StringUtils.isNotBlank(decimal) 
		&& Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d)*)?$").matcher(decimal).matches();
	}

	public static boolean isUrlFormatLegal(String url) {
		if(StringUtils.isBlank(url)){
			return false;
		}
		url = url.trim();
		return url.startsWith("http://") || url.startsWith("https://");
	}
	
	public static boolean isThisAPictureData(String imageString){
		if(imageString == null){
			return false;
		}else if(imageString.startsWith("data:image/jpeg;base64,")){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isThisAPictureUrl(String imageString){
		if(imageString == null){
			return false;
		}else if(imageString.startsWith("http://") && imageString.indexOf(".jpg") != -1){
			return true;
		}
		return false;
	}
	
	/**
	 * 检查港，澳，台身份证是否合法
	 * @param icard
	 * @return
	 */
	private static boolean checkGATIdCard(String icard){

		String reg1 = "^[A-Z]{1,2}[0-9]{6}[|(]?[0−9A−Z][|)]?$";//香港格式1 (香港身份证号码结构：XYabcdef(z))
		String reg2 = "^[A-Z][0-9]{7,12}$";//香港格式2 (H60152555)

		String reg3 = "^[1|5|7][0-9]{6}[|(]?[0−9A−Z][|)]?$";//澳门,8位数,不包含出生年月 格式为 xxxxxxx(x) 注:x全为数字,无英文字母 首位数只有1、5、7字开头的
		String reg4 = "^[a-zA-Z][0-9]{9}$";//台湾:10位字母和数字

		return icard.matches(reg1) || icard.matches(reg2) || icard.matches(reg3) || icard.matches(reg4);

	}

	/**
	 * 校验表单对象带 @ParamCheck(type = "email",msg = "审核邮箱")								<br>
	 * 注解的字段type ["text"|"","number","email","phone","money","decimal","idCard"]			<br>
	 * @param obj 带校验的表单对象
     * @return 返回一个表单检验结果
     */
	public static <T> FromCheckResult checkParamByObj(T obj){
		CheckDeciceDefault checkDeciceDefault = new CheckDeciceDefault();
		return checkDeciceDefault.check(obj);
	}
}
