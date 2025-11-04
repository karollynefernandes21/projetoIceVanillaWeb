
package com.dev.IceVanilla.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Leva para tela inicial
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
