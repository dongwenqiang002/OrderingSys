package indi.dwq.orderingSys.app.controller;

import indi.dwq.orderingSys.app.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    @Value("${file.dir}")
    private static String fileDir = "D:\\Desktop\\PROJECT\\OrderingSys\\file";

    @Autowired
    private FileService fileService;


    @GetMapping("/img/{name}.{type}")
    public void valicode(@PathVariable("name") String name, @PathVariable("type") String type,
                         HttpServletResponse response) throws Exception {
        LOGGER.info("图片获取: {} . {}", name, type);
        LOGGER.info(fileDir + "/img/" + name + "." + type);
        //将图片输出给浏览器
        BufferedImage image = ImageIO.read(new File(fileDir + "/img/" + name + "." + type));
        response.setContentType("image/" + type);
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, type, os);
    }



    /**
     * 验证码获取
     * */
    @GetMapping("/verCode")
    public void verCode(HttpServletResponse response, HttpSession session) throws IOException{

        response.setContentType("image/JPG" );
        OutputStream os = response.getOutputStream();
        BufferedImage img = new BufferedImage(80, 40, BufferedImage.TYPE_INT_RGB);
        String code = fileService.verCode(img);
        LOGGER.info("验证码为: {}",code);
        session.setAttribute("code",code);
        ImageIO.write(img, "JPG", os);
    }


}
