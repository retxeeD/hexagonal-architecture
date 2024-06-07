package com.poclll.PoClll;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/poclll")
public class PoClllController {

    @GetMapping("/start")
    public String startApi(){
        return "The API is up.";
    }
}
