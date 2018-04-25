package indi.dwq.orderingSys.app.controller;

import indi.dwq.orderingSys.app.service.EateryService;
import indi.dwq.orderingSys.app.service.FileService;
import indi.dwq.orderingSys.data.pojo.Eatery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 董文强
 * @Time 2018/4/15 11:04
 */
@Controller
@RequestMapping("/admin/eatery")
public class AdminEateryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminEateryController.class);

    @Autowired
    private EateryService eateryService;
    @Autowired
    private FileService fileService;

    /**
     * 添加商家
     */
    @GetMapping("/add.html")
    public ModelAndView addorupdateEatery(Integer eateryid) {
        LOGGER.info("eateryid: {}", eateryid);

        ModelAndView mv = new ModelAndView("/admin/eateryAddModal");
        if (eateryid == null || eateryid == 0) {
            Eatery eatery = new Eatery();
            eatery.setId(0);
            mv.addObject("eatery", eatery);
        } else {
            mv.addObject("eatery", eateryService.getEateryAllDeatil(eateryid));
        }
        return mv;
    }

    /**
     * 更新商家
     */
    @PostMapping("/update")
    @ResponseBody
    public boolean updateOrAddEatery(Eatery eatery, MultipartFile pic) {
        LOGGER.info(eatery.toString());
        if (pic != null) {
            String imgUrl = fileService.uploadImg(pic);
            if (imgUrl != null) eatery.setImgUrl(imgUrl);
        }
        if (eatery != null && eatery.getId() != 0) {
            return eateryService.updateEatery(eatery);
        } else {
            return eateryService.addEatery(eatery);
        }
    }

}
