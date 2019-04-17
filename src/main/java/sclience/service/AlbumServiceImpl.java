package sclience.service;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sclience.conf.TestInterface;
import sclience.dao.AlbumMapper;
import sclience.entity.Album;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Resource
    private AlbumMapper albumMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Album> findAllAlbums() {
        return albumMapper.selectAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Album> findAllAlbumsByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize); //开启分页配置
        List<Album> albums = albumMapper.selectAll();
        return albums;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer findTotals() {
        Album album = new Album();
        return albumMapper.selectCount(album);
    }

    @Override
    @TestInterface("删除专辑")
    public void deleteAlbum(Album album) {
        albumMapper.deleteByPrimaryKey(album.getId());
    }

    @Override
    @TestInterface("添加专辑")
    public void addAlbum(Album album) {
        //生成id
        album.setId(UUID.randomUUID().toString());
        //设置上传日期
        album.setPublishDate(new Date());
        //设置专辑集数
        album.setEpisodes(0);
        albumMapper.insertSelective(album);
    }

    @Override
    @TestInterface("更新专辑")
    public void updateAlbum(Album album) {
        albumMapper.updateByPrimaryKeySelective(album);
    }

    @Override
    public Album findOneAlbum(Album album) {
        return albumMapper.selectByPrimaryKey(album.getId());
    }
}
