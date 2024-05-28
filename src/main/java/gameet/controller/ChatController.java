package gameet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import gameet.service.StreamChatService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ChatController {

    @Autowired
    private StreamChatService chatSrv;

    @GetMapping("/api/generaTokenChat")
    public String generarTokenChat(HttpServletRequest request) {
        String apiKeySecret = "kkhwsa8ss3ke";
        String username = (String) request.getAttribute("username");
        return chatSrv.generaToken(username);
    }
 
}
