package sclience.service;

import org.springframework.stereotype.Service;
import sclience.dao.AdminMapper;
import sclience.entity.Admin;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;
    @Override
    public Admin login(Admin admin) {
        Admin checkAdmin = adminMapper.selectOne(admin);
        if (checkAdmin!=null){//管理员存在
            if (checkAdmin.getName().equals(admin.getName())){//用户名正确
                if (checkAdmin.getPassword().equals(admin.getPassword())){//密码正确
                    return checkAdmin;
                }else {
                    throw new RuntimeException("密码输入有误！");
                }
            }else{
                throw new RuntimeException("用户名输入有误！");
            }
        }else {
            throw new RuntimeException("用户不存在！");
        }
    }
}
