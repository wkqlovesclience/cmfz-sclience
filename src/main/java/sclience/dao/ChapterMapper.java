package sclience.dao;

import sclience.entity.Chapter;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ChapterMapper extends Mapper<Chapter> {
    /**
     * 联表进行分页查询
     */
    List<Chapter> findAlbumAndChapterByPage();

}