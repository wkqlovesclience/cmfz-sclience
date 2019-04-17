package sclience.service;

import org.springframework.stereotype.Service;
import sclience.conf.TestInterface;

@Service
public class TestImpl implements Test{
    @Override
    //测试自定义注解
    @TestInterface(value = "用户的测试方法")
    public void test() {
        System.out.println("测试自定义注解");
    }
}
