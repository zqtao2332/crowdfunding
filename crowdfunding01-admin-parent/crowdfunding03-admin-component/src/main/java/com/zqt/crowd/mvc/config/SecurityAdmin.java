package com.zqt.crowd.mvc.config;

import com.zqt.crowd.entity.admin.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * @author: zqtao
 * @description: 封装 SecurityAdmin，spring security 权限验证需要 User，User类中只有用户名和密码
 * 封装此类对 User类进行扩展，使得可以使用自定义的 Admin 对象来进行登录
 * @Date: 2020/6/22
 */
public class SecurityAdmin extends User {

    private static final long serialVersionUID = 1L;


    // 引入自定义的管理员对象
    private Admin admin;


/*public User(String username, String password, Collection<? extends GrantedAuthority> authorities) {

    }*/

    /**
     *  仿写 User 的构造方法
     * @param admin admin
     * @param authorities 存储角色、权限信息的集合
     */
    public SecurityAdmin(Admin admin, List<GrantedAuthority> authorities) {
        // 调用User 的构造方法
        super(admin.getLoginAcct(), admin.getUserPswd(), authorities);
        this.admin = admin;
    }

    /**
     * 对外提供获取 Admin 对象的 getter 方法
     * @return Admin
     */
    public Admin getAdmin() {
        return this.admin;
    }
}