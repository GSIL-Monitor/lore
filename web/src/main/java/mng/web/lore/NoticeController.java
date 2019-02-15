package mng.web.lore;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author <a href="ehoac@sina.com">ehoac</a>
 */
@Controller
@RequestMapping("/lore/notice")
public class NoticeController {
    @RequestMapping("/index")
    public String index(){
        return "lore/notice/notice_index";
    }
}
