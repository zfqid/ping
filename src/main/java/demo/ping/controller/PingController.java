package demo.ping.controller;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;



@RestController
public class PingController {
    @RequestMapping(value = "/out",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<String> out(){
        return Mono.just("Hello Mono");
    }

}
