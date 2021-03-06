package cn.sisyphe.coffee.bill.infrastructure.share.user.repo;

import cn.sisyphe.coffee.bill.infrastructure.share.user.UserCloudRepository;
import cn.sisyphe.framework.web.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XiongJing on 2018/1/2.
 * remark：SpringCloud方式查询用户信息接口实现
 * version: 1.0
 *
 * @author XiongJing
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserCloudRepository userCloudRepository;

    /**
     * 根据用户名称查询用户编码集合
     *
     * @param userName 用户名称
     * @return
     */
    @Override
    public List<String> findByLikeUserName(String userName) {

        ResponseResult responseResult = userCloudRepository.findByLikeUserName(userName);
        Map<String, Object> resultMap = responseResult.getResult();
        if (!resultMap.containsKey("result")) {
            return Collections.singletonList("NOT_EXISTS");
        }
        List<String> userCodeList = (ArrayList) resultMap.get("result");
        if (userCodeList == null || userCodeList.size() <= 0) {
            userCodeList.add("NOT_EXISTS");
            return userCodeList;
        }
        return userCodeList;
    }

    @Override
    public String findOneByUserCode(String userCode) {
        ResponseResult responseResult = userCloudRepository.findOneByUserCode(userCode);
        Map<String, Object> resultMap = responseResult.getResult();
        if (resultMap.containsKey("user") && (resultMap.get("user")) != null) {
            return (String) ((HashMap) resultMap.get("user")).get("userName");
        }
        return null;
    }

}
