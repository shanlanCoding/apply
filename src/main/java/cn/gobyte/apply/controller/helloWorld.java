package cn.gobyte.apply.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/*
TODO:测试Controller和ThymensVar用的
* @author shanLan misterchou@qq.com
* @date 2019/3/27 23:24
*/
@Controller
public class helloWorld {
    private static final Logger log = LoggerFactory.getLogger(helloWorld.class);


    /**
    TODO:测试
    * @param model
    * @return java.lang.String
    * @author shanLan misterchou@qq.com
    * @date 2019/3/27 23:22
    */
    @RequestMapping("/hello")
    public String hellWorld(Model model){
        model.addAttribute("hello","helloWorld!");
        return "helloWorld";
//        return "index";
    }
}
