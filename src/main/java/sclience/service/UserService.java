package sclience.service;

import sclience.entity.User;

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
}
