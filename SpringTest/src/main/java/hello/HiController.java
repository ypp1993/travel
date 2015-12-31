package hello;

/**
 * Created by peipeiyang on 15/12/18.
 */
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HiController {

    @RequestMapping("/hi")
    public String hi(@RequestParam(value="user", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("user", name);
        return "hi";
    }

}
