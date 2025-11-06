package co.edu.poli.cajeroapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.edu.poli.cajeroapp.entities.Cajero;

@Controller
@RequestMapping("/cajero")
public class CajeroController {
	 private Cajero cajero = new Cajero(); 

	    @GetMapping("/")
	    public String index(Model model) {
	        model.addAttribute("dineroTotal", cajero.getDineroTotal());
	        model.addAttribute("billetes", cajero.getBilletesDisponibles());
	        return "index";
	    }
}
