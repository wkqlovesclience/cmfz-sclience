package sclience.service;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sclience.dao.TurnMapper;
import sclience.entity.Turn;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TurnServiceImpl implements TurnService {
    @Resource
    private TurnMapper turnMapper;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Turn> findAllTurnsByPage(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize); //开启分页配置
        List<Turn> turns = turnMapper.selectAll();
        return turns;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer findTotals() {
        Turn turn = new Turn();
        return turnMapper.selectCount(turn);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Turn findById(Turn turn) {
        return turnMapper.selectByPrimaryKey(turn.getId());
    }

    @Override
    public void addTurn(Turn turn) {
        //生成id
        turn.setId(UUID.randomUUID().toString());
        //文件上传日期
        turn.setUploadTime(new Date());
        turnMapper.insert(turn);
    }

    @Override
    public void updateTurn(Turn turn) {
        turnMapper.updateByPrimaryKeySelective(turn);
    }

    @Override
    public void deleteTurn(Turn turn) {
        turnMapper.delete(turn);
    }
}
