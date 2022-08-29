package demo.ping.util;

import demo.ping.vo.FileVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class FileUtil {
    private  static Logger logger = LoggerFactory.getLogger(FileUtil.class);
    public static boolean ceateFileByContent(FileVo fileVo, String content) throws IOException {

        File directory = new File(fileVo.getFilePath());
        if(!directory.isDirectory()) {
            directory.mkdirs();
        }
        File file = new File(fileVo.getFilePath()+File.separator+fileVo.getFileName());
        if(!file.createNewFile()) return false;
        logger.info("fileName：{}",fileVo.getFileName());
        try(FileOutputStream local = new FileOutputStream(file)){
            local.write(content.getBytes());
        }
        return true;
    }

    public static String getFileContent(String fileStr) {
        StringBuffer content = new StringBuffer("");
        try {
            File file = new File(fileStr);
            try (FileInputStream in = new FileInputStream(file)) {
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String line = "";

                while ((line = br.readLine()) != null) {
                    content.append(line);
                }
            }
        } catch (Exception e) {
        }
        return content.toString();
    }
}
