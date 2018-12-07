package hdn.examples.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hello")
public class BonjourController {

    @RequestMapping(method = RequestMethod.GET)
    public String afficherBonjour(final ModelMap pModel, @RequestParam(value="age") final String age) {
        pModel.addAttribute("personne", "Umberto");
        pModel.addAttribute("age", age);
        
        return "bonjourSpring";
    }
}

