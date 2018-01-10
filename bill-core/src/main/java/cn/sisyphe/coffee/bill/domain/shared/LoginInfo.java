package cn.sisyphe.coffee.bill.domain.shared;

import cn.sisyphe.framework.web.exception.DataException;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import javax.servlet.http.HttpServletRequest;

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
        loginInfo.setOperatorCode(request.getHeader("userCode"));
        loginInfo.setOperatorName(request.getHeader("userName"));
        loginInfo.setStationCode(request.getHeader("currentStationCode"));
        if (StringUtils.isEmpty(loginInfo.getOperatorCode()) || StringUtils.isEmpty(loginInfo.getStationCode())) {
            throw new DataException("51000", "登录失效，请重新登录");
        }
        return loginInfo;
    }
}
