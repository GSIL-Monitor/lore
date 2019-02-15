package mng.web.lore;

import com.alibaba.fastjson.JSONObject;
import mng.model.lore.Lore;
import mng.service.lore.KnowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="ehoac@sina.com">ehoac</a>
 */
@Controller
@RequestMapping("/lore/know")
public class KnowController {
    private KnowService knowService;
    @Autowired
    public KnowController(KnowService knowService){
        this.knowService = knowService;
    }
    @RequestMapping("/index")
    public String index(ModelMap model){
        model.put("knows",knowService.findListByParentCode("0"));
        return "lore/know/know_index";
    }

    @RequestMapping("/downloadToolPage")
    public String downloadToolPage(HttpServletRequest request, ModelMap model){
        Map<String,String> fileNameMap = new HashMap<>(5);
        URL url = this.getClass().getClassLoader().getResource("");
        if(url != null){
            File file = new File(url.getPath() + "/static/download/lore");
            if(file.isDirectory()){
                File[] files = file.listFiles();
                if(files != null){
                    for(File f:files){
                        String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
                        fileNameMap.put(f.getName(), path + "/static/download/lore/" + f.getName());
                    }
                    model.put("fileNameMap", fileNameMap);
                }
            }
        }
        return "lore/know/download_tool";
    }

    @RequestMapping( value = "/getLoreByCode", method = RequestMethod.POST)
    @ResponseBody
    public Lore getLoreByCode(@RequestBody JSONObject params){
        String code = params.getString("code");
        return knowService.findByCode(code);
    }

    @RequestMapping( value = "/getLoreByParentCode", method = RequestMethod.POST)
    @ResponseBody
    public List<Lore> getLoreByParentCode(@RequestBody JSONObject params){
        String code = params.getString("code");
        return knowService.findListByParentCode(code);
    }
}
