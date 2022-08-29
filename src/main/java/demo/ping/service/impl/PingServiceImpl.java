package demo.ping.service.impl;

import demo.ping.service.PingService;
import demo.ping.util.FileUtil;
import demo.ping.vo.FileVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;

@Service
public class PingServiceImpl implements PingService {
    private  static Logger logger = LoggerFactory.getLogger(PingServiceImpl.class);
    @Value("${filePath}")
    private String filePath;
    @Value("${pongUrl}")
    private String pongUrl;
    private RestTemplate restTemplate = new RestTemplate();
    @Override
    public FileVo writeFile() {
        long timeStamp = System.currentTimeMillis();
        String fileName = timeStamp+".txt";
        FileVo fileVo = new FileVo();
        fileVo.setFilePath(filePath);
        fileVo.setFileName(fileName);
        boolean result = false;
        try {
            fileVo.setCreated(FileUtil.ceateFileByContent(fileVo,"hello")+"");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileVo;
    }
    @Override
    public String sendFileMessage(FileVo fileVo){
        String result = "";
        if("true".equals(fileVo.getCreated())){
            result = restTemplate.postForEntity(pongUrl,fileVo, String.class).getBody();
        }
        return result;
    }

}
