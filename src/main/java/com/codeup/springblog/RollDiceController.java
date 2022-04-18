package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RollDiceController {
    @GetMapping("/roll-dice/{n}")
    public String rollDiceForm(){
        return "roll-dice";
    }

    @PostMapping("/roll-dice")
    public String rollDiceDisplay(){
        return "roll-dice";
    }
}
