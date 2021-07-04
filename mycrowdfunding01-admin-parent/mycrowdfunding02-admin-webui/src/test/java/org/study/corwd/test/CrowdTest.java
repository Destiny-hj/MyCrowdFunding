package org.study.corwd.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.study.crowd.entity.Admin;
import org.study.crowd.entity.Role;
import org.study.crowd.mapper.AdminMapper;
import org.study.crowd.mapper.RoleMapper;
import org.study.crowd.service.api.AdminService;
import org.study.crowd.util.CrowdUtil;

import javax.sql.DataSource;
import java.sql.Connection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})
public class CrowdTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void testRoleSave() {
        for(int i = 0; i < 235; i++) {
            roleMapper.insert(new Role(i+1, "role"+i));
        }
    }

    @Test
    public void testInsertAdmin() throws Exception{
        Admin admin = adminMapper.selectByPrimaryKey(1);
//        System.out.println(adminMapper.insertSelective(new Admin(1,"chj","123","阿斯顿","asd@qq.com",null )));
    }

    @Test
    public void testconnection() throws Exception{
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    public void testLog(){
        Logger logger = LoggerFactory.getLogger(CrowdTest.class);
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
    }

    @Test
    public void testTx(){
        for (int i=2;i<100;i++){
            adminService.saveAdmin(new Admin(i,"chj"+i,"6666","阿斯顿"+i,"asd@qq.com",null ));
        }
    }

    @Test
    public void testMd5(){
        String s = "666666";
        System.out.println("-----"+ CrowdUtil.md5(s));
    }
}
