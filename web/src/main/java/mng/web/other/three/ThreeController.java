package mng.web.other.three;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author oac
 */
@Component
@RequestMapping("three")
public class ThreeController {
    @RequestMapping("index")
    public String index(){
        return "other/three/index";
    }

    @RequestMapping("first")
    public String first(){
        return "other/three/first";
    }
}
