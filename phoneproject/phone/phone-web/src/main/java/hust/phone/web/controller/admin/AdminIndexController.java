package hust.phone.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller(value = "AdminIndexController")
public class AdminIndexController {
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String loginIndex(){
        return "login";
    }
}
