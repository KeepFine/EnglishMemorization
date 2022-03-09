package com.gxt.controller;

import com.gxt.dao.MsgMapper;
import com.gxt.pojo.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class textController {
    @Autowired
    private MsgMapper msgMapper;

    @RequestMapping("/hello")
    public String text(){
        return "index";
    }
    @ResponseBody
    @RequestMapping("/gg")
    public String text2(){
        return "jj";
    }
    @RequestMapping("/hello2")
    public String text2(Model model){
        model.addAttribute("msg","ssss");
        return "index";
    }
    @RequestMapping("/hh")
    public String text3(){
        return "text";
    }
    @RequestMapping("/aa")
    public String text4(){
        return "advanced-alertify";
    }

    @ResponseBody
    @RequestMapping("/bb")
    public String text5(){
        Msg msg = new Msg();
        msg.setId(1);
        int n= msgMapper.addMsg(msg);
        return "n";
    }
}
