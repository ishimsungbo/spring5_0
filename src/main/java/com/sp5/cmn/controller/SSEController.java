package com.sp5.cmn.controller;


import com.sp5.cmn.service.MyEmitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@Slf4j
public class SSEController {

    @Autowired
    private MyEmitService myEmitService;

    @GetMapping(value = "/sseTest", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public String sseTest() {
        return "123";
    }

    @GetMapping(value = "/sseTest2", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseBodyEmitter sseTest2() {
        log.debug("SSe Call debug =================================");

        SseEmitter emitter = new SseEmitter();
        myEmitService.add(emitter);

        log.debug("실행했습니다. ResponseBodyEmitter sseTest2");

        return emitter;
    }

    @GetMapping(value = "/server")
    public String server(){
        log.debug("debug =================================");
        log.debug("안녕하세요. debug");
        log.info("안녕하세요. info");
        return "HI MY NAME IS SERVER EXAMPLE-안녕하세요";
    }

}
