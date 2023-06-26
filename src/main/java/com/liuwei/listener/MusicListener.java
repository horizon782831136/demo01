package com.liuwei.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.liuwei.domain.Music;
import com.liuwei.domain.Orders;
import com.liuwei.service.MusicService;
import com.liuwei.service.OrdersService;
import com.liuwei.utils.BeanCopyUtils;
import com.liuwei.vo.AddMusicVo;
import com.liuwei.vo.AddOrdersVo;
import org.apache.commons.compress.utils.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Component
public class MusicListener extends AnalysisEventListener<AddMusicVo> {
    @Resource
    private MusicService musicService;

    public static MusicService tempMusicService;
    List<AddMusicVo> list = Lists.newArrayList();
    @PostConstruct
    public void setTempRoleMenuService() {
        tempMusicService = this.musicService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(MusicListener.class);
    @Override
    public void invoke(AddMusicVo data, AnalysisContext context) {
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        List<Music> musics = BeanCopyUtils.copyBeanList(list, Music.class);
        musics.stream().parallel().forEach(music -> {
            tempMusicService.addMusic(music);
        });
        LOGGER.info(JSON.toJSONString(musics));
    }
}
