package org.study.crowd.mvc.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.study.crowd.entity.Role;
import org.study.crowd.service.api.AdminService;
import org.study.crowd.service.api.RoleService;

import java.util.List;

@Controller
public class AssignHandler {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("assign/to/assign/role/page.html")
    public String toAssignRolePage(@RequestParam("adminId") Integer adminId, ModelMap modelMap) {

        //已分配角色
        List<Role> assignedRoleList = roleService.getAssignedRole(adminId);

        //未分配角色
        List<Role> unAssignedRoleList = roleService.getUnAssignedRole(adminId);

        modelMap.addAttribute("assignedRoleList", assignedRoleList);
        modelMap.addAttribute("unAssignedRoleList", unAssignedRoleList);

        return "assign-role";
    }

    @RequestMapping("/assign/do/role/assign.html")
    public String saveAdminRoleRelationship(@RequestParam("adminId") Integer adminId, @RequestParam("pageNum") Integer pageNum, @RequestParam("keyword") String keyword,
                                            // 我们允许用户在页面上取消所有已分配角色再提交表单，所以可以不提供 roleIdList 请求参数
                                            // 设置 required=false 表示这个请求参数不是必须的
                                            @RequestParam(value = "roleIdList", required = false) List<Integer> roleIdList) {

        adminService.saveAdminRoleRelationship(adminId, roleIdList);

        return "redirect:/admin/get/page.html?pageNum=" + pageNum + "&keyword=" + keyword;
    }
}
