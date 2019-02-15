package mng.web.lore;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author <a href="ehoac@sina.com">ehoac</a>
 */
@Controller
@RequestMapping("/lore")
public class LoreController {
    @RequestMapping("/index")
    public String index(){
        return "lore/lore_index";
    }

    @RequestMapping("/know")
    public String know(){
        return "redirect:/lore/know/index";
    }

    @RequestMapping("/notice")
    public String notice(){
        return "redirect:/lore/notice/index";
    }
}
