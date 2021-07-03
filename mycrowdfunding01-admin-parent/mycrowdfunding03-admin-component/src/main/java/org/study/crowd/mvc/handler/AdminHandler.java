package org.study.crowd.mvc.handler;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.study.crowd.constant.CrowdConstant;
import org.study.crowd.entity.Admin;
import org.study.crowd.service.api.AdminService;

import javax.servlet.http.HttpSession;

@Controller
public class AdminHandler {

    @Autowired
    private AdminService adminService;

    @RequestMapping("admin/update.html")
    public String updateAdmin(Admin admin,@RequestParam("pageNum")Integer pageNum,@RequestParam("keyword")String keyword) {

        adminService.update(admin);

        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;

    }

    @RequestMapping("admin/to/edit/page.html")
    public String toEditPage(@RequestParam("adminId")Integer adminId, ModelMap modelMap) {

        Admin admin =  adminService.getAdminById(adminId);

        modelMap.addAttribute("admin",admin);
        return "admin-edit";
    }

    @RequestMapping("admin/save.html")
    public String save(Admin admin) {

        adminService.saveAdmin(admin);

        return "redirect:/admin/get/page.html?pageNum="+Integer.MAX_VALUE;
    }

    @RequestMapping("admin/remove/{adminId}/{pageNum}/{keyword}.html")
    public String remove(@PathVariable("adminId")Integer adminId,@PathVariable("pageNum")Integer pageNum,@PathVariable("keyword")String keyword){

        adminService.remove(adminId);
        //清空缓存
        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
    }

    @RequestMapping("admin/get/page.html")
    public String getPageInfo(
            // 使用@RequestParam注解的defaultValue属性，指定默认值，在请求中没有携带对应参数时使用默认值
            // keyword默认值使用空字符串，和SQL语句配合实现两种情况适配
            @RequestParam(value="keyword", defaultValue="") String keyword,
            // pageNum默认值使用1
            @RequestParam(value="pageNum", defaultValue="1") Integer pageNum,
            // pageSize默认值使用5
            @RequestParam(value="pageSize", defaultValue="5") Integer pageSize,
            ModelMap modelMap
    ) {

        // 调用Service方法获取PageInfo对象
        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNum, pageSize);

        // 将PageInfo对象存入模型
        modelMap.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO, pageInfo);

        return "admin-page";
    }


    @RequestMapping("admin/do/logout.html")
    public String logout(HttpSession session){
        //清空缓存
        session.invalidate();
        return "redirect:/admin/to/login.html";
    }

    @RequestMapping("admin/do/login.html")
    public String toLogin(@RequestParam("loginAcct")String loginAcct, @RequestParam("userPswd")String userPswd, HttpSession session){
        // 调用Service方法执行登录检查
        // 这个方法如果能够返回admin对象说明登录成功，如果账号、密码不正确则会抛出异常
        Admin admin = adminService.getAdminByLoginAcct(loginAcct, userPswd);

        // 将登录成功返回的admin对象存入Session域
        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN, admin);

        return "redirect:/admin/to/main/page.html";
    }
}
