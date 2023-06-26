package com.liuwei.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.liuwei.domain.Coupon;
import com.liuwei.domain.Music;
import com.liuwei.service.CouponService;
import com.liuwei.service.MusicService;
import com.liuwei.utils.BeanCopyUtils;
import com.liuwei.vo.AddCouponVo;
import com.liuwei.vo.AddMusicVo;
import org.apache.commons.compress.utils.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Component
public class CouponListener extends AnalysisEventListener<AddCouponVo> {
    @Resource
    private CouponService couponService;

    public static CouponService tempCouponService;
    List<AddCouponVo> list = Lists.newArrayList();
    @PostConstruct
    public void setTempRoleMenuService() {
        tempCouponService = this.couponService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(CouponListener.class);
    @Override
    public void invoke(AddCouponVo data, AnalysisContext context) {
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        List<Coupon> coupons = BeanCopyUtils.copyBeanList(list, Coupon.class);
        coupons.stream().parallel().forEach(coupon -> {
            tempCouponService.addCoupon(coupon);
        });
        LOGGER.info(JSON.toJSONString(coupons));
    }
}
