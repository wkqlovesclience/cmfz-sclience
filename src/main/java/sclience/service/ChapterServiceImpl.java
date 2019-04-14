package sclience.service;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sclience.dao.ChapterMapper;
import sclience.entity.Chapter;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Resource
    private ChapterMapper chapterMapper;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Chapter> findAllChaptersByPage(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);//开启分页
        return chapterMapper.findAlbumAndChapterByPage();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer findTotals() {
        Chapter chapter = new Chapter();
        return chapterMapper.selectCount(chapter);
    }

    @Override
    public void deleteChapter(Chapter chapter) {
        chapterMapper.deleteByPrimaryKey(chapter.getId());
    }

    @Override
    public void uploadChapter(Chapter chapter) {
        //生成id
        chapter.setId(UUID.randomUUID().toString());
        //设置上传时间
        chapter.setUploadTime(new Date());
        //设置播放次数
        chapter.setPlayNum(0);
        //设置下载次数
        chapter.setDownloadNum(0);
        chapterMapper.insertSelective(chapter);
    }

    @Override
    public Chapter findOne(Chapter chapter) {
        return chapterMapper.selectByPrimaryKey(chapter.getId());
    }
}
