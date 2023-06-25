package com.sp5.cmn.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class MyEmitService {

    private int myNumber = 0;

    private static final int REPEAT = 3;
    private final Map<ResponseBodyEmitter, AtomicInteger> emitterCountMap = new HashMap<>();

    public void add(ResponseBodyEmitter emitter){
        emitterCountMap.put(emitter, new AtomicInteger(0));
    }

    @Scheduled(fixedRate = 1000L)
    public void emit(){

        List<ResponseBodyEmitter> removedEmitter = new ArrayList<>(emitterCountMap.size());

        for(Map.Entry<ResponseBodyEmitter, AtomicInteger> entry : emitterCountMap.entrySet()){
            myNumber++;
            Integer cnt = entry.getValue().incrementAndGet();

            //String str = new RestTemplate().getForObject("https://jsonplaceholder.typicode.com/users/{id}", String.class, cnt);
            String str = "변경된 데이터 " + myNumber;
            log.debug("생성 및 보냅니다 ---------> " + str);
            ResponseBodyEmitter emitter = entry.getKey();

            try {
                emitter.send(str);
            }catch (IOException e){
                removedEmitter.add(emitter);
            }

            if(cnt >= cnt){
                removedEmitter.add(emitter);
            }

            for(ResponseBodyEmitter e : removedEmitter){
                emitterCountMap.remove(e);
            }
        }
    }
}
