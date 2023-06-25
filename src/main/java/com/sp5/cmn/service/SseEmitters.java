package com.sp5.cmn.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@Component
public class SseEmitters {
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    public SseEmitter add(SseEmitter emitter){
        this.emitters.add(emitter);
        log.info("new emitter added:{}", emitter);
        log.info("emitter list size:{}", emitters.size());

        emitter.onCompletion(()->{
            log.info("onCompletion callback");
            this.emitters.remove(emitter);
        });

        emitter.onTimeout(()->{
            log.info("onTimeout");
            emitter.complete();
        });

        return emitter;
    }
}
