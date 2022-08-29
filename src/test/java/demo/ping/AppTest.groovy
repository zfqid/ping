package demo.ping

import demo.ping.service.PingService
import demo.ping.util.FileUtil
import demo.ping.vo.FileVo
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spock.lang.Specification;

//@ActiveProfiles("dev")
//@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
class AppTest extends Specification {
    @Autowired
    private PingService pingService;
    def "crate file test"() {
        given:
            def fileName = System.currentTimeMillis()+".txt";
            def filePath = "/ping/testFile"
            def fileVo = new FileVo();
            def content = "hello";
            fileVo.setFilePath(filePath);
            fileVo.setFileName(fileName);
        when:
            FileUtil.ceateFileByContent(fileVo,content)
        then:
            content.equals(FileUtil.getFileContent(fileVo.getFilePath()+File.separator+fileVo.getFileName()));
    }

    def "write file test"() {
        given:
        when:
        def fileVo = pingService.writeFile()
        then:
        "true".equals(fileVo.getCreated())
        "hello".equals(FileUtil.getFileContent(fileVo.getFilePath()+File.separator+fileVo.getFileName()));
    }
    def "send file test"() {
        given:
            def fileName = System.currentTimeMillis()+".txt";
            def filePath = "/ping/testFile"
            def fileVo = new FileVo();
            def content = "hello";
            fileVo.setFilePath(filePath);
            fileVo.setFileName(fileName);
            fileVo.setCreated("true");
        when:
            FileUtil.ceateFileByContent(fileVo,content)
            String result = pingService.sendFileMessage(fileVo);
        then:
            result.indexOf("data:success")==0
    }
}
