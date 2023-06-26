package com.liuwei.service;

import com.liuwei.domain.Orderitem;
import com.liuwei.domain.Orders;
import com.liuwei.utils.Result;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 订单表(TblOrders)表服务接口
 *
 * @author makejava
 * @since 2022-11-06 15:58:03
 */
@Service
public interface OrdersService extends IService<Orders>{


    Result addOrders(Orders orders);

    Result updateOrders(Orders orders);

    Result getOrdersById(Long id);

    Result getOrdersByPage(Integer current, Integer size);

    Result deleteById(Long id);

    Result getByUserId(Long id);

    void doDownLoad(HttpServletResponse response) throws IOException;

    void doUpload(MultipartFile file) throws IOException;

    Result getCustomerOrders(Long id);

    Result buyProductDirectly(Orderitem orderitem, Long userId, Long addressId, Long platform);

    Result closeTradeByOrderId(Long id);
}
