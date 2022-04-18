package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RollDiceController {
    @GetMapping("/roll-dice")
    public String rollDiceForm(){
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String rollDiceGuess(@PathVariable int n){
        return "roll-dice";
    }

    @PostMapping("/roll-dice")
    public String rollDiceDisplay(){
        return "roll-dice";
    }
}
