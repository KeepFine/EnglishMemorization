package com.gxt.controller;

import com.gxt.pojo.Errorbook;
import com.gxt.pojo.Learning;
import com.gxt.pojo.Msg;
import com.gxt.pojo.Word;
import com.gxt.service.ErrorbookService;
import com.gxt.service.LearningService;
import com.gxt.service.PracticeService;
import com.gxt.service.WordService;
import com.gxt.util.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class JumpConreoller {

    @Autowired
    private LearningService learningService;

    @Autowired
    private WordService wordService;

    @Autowired
    private PracticeService practiceService;

    @Autowired
    private ErrorbookService errorbookService;

    @Autowired
    private Tool tool;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "pages-login";
    }
    @RequestMapping("/toAbout")
    public String toAbout(){
        return "pages-maintenance";
    }

    @RequestMapping("/toSelectBook")
    public String toSelectBook(Model model){
        return "pages-pricing";
    }

    @RequestMapping("/toHome")
    public String toHome(){
        return "index";
    }

    @RequestMapping("/toLearning")
    public String toLearning(HttpSession session,Model model){
        Msg userMag = (Msg) session.getAttribute("UserMsg");
        String learningBookId = learningService.getLearningBookId(userMag.getUserCode());
        if (learningBookId!=null){
            int learningNo = learningService.getLearningNo(userMag.getUserCode());
            while (wordService.getWord(learningBookId,learningNo)==null){
                learningNo++;
            }
            learningService.updatalearningNo(userMag.getUserCode(),learningNo);
            Word word = wordService.getWord(learningBookId,learningNo);
            model.addAttribute("word",word.getName());
        }else {
            model.addAttribute("wordInfo","?????????????????????");
            model.addAttribute("word","");
        }

        return "pages-learning";
    }
    @RequestMapping("/toPractice")
    public String toPractice(HttpSession session,Model model){
        //????????????
        tool.updataPraWord(session);

        //?????????????????????????????????
        Word word = tool.getPraWord(session);
        if (word!=null){
            model.addAttribute("word",word.getName());
            ArrayList<String> wordList = tool.getAwsWordOnLearn(word,session);
            model.addAttribute("A",wordList.get(0));
            model.addAttribute("B",wordList.get(1));
            model.addAttribute("C",wordList.get(2));
            model.addAttribute("D",wordList.get(3));
            //???????????????model
            if (!wordList.get(4).isEmpty()){
                session.setAttribute("Ans",wordList.get(4));
            }
        }else {
            model.addAttribute("A","?????????????????????");
            model.addAttribute("B","?????????????????????");
            model.addAttribute("C","?????????????????????");
            model.addAttribute("D","?????????????????????");
            model.addAttribute("wordInfo","??????????????????????????????????????????????????????????????????????????????????????????");
            model.addAttribute("word","");
        }



        //?????????????????????
        Word preWord = tool.getPraPreWord(session);
        if (preWord!=null){
            model.addAttribute("preWord",preWord.getName());
            model.addAttribute("preWordMeaning",preWord.getMeaning());
        }else {
            model.addAttribute("preWord","??????????????????");
            model.addAttribute("preWordMeaning","??????");
        }

        return "pages-practice";
    }

    @RequestMapping("/toLearningTwo")
    public String toLearningTwo(){
        return "pages-learningTwo";
    }

    @RequestMapping("/toErroeBook")
    public String toErroeBook(HttpSession session,Model model){
        Msg userMsg = (Msg) session.getAttribute("UserMsg");
        List<Errorbook> errorbooks =  errorbookService.getErrorBook(userMsg.getUserCode());
        model.addAttribute("errorbooks",errorbooks);
        return "errorBook";
    }

    @RequestMapping("/toMail")
    public String toMail(){
        return "pages-mail";
    }

    @RequestMapping("/toSearch")
    public String toSearch(){
        return "pages-search";
    }

    @RequestMapping("/toList")
    public String toList(){
        return "pages-personList";
    }

    @RequestMapping("/toRecoverpw")
    public String toRecoverpw(){
        return "pages-recoverpw";
    }

    @RequestMapping("/toRegister")
    public String toRegister(){
        return "pages-register";
    }

    @RequestMapping("/toPersonal")
    public String toPersonal(){
        return "pages-personal";
    }
}
