package sclience.service;

import sclience.entity.Turn;

import java.util.List;

public interface TurnService {
    /**
     * 分页查询
     */
    List<Turn> findAllTurnsByPage(Integer pageNum,Integer pageSize);
    /**
     * 获取轮播图信息总条数
     */
    Integer findTotals();
    /**
     * 根据id查询
     */
    Turn findById(Turn turn);
    /**
     * 添加
     */
    void addTurn(Turn turn);
    /**
     * 更新
     */
    void updateTurn(Turn turn);
    /**
     * 删除
     */
    void deleteTurn(Turn turn);

}
