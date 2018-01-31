package cn.sisyphe.coffee.bill.util;

import cn.sisyphe.framework.auth.logic.AuthenticationSupplier;
import cn.sisyphe.framework.auth.logic.ScopeAuthenticationAdapter;
import cn.sisyphe.framework.web.exception.AccessDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @Date 2018/1/30 17:53
 * @description
 */

@Service
public class BackendAuthService {

    private AuthenticationSupplier authenticationSupplier;

    private final ScopeAuthenticationAdapter scopeAuthenticationAdapter;


    @Autowired
    public BackendAuthService(AuthenticationSupplier authenticationSupplier) {
        this.authenticationSupplier = authenticationSupplier;
        this.scopeAuthenticationAdapter = new ScopeAuthenticationAdapter(authenticationSupplier);
    }


    public void validateAuthScope(String userCode, Set<String> stationCodes) {
        Set<String> authorizedScope = scopeAuthenticationAdapter.identifyPermissionScope(userCode, stationCodes);
        if (authorizedScope == null || authorizedScope.size() <= 0) {
            throw new AccessDeniedException("101", "没有可访问的权限范围");
        }
    }
}
