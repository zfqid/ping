package demo.ping.scheduled;

import demo.ping.service.PingService;
import demo.ping.vo.FileVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Random;

@Configuration
@EnableScheduling
public class WriteJob {
    Random random=new Random();
    @Autowired
    PingService pingService;
    @Scheduled(cron = "*/1 * * * * ?")
    public void scheduler() throws InterruptedException {
        Thread.sleep(random.nextInt(500));//Random number to prevent multiple services from executing at the same time
        FileVo fileVo = pingService.writeFile();
        pingService.sendFileMessage(fileVo);
    }
}
