package indi.dwq.orderingSys.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;

/**
 * @author 董文强
 * @Time 2018/3/19 17:16
 * <p>用来管理项目中文件的使用</p>
 */
@Controller
@RequestMapping("/file")
public class FileController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);


    @GetMapping("/img/{name}.{type}")
    public void valicode(@PathVariable("name") String name, @PathVariable("type") String type,
                         HttpServletResponse response) throws Exception {
        LOGGER.info("图片获取: {} . {}", name, type);
        //将图片输出给浏览器
        BufferedImage image = ImageIO.read(new File("E:\\abc.bmp"));
        response.setContentType("image/bmp");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "bmp", os);

    }

  /*  @PostMapping("/upload")
    public String */
}