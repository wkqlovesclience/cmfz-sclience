package sclience.service;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sclience.dao.LogMapper;
import sclience.entity.Log;

import javax.annotation.Resource;
import java.util.List;
@Service
@Transactional
public class LogServiceImpl implements LogService {
    @Resource
    private LogMapper logMapper;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Log> findAllLogs(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);//开启分页
        return logMapper.selectAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer findTotals() {
        Log log = new Log();
        return logMapper.selectCount(log);
    }
}
