package cq.techniques.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @PostMapping("/test")
    public Object test(){
        return "yyy";
    }
}
