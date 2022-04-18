package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String rollDiceGuess(@PathVariable int n, Model model){
        model.addAttribute("guessedNumber", n);
        int correctNum = (int) Math.floor(Math.random()*(6-1+1)+1);
        model.addAttribute("correctNum", correctNum);
        return "roll-dice";
    }


}
