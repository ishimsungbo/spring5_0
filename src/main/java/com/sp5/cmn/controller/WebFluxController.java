package com.sp5.cmn.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class WebFluxController {

    @GetMapping(value = "/server")
    public String server(){
        log.debug("debug =================================");
        log.debug("안녕하세요. debug");
        log.info("안녕하세요. info");
        return "HI MY NAME IS SERVER EXAMPLE-안녕하세요";
    }

    //TODO

}
