package hbi.core.demo.controllers;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import hbi.core.demo.dto.Demo;
import hbi.core.demo.service.IDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class DemoController extends BaseController {
    @Autowired
    private IDemoService demoService;

    @RequestMapping("/demo/query")
    @ResponseBody
    public ResponseData selectList(HttpServletRequest request, Demo condition,
                                   @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                   @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pagesize) {
        System.out.println("=========================selectList==============================");
        IRequest iRequest = createRequestContext(request);
        List<Demo> datas = demoService.selectByDemo(iRequest, condition, page,
                pagesize);
        return new ResponseData(datas);
    }

    @RequestMapping(value = "/demo/submit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData submit(HttpServletRequest request, @RequestBody
            List<Demo> demos) {

        IRequest iRequest = createRequestContext(request);
        List<Demo> datas = demoService.batchUpdate(iRequest, demos);
        return new ResponseData(datas);
    }

    @RequestMapping(value = "/demo/remove", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody
            List<Demo> demos) {
        IRequest iRequest = createRequestContext(request);
        demoService.batchDelete(demos);
        return new ResponseData();
    }
    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}