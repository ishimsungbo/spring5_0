package com.sp5.cmn.controller;

import com.sp5.cmn.service.SseEmitters;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@RestController
@Slf4j
public class SseController2 {

    @Autowired
    private SseEmitters sseEmitters;

    public SseController2(SseEmitters sseEmitters) {
        this.sseEmitters = sseEmitters;
    }

    @GetMapping(value = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> connect(){

        SseEmitter emitter = new SseEmitter();
        sseEmitters.add(emitter);


        log.info("데이터를 보냅니다");

        try {
            emitter.send(SseEmitter.event()
                    .name("클라이언트에게 데이터 보내는 이벤트")
                    .data("데이터를 보냅니다."));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(emitter);
    }

}
