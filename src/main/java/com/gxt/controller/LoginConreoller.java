package com.gxt.controller;

import com.alibaba.druid.sql.visitor.functions.If;
import com.gxt.pojo.*;
import com.gxt.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/login")
public class LoginConreoller {

    @Autowired
    private MsgService MsgService;

    @Autowired
    private PracticeService practiceService;

    @Autowired
    private LearningService learningService;

    @Autowired
    private QueService queService;

    @Autowired
    private TargetService targetService;

//    @Autowired
//    private LevelexbookService levelexbookService;

    @Autowired
    private ForgetService forgetService;

    @Autowired
    private WordService wordService;



    @RequestMapping("/register")
    public String register(Model model,String email,String userCode,String userPassword){
        Msg msg = new Msg();
        msg.setMail(email);
        msg.setUserCode(userCode);
        msg.setUserPassword(userPassword);
        msg.setPicture(null);
        msg.setUserName(null);
        msg.setCreationDate(new Date());
        //为用户注册练习模块
        Practice practice = new Practice();
        practice.setUserCode(userCode);
        practice.setCompletion(null);
        practice.setLearningBookId(null);
        practice.setErrorNo(null);
        practice.setCorrect(null);
        practice.setAllTotal(null);
        //为用户注册学习模块
        Learning learning = new Learning();
        learning.setUserCode(userCode);
        learning.setLearningBookId(null);
        learning.setCompletion(null);
        learning.setLearningNo(0);
        learning.setErrorNo(0);

        //为用户注册密保模块
        Que que = new Que();
        que.setUserCode(userCode);
        que.setQue(null);
        que.setAsk(null);

        //为用户注册目标模块
        Target target = new Target();
        target.setUserCode(userCode);
        target.setTarget(null);
        target.setPicture(null);

        if (MsgService.register(msg)!=0){
            practiceService.addUserPractice(practice);
            learningService.addUserLearn(learning);
            queService.addUserQue(que);
            targetService.addUserTarget(target);

            model.addAttribute("msg","注册成功");
            return "redirect:/toLogin";


        }
        else

            model.addAttribute("msg","注册失败");
            return "redirect:/toRegister";

    }


    @RequestMapping("/loginTo")
    public String loginTo(HttpSession session, String userCode, String userPassword, Model model){
        Msg msg = MsgService.getMsgByUserCode(userCode);
        System.out.println(msg);
        System.out.println(userCode);
        Date date = new Date();

        if(msg!=null){
            if(Objects.equals(msg.getUserPassword(), userPassword)){

                //获得用户准确率
                Integer correctT = practiceService.getCorrect(userCode);

                if (correctT!=null){
                    Integer all = practiceService.getAllTotal(userCode);
                    NumberFormat numberFormat = NumberFormat.getInstance();
                    numberFormat.setMaximumFractionDigits(2);
                    String correct = numberFormat.format((float) correctT / (float) all * 100);
                    session.setAttribute("correct",correct+"%");
                }else {
                    session.setAttribute("correct","您还未开始练习");
                }

                //获得用户登录天数
                int stuDay = (int) ((date.getTime()-(msg.getCreationDate().getTime()) )/ (1000*3600*24))+1;
                session.setAttribute("stuDay",stuDay);
                System.out.println(stuDay);
                //获得用户剩余单词数
                String bookId = learningService.getLearningBookId(userCode);
                if (bookId!=null){

                    int count = wordService.getWordCount(bookId);
                    Integer completion =learningService.getCompletion(userCode);
                    System.out.println(completion);
                    int over;
                    if (completion!=null){
                        over = count - completion;
                    }else {
                        over = count;
                        completion = 0;
                    }
                    session.setAttribute("over",over+"个词");

                    session.setAttribute("completion",completion+"个词");
                }else {
                    session.setAttribute("over","您未选择单词本");

                    session.setAttribute("completion","前往”学习“选择");
                }

                //加载用户头像
                if (msg.getPicture()!=null){
                    session.setAttribute("userPicture",msg.getPicture());
                }else {
                    session.setAttribute("userPicture","nopic.png");
                }

                //加载备忘录模块
                List<Forget> forgets = forgetService.getForgetList(userCode);
                if (forgets!=null){
                    session.setAttribute("forgets",forgets);
                }else {
                    session.setAttribute("forgets","还未添加备忘事件");
                }

                //加载励志墙模块
                Target target = targetService.getUserTarget(userCode);
                if (target.getTarget()!=null){
                    session.setAttribute("target",target.getTarget());
                }else {
                    session.setAttribute("target","还未设置励志语");
                }

                //加载图片
                if (target.getPicture()!=null){
                    session.setAttribute("targetPic",target.getPicture());
                }else {
                    session.setAttribute("targetPic","xx");
                }

                //加载用户名
                String userName = msg.getUserName();
                session.setAttribute("userName", Objects.requireNonNullElse(userName, "还未设置用户名"));

                //加载邮箱
                if (msg.getMail()!=null){
                    session.setAttribute("mail",msg.getMail());
                }else {
                    session.setAttribute("mail","您还未设置邮箱");
                }

                String ask = queService.getUserAsk(userCode);
                if(ask!=null){
                    session.setAttribute("ask",ask);
                }else {
                    session.setAttribute("ask","您还未设置密保密码");
                }

                session.setAttribute("UserMsg",msg);
                return "index";
            }else {
                session.setAttribute("loginMsg","密码错误");
                return "redirect:/toLogin";
            }
        }else {
            session.setAttribute("loginMsg","该用户不存在");
            return "redirect:/toLogin";
        }

    }

    @RequestMapping("/LoginOut")
    public String LoginOut(HttpSession session){
        session.removeAttribute("UserMsg");
        session.removeAttribute("over");
        session.removeAttribute("completion");
        session.removeAttribute("userPicture");
        session.removeAttribute("forgets");
        session.removeAttribute("target");
        session.removeAttribute("targetPic");
        session.removeAttribute("loginMsg");
        session.removeAttribute("isRight");
        session.removeAttribute("Ans");
        session.removeAttribute("userName");
        session.removeAttribute("mail");
        return "redirect:/toLogin";
    }
}
