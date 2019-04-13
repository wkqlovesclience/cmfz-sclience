package sclience.service;

import sclience.entity.Album;

import java.util.List;

public interface AlbumService {
    /**
     * 获取所有专辑
     */
    List<Album> findAllAlbums();
    /**
     * 获取所有专辑 并分页展示
     */
    List<Album> findAllAlbumsByPage(Integer pageNum,Integer pageSize);
    /**
     * 获取专辑总记录数
     */
    Integer findTotals();
    /**
     * 删除
     */
    void deleteAlbum(Album album);
    /**
     * 添加
     */
    void addAlbum(Album album);
    /**
     * 更新
     */
    void updateAlbum(Album album);
    /**
     * 查询一个
     */
    Album findOneAlbum(Album album);

}
