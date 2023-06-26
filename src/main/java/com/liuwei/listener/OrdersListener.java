package com.liuwei.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.liuwei.domain.Orders;
import com.liuwei.domain.Payinfo;
import com.liuwei.service.OrdersService;
import com.liuwei.service.PayinfoService;
import com.liuwei.utils.BeanCopyUtils;
import com.liuwei.vo.AddOrderitemVo;
import com.liuwei.vo.AddOrdersVo;
import com.liuwei.vo.AddPayinfoVo;
import org.apache.commons.compress.utils.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Component
public class OrdersListener extends AnalysisEventListener<AddOrdersVo> {
    @Resource
    private OrdersService ordersService;

    public static OrdersService tempOrdersService;
    List<AddOrdersVo> list = Lists.newArrayList();
    @PostConstruct
    public void setTempRoleMenuService() {
        tempOrdersService = this.ordersService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(OrdersListener.class);
    @Override
    public void invoke(AddOrdersVo data, AnalysisContext context) {
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        List<Orders> orders = BeanCopyUtils.copyBeanList(list, Orders.class);
        orders.stream().parallel().forEach(order -> {
            tempOrdersService.addOrders(order);
        });
        LOGGER.info(JSON.toJSONString(orders));
    }
}
