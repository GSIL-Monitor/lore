package mng.web.lore;

import mng.service.common.ToastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author <a href="ehoac@sina.com">ehoac</a>
 */
@Controller
@RequestMapping("/lore/notice")
public class NoticeController {
    private ToastService toastService;
    @Autowired
    public NoticeController(ToastService toastService){
        this.toastService = toastService;
    }

    @RequestMapping("/index")
    public String index(ModelMap model){
        model.put("warnMsg", "ddd");
        return "lore/notice/notice_index";
    }
}
