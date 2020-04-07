package cq.techniques.controller;

import cq.techniques.service.FreeMarkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
    @Autowired
    private FreeMarkerService freeMarkerService;

    @GetMapping("/test")
    public Object test(){
        freeMarkerService.createHtmlByFreeMarker();
        return "ok";
    }
}
