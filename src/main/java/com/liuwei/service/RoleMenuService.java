package com.liuwei.service;

import com.liuwei.domain.RoleMenu;
import com.liuwei.utils.Result;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 角色-权限关联表(RoleMenu)表服务接口
 *
 * @author makejava
 * @since 2022-11-06 16:04:16
 */
@Service
public interface RoleMenuService extends IService<RoleMenu>{


    Result addRoleMenu(RoleMenu roleMunu);

    Result deleteRoleMunu(Long id);

    Result getRoleMunuById(Long id);

    Result getRoleMunuByPage(Integer current, Integer size);

    void doDownLoad(HttpServletResponse response) throws IOException;

    void doUpload(MultipartFile file) throws IOException;
}
