package sclience.service;

import sclience.entity.Chapter;

import java.util.List;

public interface ChapterService {
    /**
     * 分页查询
     */
    List<Chapter> findAllChaptersByPage(Integer pageNum,Integer pageSize);
    /**
     * 获取总记录数
     */
    Integer findTotals();
    /**
     * 根据专辑id获取每个专辑下的章节数
     */
    Integer findCount(String albumId);
    /**
     * 删除
     */
    void deleteChapter(Chapter chapter);
    /**
     * 上传
     */
    void uploadChapter(Chapter chapter);
    /**
     * 根据id获取Chapter
     */
    Chapter findOne(Chapter chapter);
    /**
     * 修改
     */
    void updateChapter(Chapter chapter);

}
