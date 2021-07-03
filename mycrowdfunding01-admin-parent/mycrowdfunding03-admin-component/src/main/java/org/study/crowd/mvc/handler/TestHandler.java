package org.study.crowd.mvc.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.study.crowd.entity.Admin;
import org.study.crowd.service.api.AdminService;
import org.study.crowd.util.CrowdUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TestHandler {

    @Autowired
    private AdminService adminService;

    private Logger logger = LoggerFactory.getLogger(TestHandler.class);

    @RequestMapping("/test/ssm")
    public String testSsm(Model model, HttpServletRequest request){
        logger.info(String.valueOf(CrowdUtil.judgeRequestType(request)));
        List<Admin> list = adminService.getAll();
        model.addAttribute("list",list);
        return "target";
    }

    @RequestMapping("/send/array")
    @ResponseBody
    public String testReceiveArray(@RequestParam("array[]")List<Integer> list,HttpServletRequest request){
        for (Integer integer : list) {
            System.out.println(integer);
        }
        logger.info(String.valueOf(CrowdUtil.judgeRequestType(request)));
        String s = null;
        System.out.println(s.length());

        return "ok";
    }
}
