package sclience.service;

import sclience.entity.Admin;

public interface AdminService {
    /**
     * 根据管理员用户名查询一名管理员  并进行登录验证
     */
    Admin login(Admin admin);
}
