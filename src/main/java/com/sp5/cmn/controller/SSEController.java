package com.sp5.cmn.controller;


import com.sp5.cmn.service.MyEmitService;
import com.sp5.cmn.service.SseEmitters;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@RestController
@Slf4j
public class SSEController {

    @Autowired
    private MyEmitService myEmitService;

    @GetMapping(value = "/sseTest", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseBodyEmitter sseTest2() {
        log.debug("SSe Call debug =================================");

        SseEmitter emitter = new SseEmitter();
        myEmitService.add(emitter);

        log.debug("실행했습니다. ResponseBodyEmitter sseTest2");

        return emitter;
    }

    /*
    @GetMapping(value = "/sseTest3", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> sseTest3() {
        log.debug("SSe Call debug =================================");

        SseEmitter emitter = new SseEmitter();
        myEmitService.add(emitter);

        log.debug("실행했습니다. ResponseBodyEmitter sseTest2");

        return ResponseEntity.ok(emitter);
    }*/

    //TODO 프론트에 지속적으로 전달하는 TEXT_EVENT_STREAM_VALUE 를 만든다.
}
