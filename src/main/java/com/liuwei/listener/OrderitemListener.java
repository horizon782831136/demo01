package com.liuwei.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.liuwei.domain.Orderitem;
import com.liuwei.domain.Orders;
import com.liuwei.service.OrderitemService;
import com.liuwei.service.OrdersService;
import com.liuwei.utils.BeanCopyUtils;
import com.liuwei.vo.AddOrderitemVo;
import com.liuwei.vo.AddOrdersVo;
import org.apache.commons.compress.utils.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Component
public class OrderitemListener extends AnalysisEventListener<AddOrderitemVo> {
    @Resource
    private OrderitemService orderitemService;

    public static OrderitemService tempOrderitemService;
    List<AddOrderitemVo> list = Lists.newArrayList();
    @PostConstruct
    public void setTempRoleMenuService() {
        tempOrderitemService = this.orderitemService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderitemListener.class);
    @Override
    public void invoke(AddOrderitemVo data, AnalysisContext context) {
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        List<Orderitem> orderitems = BeanCopyUtils.copyBeanList(list, Orderitem.class);
        orderitems.stream().parallel().forEach(orderitem -> {
            tempOrderitemService.addOrderitem(orderitem);
        });
        LOGGER.info(JSON.toJSONString(orderitems));
    }
}
