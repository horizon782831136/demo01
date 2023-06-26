package com.liuwei.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuwei.dao.MusicDao;
import com.liuwei.domain.Music;;
import com.liuwei.listener.MusicListener;
import com.liuwei.listener.OrderitemListener;
import com.liuwei.service.MusicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuwei.utils.*;
import com.liuwei.vo.AddMusicVo;
import com.liuwei.vo.AddOrderitemVo;
import com.liuwei.vo.ShowMusicVo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * 音乐表(TblMusic)表服务实现类
 *
 * @author makejava
 * @since 2022-11-06 15:57:36
 */
@Service
public class MusicServiceImpl extends ServiceImpl<MusicDao, Music> implements MusicService {
    @Resource
    private MusicService musicService;

    @Override
    public Result addMusic(Music music) {
        music.setDelFlag(Default.DEFAULT_DELETE);
        music.setStatus(Default.DEFAULT_STATUS);
        boolean flag = musicService.save(music);
        return ResultUtils.add(flag);
    }

    @Override
    public Result updateMusic(Music music) {
        boolean flag = musicService.updateById(music);
        return ResultUtils.update(flag);
    }

    @Override
    public Result getMusicById(Long id) {
        LambdaQueryWrapper<Music> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Music::getMusicId, id);
        return new Result(musicService.getOne(queryWrapper));
    }

    @Override
    public Result getMusicByPage(Integer current, Integer size) {
        IPage<Music> page = new Page<>(current, size);
        musicService.page(page);
        return new Result(page);
    }

    @Override
    public Result deleteById(Long id) {
        boolean flag = musicService.removeById(id);
        return ResultUtils.delete(flag);
    }

    @Override
    public void doDownLoad(HttpServletResponse response) throws IOException {
        ExcelUtils.downLoad(response, AddMusicVo.class, BeanCopyUtils.copyBeanList(
                musicService.list(), AddMusicVo.class
        ));
    }

    @Override
    public void doUpload(MultipartFile file) throws IOException{
        ExcelUtils.upload(file, AddMusicVo.class, new MusicListener());
    }

    @Override
    public Result getByCustomer() {
        LambdaQueryWrapper<Music> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Music::getStatus, Default.DEFAULT_STATUS);
        List<ShowMusicVo> showMusicVos = BeanCopyUtils.copyBeanList(musicService.list(queryWrapper), ShowMusicVo.class);
        return new Result(showMusicVos);
    }
}
