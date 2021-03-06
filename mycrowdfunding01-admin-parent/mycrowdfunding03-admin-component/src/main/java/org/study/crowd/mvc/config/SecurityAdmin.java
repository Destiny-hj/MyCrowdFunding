package org.study.crowd.mvc.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.study.crowd.entity.Admin;

import java.util.List;

public class SecurityAdmin extends User {
    private static final long serialVersionUID = 1L;

    // 原始admin对象，包含admin对象的全部属性
    private Admin originalAdmin;

    public SecurityAdmin(Admin originalAdmin, List<GrantedAuthority> authorities) {
        super(originalAdmin.getLoginAcct(), originalAdmin.getUserPswd(), authorities);
        this.originalAdmin = originalAdmin;
        // 将原始Admin对象中的密码擦除
        this.originalAdmin.setUserPswd(null);
    }

    // 获取原始的Admin对象的get方法
    public Admin getOriginalAdmin(){
        return originalAdmin;
    }

}
