package com.liuwei.service;

import com.liuwei.domain.User;
import com.liuwei.utils.Result;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2022-11-05 22:11:27
 */
@Service
public interface UserService extends IService<User>{
    Result login(Map<String, String> user, HttpSession session);
    Result register(Map<String, String> user, HttpSession session);

    Result addUser(User user);

    Result updateUser(User user);

    Result deleteById(Long id);

    Result getByPage(Integer current, Integer size);

    Result getById(Long id);

    Result logout(HttpSession session);

    Result getLikeByPage(String tel, Integer current, Integer size);

    Result getAll();

    void doDownLoad(HttpServletResponse response) throws IOException;

    void doUpload(MultipartFile file) throws IOException;

    Result deleteByBatch(List<Long> ids);

    Result updateBasicInfo(User user);
}
