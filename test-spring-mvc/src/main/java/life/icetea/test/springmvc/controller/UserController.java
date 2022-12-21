package life.icetea.test.springmvc.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author icetea
 */
@RequestMapping("user")
@RestController
public class UserController {


    /**
     * {@link  InitBinder} 注解标注在controller的方法上，给当前controller注册一个属性编辑器（帮助完成参数绑定），只对当前Controller有效。
     * 若想对所有controller都生效，可以在标注了@ControllerAdvice类的方法上使用该注解
     * 相似的注解还包括
     * 1. {@link ExceptionHandler} 用于处理controller中的异常
     * 2. {@link ModelAttribute} 用于绑定键值到Model中
     *
     * @param binder {@link  InitBinder} 注解标注的方法必须有一个WebDataBinder参数（用于设置参数之间的绑定关系，如日期数据的映射格式）
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor customDateEditor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, customDateEditor);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String testFormatData(Date date) {
        Map<String, Object> map = new HashMap<>();
        map.put("date", date);
        return map.toString();
    }

}
