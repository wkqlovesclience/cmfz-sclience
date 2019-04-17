package sclience.service;

import sclience.entity.User;
import sclience.entity.UserVO;

import java.util.List;

public interface UserService {
    /**
     * 获取所有用户  并进行分页
     */
    List<User> findAllUsersByPage(Integer pageNum,Integer pageSize);
    /**
     * 获取用户总记录数
     */
    Integer findTotals();
    /**
     * 查询所有
     */
    List<User> findAllUsers();
    /**
     * 添加
     */
    void addUser(User user);
    /**
     * 更新
     */
    void updateUser(User user);
    /**
     * 删除
     */
    void deleteUser(User user);
    /**
     * 根据省份查询 省份信息 及 每个省份的人数
     */
    List<UserVO> findCountGroupByProvince();
    /**
     * 统计某一时间段内用户的注册情况
     */
    Integer findByRegistTime(Integer days);
}
