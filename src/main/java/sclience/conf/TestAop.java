package sclience.conf;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sclience.dao.LogMapper;
import sclience.entity.Admin;
import sclience.entity.Log;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

@Aspect
@Configuration
public class TestAop {
    @Resource
    private LogMapper logMapper;
    @Pointcut("@annotation(testInterface)")
    public void pointcut(TestInterface testInterface){}

    @Around("pointcut(testInterface)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint,TestInterface testInterface){
        Log log = new Log();
        //生成id
        log.setId(UUID.randomUUID().toString());
        //WHO
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("loginAdmin");
        if (admin!=null){
            log.setAdminName(admin.getName());
        }
        //WHEN
        log.setCreateDate(new Date());
        //WHAT
        String value = testInterface.value();
        log.setOperation(value);
        System.out.println(value);
        try {
            Object proceed = proceedingJoinPoint.proceed();
            //success
            System.out.println("-----------proceed--------"+proceed);
            log.setSign("操作成功");
            logMapper.insertSelective(log);
            //记录日志
            return proceed;
        }catch (Throwable throwable){
            throwable.printStackTrace();
            //fail
            log.setSign("操作失败");
            logMapper.insertSelective(log);
            //记录日志
            return null;
        }
    }
}
