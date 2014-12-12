package hms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by sadika on 2014-12-12.
 */
@Controller
@RequestMapping("home")
public class MainController {

    @RequestMapping("view")
    public String home() {
        return "home";
    }
}
