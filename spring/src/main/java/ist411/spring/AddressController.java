/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist411.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author drdea
 */
@Controller
public class AddressController {
    @RequestMapping(value="/address", params="name")
    public String sendForm(@RequestParam(value="name", required=true) String name,
                          @RequestParam(value="street", required=true) String street,
                          @RequestParam(value="state", required=true) String state,
                          @RequestParam(value="zip", required=true) int zip,
                          Model model) {
        model.addAttribute("name", name);
        model.addAttribute("name", street);
        model.addAttribute("state", state);
        model.addAttribute("zip", zip);
        return "address";
    }
    @RequestMapping("/address")
    public String displayForm(Model model) {
        return "form";
    }
}
