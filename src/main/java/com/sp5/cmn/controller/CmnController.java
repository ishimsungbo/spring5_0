package com.sp5.cmn.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class CmnController {

    @RequestMapping(value = "/")
    public String index(){
        log.info("============================================");
        log.info("Connected ! spring 5 project");
        log.info("============================================");

        System.out.println("--------------------------->");

        log.debug("**********************************************");

        return "index";
    }

}
