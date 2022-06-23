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
