package com.liuwei.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.liuwei.domain.Cart;
import com.liuwei.domain.Music;
import com.liuwei.service.CartService;
import com.liuwei.service.MusicService;
import com.liuwei.utils.BeanCopyUtils;
import com.liuwei.vo.AddCartVo;
import com.liuwei.vo.AddMusicVo;
import org.apache.commons.compress.utils.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
@Component
public class CartListener extends AnalysisEventListener<AddCartVo> {
    @Resource
    private CartService cartService;

    public static CartService tempCartService;
    List<AddCartVo> list = Lists.newArrayList();
    @PostConstruct
    public void setTempRoleMenuService() {
        tempCartService = this.cartService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(CartListener.class);
    @Override
    public void invoke(AddCartVo data, AnalysisContext context) {
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        List<Cart> carts = BeanCopyUtils.copyBeanList(list, Cart.class);
        carts.stream().parallel().forEach(cart -> {
            tempCartService.addCart(cart);
        });
        LOGGER.info(JSON.toJSONString(carts));
    }
}
