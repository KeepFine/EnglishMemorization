package com.gxt.controller;

import com.gxt.pojo.Msg;
import com.gxt.service.ErrorbookService;
import com.gxt.service.MsgService;
import com.gxt.service.QueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/ToDo")
public class ToDoController {


    @Autowired
    private ErrorbookService errorbookService;

    @Autowired
    private MsgService msgService;

    @Autowired
    private QueService queService;

    @RequestMapping("/delErrorBook/{id}")
    public String delErrorBook(@PathVariable("id") Integer id){
        errorbookService.delOneWord(id);
        return "redirect:/toErroeBook";
    }


    @RequestMapping("/upload")
    public String singleFileUpload(HttpSession session, @RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
//            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/toPersonal";
        }

        try {
            // Get the file and save it somewhere
            Msg userMsg = (Msg) session.getAttribute("UserMsg");
            String fileName = UUID.randomUUID().toString().replace("-", "").toLowerCase()+file.getOriginalFilename();
            byte[] bytes = file.getBytes();
            Path path = Paths.get("D:/IdeaProjects/MyProject/target/classes/static/headpic/"+fileName);
            Files.write(path, bytes);
//D:\IdeaProjects\MyProject\target\classes\static\headpic\054a56cb78354bbb99f55aac81dda676MyLOGO.jpg.png
            //0d2dcfe716ea43629b7b8eede85ec4dbbook.png
//${session.userPicture}
            String pic = "headpic/"+fileName;
            msgService.updatePicture(userMsg.getId(),pic);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/toPersonal";
    }

    @RequestMapping("/updataUserMsg")
    public String updataUserMsg(HttpSession session,String userName,String mail,String ask){
        Msg userMsg = (Msg) session.getAttribute("UserMsg");
        msgService.updateName(userMsg.getId(), userName);
        msgService.updateMail(userMsg.getId(), mail);
        queService.updataAsk(userMsg.getUserCode(),ask);
        session.setAttribute("userName",userName);
        session.setAttribute("mail",mail);
        session.setAttribute("ask",ask);
        return "redirect:/toPersonal";
    }

    @RequestMapping("/updataPwd")
    public String updataPwd(HttpSession session,String pass,String userPassword){
        Msg userMsg = (Msg) session.getAttribute("UserMsg");
        if (Objects.equals(pass, userMsg.getUserPassword())){
            if (userPassword!=null){
                msgService.updatePassword(userMsg.getId(), userPassword);
            }
        }
        return "redirect:/toPersonal";
    }

}
