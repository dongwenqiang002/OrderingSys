package indi.dwq.orderingSys.util;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import indi.dwq.orderingSys.data.pojo.Food;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author 董文强
 * @Time 2018/4/10 17:48
 */
public class PageUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(PageUtil.class);



    /**
     * 分页工具
     * @param mv            需要被加入的modelandview
     * @param pageNum       当前页
     * @param pageSize      每页记录数
     * @param resultName    存入model的名字
     * @param selectList    查询语句
     * @return              发生异常返回false ,其他:true
     * */
    public static Boolean paging(String resultName, ModelAndView mv, Integer pageSize, Integer pageNum, SelectList selectList) {
        try {
            Page page = PageHelper.startPage(pageNum, pageSize);
            List foodList = selectList.run();
            if (pageNum > page.getPages()) {
                pageNum = page.getPages();
                page = PageHelper.startPage(pageNum, 12);
                foodList = selectList.run();
            }
            mv.addObject(resultName, foodList);
            mv.addObject("total", page.getTotal());
            mv.addObject("pages", page.getPages());
            mv.addObject("pageNum", pageNum);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public interface SelectList {
        List run();
    }
}
