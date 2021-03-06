package com.zqt.crowd.service.impl.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zqt.crowd.constant.CommonConstant;
import com.zqt.crowd.entity.admin.Admin;
import com.zqt.crowd.entity.admin.AdminExample;
import com.zqt.crowd.exception.LoginAcctAlreadyInUseException;
import com.zqt.crowd.exception.LoginFailedException;
import com.zqt.crowd.mapper.admin.AdminMapper;
import com.zqt.crowd.service.api.admin.AdminService;
import com.zqt.crowd.util.DateUtil;
import com.zqt.crowd.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author: zqtao
 * @description: 管理员业务层实现类
 * @Date: 2020/5/17 22:35
 * @version: 1.0
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    // 装配在 spring-web-mvc.xml 中声明的spring security 盐值加密所需 bean
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void saveAdmin(Admin admin) {
        // 获取系统时间
        String createTime = DateUtil.getDate();
        admin.setCreateTime(createTime);

        // 登录密码加密
        String userPswd = admin.getUserPswd();
        // 使用spring security 对密码进行盐值加密
        admin.setUserPswd(passwordEncoder.encode(userPswd));

        // 执行保存，如果账户被占用，跑出异常
        try {
            adminMapper.insert(admin);
        } catch (Exception e) {
            e.printStackTrace();
            // 检测当前捕获的异常对象，如果是 DuplicateKeyException 类型说明是账号重复导致的
            if (e instanceof DuplicateKeyException) {
                // 抛出自定义的 LoginAcctAlreadyInUseException 异常
                throw new LoginAcctAlreadyInUseException(CommonConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
            // 为了不掩盖问题，如果当前捕获到的不是 DuplicateKeyException 类型的异常，
            // 则把当前捕获到的异常对象继续向上抛出
            throw e;
        }
    }

    public List<Admin> getAll() {
        // 空Example就是查全部
        return adminMapper.selectByExample(new AdminExample());
    }

    public Admin getAdminByLoginAcct(String loginAcct, String userPswd) {
        // 1、根据登录账号查询Admin对象
        // 创建AdminExample对象
        AdminExample adminExample = new AdminExample();
        // 创建Criteria 对象
        AdminExample.Criteria criteria = adminExample.createCriteria();
        // 在Criteria对象中封装查询条件
        criteria.andLoginAcctEqualTo(loginAcct);
        // 查询
        List<Admin> adminList = adminMapper.selectByExample(adminExample);

        // 2、判断Admin对象是否为null
        if (adminList == null || adminList.size() == 0)
            // 3、null 抛出异常
            throw new LoginFailedException(CommonConstant.MESSAGE_LOGIN_FAILED);
        if (adminList.size() > 1)
            throw new RuntimeException(CommonConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);

        // 4、Admin不为null，则将数据库密码从admin对象中取出
        Admin admin = adminList.get(0);
        if (admin == null)
            throw new LoginFailedException(CommonConstant.MESSAGE_LOGIN_FAILED);
        String userPswdDB = admin.getUserPswd();

        // 5、将表单提交的明文密码进行加密处理
        String userPswdForm = MD5Util.md5(userPswd);

        // 6、比较密文
        if (!Objects.equals(userPswdDB, userPswdForm))
            // 7、不一致，抛出异常
            throw new LoginFailedException(CommonConstant.MESSAGE_LOGIN_FAILED);
        // 8、一致，返回
        return admin;
    }

    public Admin getAdminByLoginAcct(String loginAcct) {

        // 根据登录账号查询Admin对象
        // 创建AdminExample对象
        AdminExample adminExample = new AdminExample();
        // 创建Criteria 对象
        AdminExample.Criteria criteria = adminExample.createCriteria();
        // 在Criteria对象中封装查询条件
        criteria.andLoginAcctEqualTo(loginAcct);
        // 查询
        List<Admin> adminList = adminMapper.selectByExample(adminExample);

        return adminList.get(0);
    }


    @Override
    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {

        // 1、调用PageHelper的startPage() 方法开启分页功能
        // 这里充分体现了PageHelper的“非侵入式”设计：原本要做的查询不必有任何修改
        PageHelper.startPage(pageNum, pageSize);

        // 2、执行查询
        List<Admin> adminList = adminMapper.selectAdminByKeyword(keyword);

        // 3、将List封装到PageInfo对象中
        return new PageInfo<>(adminList);

    }

    @Override
    public void remove(Integer adminId) {
        adminMapper.deleteByPrimaryKey(adminId);
    }

    @Override
    public Admin getAdminById(Integer adminId) {
        return adminMapper.selectByPrimaryKey(adminId);
    }

    @Override
    public void update(Admin admin) {

        // 密码明文加密，注意可能为修改，那么传值为空
        if (!"".equals(admin.getUserPswd())) {
            String userPswd = admin.getUserPswd();
//            admin.setUserPswd(MD5Util.md5(userPswd));
            admin.setUserPswd(passwordEncoder.encode(userPswd));
        } else {
            admin.setUserPswd(null);
        }

        try {
            // Selective 表示选择性更新，对于 Admin对象中值为 null的不进行更新，只更新存在的字段
            adminMapper.updateByPrimaryKeySelective(admin);
        } catch (Exception e) {
            // 检测当前捕获的异常对象，如果是 DuplicateKeyException 类型说明是账号重复导致的
            if (e instanceof DuplicateKeyException) {
                // 抛出自定义的 LoginAcctAlreadyInUseException 异常
                throw new LoginAcctAlreadyInUseException(CommonConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
            // 为了不掩盖问题，如果当前捕获到的不是 DuplicateKeyException 类型的异常，
            // 则把当前捕获到的异常对象继续向上抛出
            throw e;
        }
    }

    @Override
    public void saveAdminRoleRelationship(Integer adminId, List<Integer> roleIdList) {
        // 旧数据如下：
        // adminId	roleId
        // 1		1（要删除）
        // 1		2（要删除）
        // 1		3
        // 1		4
        // 1		5
        // 新数据如下：
        // adminId	roleId
        // 1		3（本来就有）
        // 1		4（本来就有）
        // 1		5（本来就有）
        // 1		6（新）
        // 1		7（新）
        // 为了简化操作：先根据adminId删除旧的数据，再根据roleIdList保存全部新的数据

        // 1.根据adminId删除旧的关联关系数据
        adminMapper.deleteOLdRelationship(adminId);

        // 2.根据roleIdList和adminId保存新的关联关系
        if (roleIdList != null && roleIdList.size() > 0) {
            adminMapper.insertNewRelationship(adminId, roleIdList);
        }
    }

}
