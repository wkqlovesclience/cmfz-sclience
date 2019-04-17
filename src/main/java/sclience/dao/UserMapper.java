package sclience.dao;

import sclience.entity.User;
import sclience.entity.UserVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {
    /**
     * 根据省份查询用户人数
     */
    List<UserVO> findCountGroupByProvince();
    /**
     * 统计某一时间段内用户的注册情况
     */
    Integer findByRegistTime(Integer days);

}