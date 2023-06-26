package com.liuwei.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.liuwei.domain.Payinfo;
import com.liuwei.domain.Product;
import com.liuwei.service.PayinfoService;
import com.liuwei.service.ProductService;
import com.liuwei.utils.BeanCopyUtils;
import com.liuwei.vo.AddPayinfoVo;
import com.liuwei.vo.AddProductVo;
import org.apache.commons.compress.utils.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Component
public class PayinfoListener extends AnalysisEventListener<AddPayinfoVo> {
    @Resource
    private PayinfoService payinfoService;

    public static PayinfoService tempPayinfoService;
    List<AddPayinfoVo> list = Lists.newArrayList();
    @PostConstruct
    public void setTempRoleMenuService() {
        tempPayinfoService = this.payinfoService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(PayinfoListener.class);
    @Override
    public void invoke(AddPayinfoVo data, AnalysisContext context) {
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        List<Payinfo> payinfos = BeanCopyUtils.copyBeanList(list, Payinfo.class);
        payinfos.stream().parallel().forEach(payinfo -> {
            tempPayinfoService.addPayInfo(payinfo);
        });
        LOGGER.info(JSON.toJSONString(payinfos));
    }
}
