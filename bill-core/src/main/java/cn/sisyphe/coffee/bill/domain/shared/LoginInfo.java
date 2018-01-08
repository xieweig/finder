package cn.sisyphe.coffee.bill.domain.shared;

import cn.sisyphe.framework.web.exception.DataException;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

/**
 * @author Amy on 2018/1/8.
 *         describe：登录信息
 */
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginInfo {
    private String operatorCode;
    private String operatorName;
    private String stationCode;
    private String token;

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginInfo() {

    }

    public LoginInfo(String operatorCode, String stationCode) {
        this.operatorCode = operatorCode;
        this.stationCode = stationCode;
    }

    public LoginInfo(String operatorCode, String operatorName, String stationCode) {
        this.operatorCode = operatorCode;
        this.operatorName = operatorName;
        this.stationCode = stationCode;
    }

    public static LoginInfo getLoginInfo(HttpServletRequest request) throws DataException {
        LoginInfo loginInfo = new LoginInfo();
        //TODO 后面修改 唐华玲
        try {
            Cookie[] cookies = request.getCookies();
            loginInfo.setOperatorCode(obtainCookieValue(cookies, "userCode"));
            loginInfo.setOperatorName(obtainCookieValue(cookies, "userName"));
            loginInfo.setStationCode(obtainCookieValue(cookies, "currentStationCode"));
        } catch (Exception e) {
            System.out.println("没有找到cookies");
        }
        if (loginInfo.getOperatorCode() == null) {
            loginInfo.setOperatorCode(request.getHeader("userCode"));
            loginInfo.setOperatorName(request.getHeader("userName"));
            loginInfo.setStationCode(request.getHeader("currentStationCode"));
        }
        if (loginInfo.getOperatorCode() == null) {
            loginInfo.setOperatorCode("YGADMIN");
            loginInfo.setOperatorName("超级管理员");
            loginInfo.setStationCode("HDQA00");
        }

        if (StringUtils.isEmpty(loginInfo.getOperatorCode()) || StringUtils.isEmpty(loginInfo.getStationCode())) {
            throw new DataException("51000", "登录失效，请重新登录");
        }
        return loginInfo;
    }


    private static String obtainCookieValue(Cookie[] cookies, String cookiesName) throws DataException {
        try {
            String value = "";
            for (Cookie cookieItem : cookies) {
                String itemName = URLDecoder.decode(cookieItem.getName(), "utf-8");
                if (cookiesName.equals(itemName)) {
                    value = URLDecoder.decode(cookieItem.getValue(), "utf-8");
                    break;
                }
            }
            return value;
        } catch (Exception e) {
            throw new DataException("41011", "读取cookie出错了");
        }
    }

}
