package sclience.dao;

import sclience.entity.Album;
import tk.mybatis.mapper.common.Mapper;

public interface AlbumMapper extends Mapper<Album> {
    /**
     * 联表分页查询
     */
    //List<Album> findAlbumAndChaptersByPage(@Param("start") Integer start, @Param("rows") Integer rows);

}