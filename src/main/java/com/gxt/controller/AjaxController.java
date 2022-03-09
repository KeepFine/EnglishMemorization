package com.gxt.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gxt.pojo.*;
import com.gxt.service.*;
import com.gxt.util.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
@ResponseBody
@RequestMapping("/ajax")
public class AjaxController {

    @Autowired
    private MsgService loginService;

    @Autowired
    private ForgetService forgetService;

    @Autowired
    private TargetService targetService;

    @Autowired
    private LearningService learningService;

    @Autowired
    private WordService wordService;

    @Autowired
    private ErrorbookService errorbookService;

    @Autowired
    private PracticeService practiceService;

    @Autowired
    private Tool tool;

    @RequestMapping("/chackName")
    public String chackName(String userCode) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        if (loginService.getUserCodeByName(userCode)!=null){

            return mapper.writeValueAsString("用户名重复");
        }
        return mapper.writeValueAsString("ok");
    }

    @RequestMapping("/chackPwd")
    public String chackPwd(String userPassword) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String str = null;
        if (userPassword.length()<=7){
            str = mapper.writeValueAsString("密码太简单了哦");
            return str;
        }
        str = mapper.writeValueAsString("ok");
        return str;
    }
//    @RequestMapping("/chackAll")
//    public String chackAll(String userCode) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        String str = null;
//        if(loginService.getMsgByUserCode(userCode)!=null){
//            str = mapper.writeValueAsString("注册成功");
//            return str;
//        }
//        str = mapper.writeValueAsString("注册失败，请检查是否重名");
//        return str;
//    }
    @RequestMapping("/addForget")
    public String addForget(HttpSession session,String content){
        Msg userMsg = (Msg)session.getAttribute("UserMsg");

        Forget forget = new Forget();
        String userCode = userMsg.getUserCode();
        forget.setUserCode(userCode);
        forget.setContent(content);
        Date date = new Date();
        forget.setCreatTime(date);
        String str = null;
        if (forgetService.addUserForget(forget)!=0){
            List<Forget> forgets = forgetService.getForgetList(userCode);
            session.setAttribute("forgets",forgets);
            str = "1";
            return str;
        }else {
            str = "0";
            return str;
        }
    }

    @RequestMapping("/updataForget")
    public String updataForget(HttpSession session,String content,int id){
        Msg userMsg = (Msg)session.getAttribute("UserMsg");
        Forget forget = forgetService.getForgetById(id);
        String userCode = forget.getUserCode();
        forget.setContent(content);
        String str = null;
        if (forgetService.addUserForget(forget)!=0){
            List<Forget> forgets = forgetService.getForgetList(userCode);
            session.setAttribute("forgets",forgets);
            str = "1";
            return str;
        }else {
            str = "0";
            return str;
        }
    }

    @RequestMapping("/addTargetContent")
    public String addTargetContent(HttpSession session,String content){
        Msg userMsg = (Msg)session.getAttribute("UserMsg");
        String userCode = userMsg.getUserCode();

        String str = null;
        if (targetService.updataTarget(userCode,content)!=0){
            Target target = targetService.getUserTarget(userCode);
            session.setAttribute("target",target);
            str = "1";
            return str;
        }else {
            str = "0";
            return str;
        }
    }

    @RequestMapping("/updataBook")
    public void updataBook(HttpSession session,String bookId){
        Msg userMsg = (Msg) session.getAttribute("UserMsg");
        Learning learning = new Learning();
        learning.setLearningNo(0);
        learning.setErrorNo(0);
        learning.setCompletion(0);
        learning.setLearningBookId(bookId);
        learning.setUserCode(userMsg.getUserCode());

        learningService.updataUserLearn(learning);

        session.setAttribute("completion",learning.getCompletion()+"个词");
        int count = wordService.getWordCount(bookId);
        session.setAttribute("over",count-learning.getCompletion()+"个词");
    }

    @RequestMapping("/chackMeaning")
    private String chackMeaning(HttpSession session) throws JsonProcessingException {
        Msg userMsg = (Msg) session.getAttribute("UserMsg");
        String userCode = userMsg.getUserCode();
        String bookId = learningService.getLearningBookId(userCode);
        int learnNo = learningService.getLearningNo(userCode);
        String meaning = wordService.getWord(bookId,learnNo).getMeaning();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(meaning);
    }

    @RequestMapping("/addError")
    public String addError(HttpSession session) throws JsonProcessingException {
        //Tool tool = new Tool(learningService,wordService,errorbookService);
        Word word = tool.getWord(session);
        String worsNextName = tool.addToError(session,word);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(worsNextName);
    }

    @RequestMapping("/nextOne")
    public String nextOne(HttpSession session) throws JsonProcessingException {
        Word word = tool.getWord(session);
        String worsNextName = tool.nextOne(session);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(worsNextName);
    }

    @RequestMapping("/previousOne")
    public String previousOne(HttpSession session) throws JsonProcessingException {
        Word word = tool.getWord(session);
        String worsNextName = tool.previousOne(session);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(worsNextName);
    }

    //更换练习模块的词库
    @RequestMapping("/updataPraBook")
    public void updataPraBook(HttpSession session,String bookId){
        tool.updataPraBook(session,bookId);
    }

    //练习模块A选项
    @RequestMapping("/Ans")
    public void Ans(HttpSession session, int userAns, Model model){
        //判断是否正确
        Object obj =  session.getAttribute("Ans");
        int Ans =Integer.parseInt ((String) obj);
        Msg userMsg = (Msg) session.getAttribute("UserMsg");
        Practice practice = practiceService.getUserPractice(userMsg.getUserCode());
        Word word = tool.getPraWord(session);
        if (Ans==userAns){
            practiceService.updataCorrect(userMsg.getUserCode(), practice.getCorrect()+1);
            session.setAttribute("isRight","正确");
        }else {
            session.setAttribute("isRight","错误");
        }
        if (practiceService.getAllTotal(userMsg.getUserCode())!=null){
            practiceService.updataAllTotal(userMsg.getUserCode(), practice.getAllTotal()+1);
        }else {
            practiceService.updataAllTotal(userMsg.getUserCode(), 1);
        }

        ArrayList<Object> list = tool.getPraWordTool(session);
        String bookId = (String) list.get(0);
        int wordId = (int) list.get(1);
        if (Objects.equals(bookId, "errorbook")){
            practiceService.updataErrorNo(userMsg.getUserCode(),wordId+1);
        }else if (Objects.equals(bookId, "learning")){
            practiceService.updataLearningNo(userMsg.getUserCode(),wordId+1);
        }else {
            practiceService.updataCompletion(userMsg.getUserCode(), wordId+1);
        }
//        //加载答案
//        model.addAttribute("preWord",word.getMeaning());
//        model.addAttribute("preWordMeaning",word.getMeaning());
//
//        //加载下一题
//        ArrayList<String> wordList = tool.getAwsWordOnLearn(word,session);
//        model.addAttribute("A",wordList.get(0));
//        model.addAttribute("B",wordList.get(1));
//        model.addAttribute("C",wordList.get(2));
//        model.addAttribute("D",wordList.get(3));
//        //把答案放入model
//        if (!wordList.get(4).isEmpty()){
//            model.addAttribute("Ans",wordList.get(4));
//        }
    }

    @RequestMapping("/Epwd")
    public String Epwd(HttpSession session,String pass) throws JsonProcessingException {
        Msg userMsg = (Msg) session.getAttribute("UserMsg");
        ObjectMapper mapper = new ObjectMapper();
        if (Objects.equals(pass, userMsg.getUserPassword())){
            return mapper.writeValueAsString("密码正确");
        }else {
            return mapper.writeValueAsString("密码错误");
        }
    }

}
