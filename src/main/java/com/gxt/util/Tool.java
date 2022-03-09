package com.gxt.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gxt.pojo.*;
import com.gxt.service.ErrorbookService;
import com.gxt.service.LearningService;
import com.gxt.service.PracticeService;
import com.gxt.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class Tool {

    @Autowired
    private LearningService learningService;

    @Autowired
    private WordService wordService;

    @Autowired
    private ErrorbookService errorbookService;

    @Autowired
    private PracticeService practiceService;

    public static Tool tool;

    @PostConstruct
    public void init(){
        tool = this;
        tool.errorbookService = this.errorbookService;
        tool.learningService = this.learningService;
        tool.wordService = this.wordService;
    }

    public Tool(LearningService learningService, WordService wordService, ErrorbookService errorbookService) {
        this.learningService = learningService;
        this.wordService = wordService;
        this.errorbookService = errorbookService;
    }

    public Word getWord(HttpSession session){
        Msg userMsg = (Msg) session.getAttribute("UserMsg");
        String bookId = learningService.getLearningBookId(userMsg.getUserCode());
        int wordId = learningService.getLearningNo(userMsg.getUserCode());

        return wordService.getWord(bookId,wordId);
    }

    public String addToError(HttpSession session,Word word) throws JsonProcessingException {

        Msg userMsg = (Msg) session.getAttribute("UserMsg");

        //学习进度+1
        Learning learning = learningService.getUserLearn(userMsg.getUserCode());
        int learningNo = learning.getLearningNo();
        learningNo++;
        while (wordService.getWord(learning.getLearningBookId(),learningNo)==null){
            learningNo++;
        }
        //判断是否重复添加
        if (errorbookService.getWordByNo(userMsg.getUserCode(), word.getWordId())!=null){

            Word wordNext = wordService.getWord(learning.getLearningBookId(),learningNo);
            return wordNext.getName();
        }

        learningService.updatalearningNo(userMsg.getUserCode(),learningNo);
        int completion = learningService.getCompletion(userMsg.getUserCode())+1;
        learningService.updataCompletion(userMsg.getUserCode(), completion);
        //加到错题本
        Errorbook errorbook = new Errorbook();
        errorbook.setUserCode(userMsg.getUserCode());
        errorbook.setWordId(word.getWordId());
        errorbook.setWordName(word.getName());
        errorbook.setMeaning(word.getMeaning());
        Integer userNo;
        System.out.println(errorbookService.getErrorBook(userMsg.getUserCode()));
        if ((errorbookService.getErrorBook(userMsg.getUserCode())).isEmpty()){
            userNo=1;
        }else {

            userNo = errorbookService.getMaxNoWord(userMsg.getUserCode()).getUsersNo();
            userNo++;
        }
        errorbook.setUsersNo(userNo);
        errorbookService.addOneWord(errorbook);

//        session.setAttribute("completion",completion);
//        int over = (int)session.getAttribute("over");
//        session.setAttribute("over",over);
         //找到下一个
        //ObjectMapper mapper = new ObjectMapper();
        Word wordNext = wordService.getWord(learning.getLearningBookId(),learningNo);
        //return mapper.writeValueAsString(wordNext.getName());
        return wordNext.getName();
    }


    public String nextOne(HttpSession session){
        Msg userMsg = (Msg) session.getAttribute("UserMsg");
        Learning learning = learningService.getUserLearn(userMsg.getUserCode());
        //学习进度+1
        int learningNo = learning.getLearningNo();
        learningNo++;

        while (wordService.getWord(learning.getLearningBookId(),learningNo)==null){
            learningNo++;
        }
        learningService.updatalearningNo(userMsg.getUserCode(),learningNo);
        int completion = learningService.getCompletion(userMsg.getUserCode())+1;
        learningService.updataCompletion(userMsg.getUserCode(), completion);
        Word wordNext = wordService.getWord(learning.getLearningBookId(),learningNo);
        return wordNext.getName();
    }

    public String previousOne(HttpSession session){
        Msg userMsg = (Msg) session.getAttribute("UserMsg");
        Learning learning = learningService.getUserLearn(userMsg.getUserCode());
        //学习进度-1
        int learningNo = learning.getLearningNo();
        learningNo--;

        while (wordService.getWord(learning.getLearningBookId(),learningNo)==null){
            learningNo--;
        }
        learningService.updatalearningNo(userMsg.getUserCode(),learningNo);
        int completion = learningService.getCompletion(userMsg.getUserCode())-1;
        learningService.updataCompletion(userMsg.getUserCode(), completion);
        Word wordNext = wordService.getWord(learning.getLearningBookId(),learningNo);
        return wordNext.getName();

    }


    public Word getPraPreWord(HttpSession session){
//        Msg userMsg = (Msg) session.getAttribute("UserMsg");
//        Practice practice = practiceService.getUserPractice(userMsg.getUserCode());
//        //判断用户练习的题库是哪个
//        Integer wordId;
//        String bookId = practice.getLearningBookId();
//        if (Objects.equals(bookId, "errorbook")){
//            wordId = practice.getErrorNo();
//        }else if (Objects.equals(bookId, "learning")){
//            bookId = learningService.getLearningBookId(userMsg.getUserCode());
//            wordId = practice.getLearningNo();
//        }else if (Objects.equals(bookId, "learned")){
//            bookId = learningService.getLearningBookId(userMsg.getUserCode());
//            wordId = practice.getCompletion();
//        }else {
//            return null;
//        }
        Msg userMsg = (Msg) session.getAttribute("UserMsg");
        ArrayList<Object> list = tool.getPraWordTool(session);
        String bookId = (String) list.get(0);
        int wordId = (int) list.get(1);
        //若未开始或上一词不存在
        if (wordId==1){
            return null;
        }
        //若题目出自错题本
        if (Objects.equals(bookId, "errorbook")){

            Errorbook errorbook = errorbookService.getWordByUsersNo(userMsg.getUserCode(), wordId-1);
            Word word = new Word();
            word.setWordId(errorbook.getWordId());
            word.setName(errorbook.getWordName());
            word.setMeaning(errorbook.getMeaning());
            return word;
        }

        return wordService.getWord(bookId,wordId-1);
    }

    public ArrayList<Object> getPraWordTool(HttpSession session){
        Msg userMsg = (Msg) session.getAttribute("UserMsg");
        Practice practice = practiceService.getUserPractice(userMsg.getUserCode());
        //判断用户练习的题库是哪个
        Integer wordId;
        String bookId = practice.getLearningBookId();
        if (Objects.equals(bookId, "errorbook")){
            wordId = practice.getErrorNo();
        }else if (Objects.equals(bookId, "learning")){
            bookId = learningService.getLearningBookId(userMsg.getUserCode());
            wordId = practice.getLearningNo();
        }else if (Objects.equals(bookId, "learned")){
            bookId = learningService.getLearningBookId(userMsg.getUserCode());
            wordId = practice.getCompletion();
        }else {
            return null;
        }

        ArrayList<Object> list = new ArrayList<>();
        list.add(bookId);
        list.add(wordId);
        return list;
    }



    public Word getPraWord(HttpSession session){
//        Msg userMsg = (Msg) session.getAttribute("UserMsg");
//        Practice practice = practiceService.getUserPractice(userMsg.getUserCode());
//        //判断用户练习的题库是哪个
//        Integer wordId;
//        String bookId = practice.getLearningBookId();
//        if (Objects.equals(bookId, "errorbook")){
//            wordId = practice.getErrorNo();
//        }else if (Objects.equals(bookId, "learning")){
//            bookId = learningService.getLearningBookId(userMsg.getUserCode());
//            wordId = practice.getLearningNo();
//        }else if (Objects.equals(bookId, "learned")){
//            bookId = learningService.getLearningBookId(userMsg.getUserCode());
//            wordId = practice.getCompletion();
//        }else {
//            return null;
//        }
        Msg userMsg = (Msg) session.getAttribute("UserMsg");
        ArrayList<Object> list = tool.getPraWordTool(session);
        String bookId = (String) list.get(0);
        int wordId = (int) list.get(1);
        if (Objects.equals(bookId, "errorbook")){
            if (wordId>=errorbookService.getMaxNoWord(userMsg.getUserCode()).getUsersNo()){
                return null;
            }
            Errorbook errorbook = null;
            errorbook = errorbookService.getWordByUsersNo(userMsg.getUserCode(), wordId);
            while (errorbook==null){
                wordId++;
                errorbook = errorbookService.getWordByUsersNo(userMsg.getUserCode(), wordId);
            }
            Word word = new Word();
            word.setWordId(errorbook.getWordId());
            word.setName(errorbook.getWordName());
            word.setMeaning(errorbook.getMeaning());
            return word;
        }
        if(wordId>=wordService.getWordCount(bookId)){
            return null;
        }
        return wordService.getWord(bookId,wordId);

    }


    public void updataPraWord(HttpSession session){
        Msg userMsg = (Msg) session.getAttribute("UserMsg");
        //Practice practice = practiceService.getUserPractice(userMsg.getUserCode());
        //完成部分不变
        Integer completion = practiceService.getCompletion(userMsg.getUserCode());
        if (completion==null){
            completion = 1;
            practiceService.updataCompletion(userMsg.getUserCode(),completion);
        }
        //更新背记处节点
        Integer learningNo = learningService.getLearningNo(userMsg.getUserCode());
        if (learningNo!=null){
            practiceService.updataLearningNo(userMsg.getUserCode(),learningNo);
        }else {
            practiceService.updataLearningNo(userMsg.getUserCode(),1);
        }

        //错题本处不变
        Integer errorNo = practiceService.getErrorNo(userMsg.getUserCode());
        if (errorNo==null){
            errorNo = 1;
            practiceService.updataErrorNo(userMsg.getUserCode(), errorNo);
        }
    }

    public void updataPraBook(HttpSession session,String bookId){
        Msg userMsg = (Msg) session.getAttribute("UserMsg");
        Practice practice = practiceService.getUserPractice(userMsg.getUserCode());
        practiceService.updataLearningBookId(userMsg.getUserCode(),bookId);
    }


    //获得随机题目
    public ArrayList<String> getAwsWordOnLearn(Word word,HttpSession session){
        Msg userMsg = (Msg) session.getAttribute("UserMsg");
        //String bookId = learningService.getLearningBookId(userMsg.getUserCode());

        Practice practice = practiceService.getUserPractice(userMsg.getUserCode());
        String bookId = practice.getLearningBookId();
//        if (Objects.equals(bookId, "learning")){
//            bookId = learningService.getLearningBookId(userMsg.getUserCode());
//        }else if (Objects.equals(bookId, "learned")){
//            bookId = learningService.getLearningBookId(userMsg.getUserCode());
//        }

        bookId = learningService.getLearningBookId(userMsg.getUserCode());

        int wordId = word.getWordId();
        int oneId =(int) ( Math.random()*10+1);
        int twoId = 0;
        int threeId = 0;
        do {
            twoId =(int) ( Math.random()*10+1);
        }
        while (twoId == oneId);
        do {
            threeId =(int) ( Math.random()*10+1);
        }
        while (threeId == oneId || threeId == twoId);
        Word wordOne = wordService.getWord(bookId,wordId+oneId);
        Word wordTwo = wordService.getWord(bookId,wordId+twoId);
        Word wordThree = wordService.getWord(bookId,wordId+threeId);

        ArrayList<String> wordListF = new ArrayList<>();
        wordListF.add(wordOne.getMeaning());
        wordListF.add(wordTwo.getMeaning());
        wordListF.add(wordThree.getMeaning());


        int No = (int) ( Math.random()*4);//[0,1)*4+1=[1,5)
        ArrayList<String> wordList = new ArrayList<>();
        //List<String> wordList = null;
        for (int i=0;i<4;i++){
            if (i==No){
                wordList.add(i,word.getMeaning());
            }else if(i<No){
                wordList.add(i,wordListF.get(i));
            }else {
                wordList.add(i,wordListF.get(i-1));
            }
        }
        wordList.add(4, String.valueOf(No));
        return wordList;
    }
}
