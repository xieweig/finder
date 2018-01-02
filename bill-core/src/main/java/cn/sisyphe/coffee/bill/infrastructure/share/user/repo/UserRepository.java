package cn.sisyphe.coffee.bill.infrastructure.share.user.repo;

import java.util.List;

/**
 * Created by XiongJing on 2018/1/2.
 * remark：SpringCloud方式查询用户信息接口
 * version: 1.0
 *
 * @author XiongJing
 */
public interface UserRepository {

    /**
     * 根据用户名称查询用户编码集合
     *
     * @param userName 用户名称
     * @return
     */
    List<String> findByLikeUserName(String userName);
}
