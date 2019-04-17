package sclience.service;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sclience.conf.TestInterface;
import sclience.dao.UserMapper;
import sclience.entity.User;
import sclience.entity.UserVO;
import sclience.tuil.MD5Utils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
    @Resource
    private UserMapper userMapper;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> findAllUsersByPage(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize); //开启分页配置
        List<User> users = userMapper.selectAll();
        return users;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer findTotals() {
        User user = new User();
        return userMapper.selectCount(user);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> findAllUsers() {
        return userMapper.selectAll();
    }

    @Override
    @TestInterface("添加用户")
    public void addUser(User user) {
        //生成id
        user.setId(UUID.randomUUID().toString());
        //设置注册时间
        user.setRegistTime(new Date());
        //对密码进行加密处理  密码+盐
        String salt = MD5Utils.getSalt();
        user.setPassword(MD5Utils.getPassword(user.getPassword() + salt));

    }

    @Override
    @TestInterface("更新用户")
    public void updateUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);

    }

    @Override
    @TestInterface("删除用户")
    public void deleteUser(User user) {
        userMapper.deleteByPrimaryKey(user.getId());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<UserVO> findCountGroupByProvince() {
        return userMapper.findCountGroupByProvince();
    }
}
