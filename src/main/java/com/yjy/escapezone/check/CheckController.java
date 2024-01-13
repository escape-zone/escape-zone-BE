package com.yjy.escapezone.check;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class CheckController {
    @GetMapping("/check")
    public String check() {
        return "OK";
    }

}
