package com.liuwei.service;

import com.liuwei.domain.Menu;
import com.liuwei.domain.Role;
import com.liuwei.utils.Result;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 角色表(TblRole)表服务接口
 *
 * @author makejava
 * @since 2022-11-06 15:58:38
 */
@Service
public interface RoleService extends IService<Role>{


    Result addRole(Role role);

    Result updateRole(Role role);

    Result getRoleById(Long id);

    Result getRoleByPage(Integer current, Integer size);

    Result getAll();

    void doDownLoad(HttpServletResponse response) throws IOException;

    void doUpload(MultipartFile file) throws IOException;
}
