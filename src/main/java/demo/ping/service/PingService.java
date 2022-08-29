package demo.ping.service;

import demo.ping.vo.FileVo;

public interface PingService {
    FileVo writeFile();

    String sendFileMessage(FileVo fileVo);
}
