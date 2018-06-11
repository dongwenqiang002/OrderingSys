package indi.dwq.orderingSys.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

/**
 * @author 董文强
 * @Time 2018/4/16 9:28
 */
@Service
public class FileService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileService.class);

    @Value("${file.dir}")
    private static String fileDir = "E:\\Desktop\\project\\OrderingSys\\file";
    private Random random = new Random(System.currentTimeMillis());


    public String uploadImg(MultipartFile file) {
        if (file == null) {
            return null;
        }
        String name;//= System.currentTimeMillis() + random.nextLong()+file.getOriginalFilename();
        try {
            name = System.currentTimeMillis() + random.nextLong() + file.getOriginalFilename();
            FileCopyUtils.copy(file.getBytes(), new File(fileDir + "/img/" + name));
        } catch (IOException ioe) {
            LOGGER.error(ioe.getMessage());
            return null;
        }


        return name;
    }

    /**
     * 生成验证码图片文件
     */
    public String verCode(BufferedImage bufimg) {
        int width = 80;
        int height = 40;
        int lines = 10;
        String code = "";
        BufferedImage img = bufimg;//new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = img.getGraphics();

        //设置背景色
        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);//画背景
        //填充指定的矩形。使用图形上下文的当前颜色填充该矩形

        //设置字体
        g.setFont(new Font("黑体", Font.BOLD, 18));

        //随机数字
        Date d = new Date();
        //System.out.println(d.getTime());
        Random r = new Random(d.getTime());
        for (int i = 0; i < 4; i++) {
            int a = r.nextInt(10);//取10以内的整数[0，9]
            code += a;
            int y = 10 + r.nextInt(20); //10~30范围内的一个整数，作为y坐标
            Color c = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
            g.setColor(c);
            g.drawString("" + a, 5 + i * width / 4, y);
        }
        //干扰线
        for (int i = 0; i < lines; i++) {
            Color c = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
            g.setColor(c);
            g.drawLine(r.nextInt(width), r.nextInt(height), r.nextInt(width), r.nextInt(height));
        }
        g.dispose();//类似于流中的close()带动flush()---把数据刷到img对象当中
        //ImageIO.write(img, "JPG", new FileOutputStream("img/b.jpg"));
        //return img;
        return code;
    }

    /**
     * 生成验证码图片文件
     */
    public BufferedImage randomImg() {
        int width = 80;
        int height = 40;
        String code = "";
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = img.getGraphics();

        //设置背景色
        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);//画背景
        //填充指定的矩形。使用图形上下文的当前颜色填充该矩形

        //设置字体
        g.setFont(new Font("黑体", Font.BOLD, 18));

        int y = 25; //10~30范围内的一个整数，作为y坐标
        Color c = new Color(115, 115, 115);
        g.setColor(c);
        g.drawString("图片找不到啦", 5 + width, y);

        g.dispose();//类似于流中的close()带动flush()---把数据刷到img对象当中
        //ImageIO.write(img, "JPG", new FileOutputStream("img/b.jpg"));
        //return img;
        return img;
    }
}
