package co.edu.poli.cajeroapp.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.poli.cajeroapp.entities.Cliente;
import co.edu.poli.cajeroapp.services.IClienteService;
import co.edu.poli.cajeroapp.services.impl.ClienteService;
import jakarta.servlet.http.HttpSession;

@Controller
public class ClienteController {

    private final ClienteService clienteService_1;
	@Autowired
	private IClienteService clienteService;

    ClienteController(ClienteService clienteService_1) {
        this.clienteService_1 = clienteService_1;
    }
	@GetMapping("/")
	public String index(Model model) {
		return "index";
	}
	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}
	@PostMapping("/login")
	public String loginSubmit(@RequestParam("documento") String documento, @RequestParam("clave") String clave,
			HttpSession session,
			Model model) {
		Cliente cliente = clienteService.validarCliente(Long.parseLong(documento), clave);
		if(cliente !=null ) {
			session.setAttribute("cliente", cliente);
			return "redirect:/menu";
		}else {
			model.addAttribute("error", "credenciales invalidas");
			return "login";
		}
	}
	@GetMapping("/menu")
	public String menu(HttpSession session, Model model) {
	    Cliente cliente = (Cliente) session.getAttribute("cliente");
	    if (cliente == null) {
	        return "redirect:/login";
	    }
	    model.addAttribute("cliente", cliente);
	    return "menu";
	}

	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:/";
	}
	@PostMapping("/retirar")

	public String retirar(@RequestParam("monto") BigDecimal monto, HttpSession session, Model model) {
	    Cliente cliente = (Cliente) session.getAttribute("cliente");
	    if (cliente == null) {
	        return "redirect:/login";
	    }

	    if (monto.compareTo(BigDecimal.ZERO) <= 0) {
	        model.addAttribute("error", "El monto debe ser mayor que cero.");
	    } else if (cliente.getSaldo().compareTo(monto) < 0) {
	        model.addAttribute("error", "Fondos insuficientes.");
	    } else {
	        cliente.setSaldo(cliente.getSaldo().subtract(monto));
	        clienteService.guardarCliente(cliente); 

	        model.addAttribute("mensaje", "Has retirado $" + monto + " exitosamente.");
	    }
	    
	    model.addAttribute("cliente", cliente);
	    return "menu";
	}

	@PostMapping("/consignar")
	public String consignar(@RequestParam("monto") BigDecimal monto, HttpSession session, Model model) {
	    Cliente cliente = (Cliente) session.getAttribute("cliente");
	    if (cliente == null) {
	        return "redirect:/login";
	    }

	    if (monto.compareTo(BigDecimal.ZERO) <= 0) {
	        model.addAttribute("error", "El monto debe ser mayor que cero.");
	    } else {
	        cliente.setSaldo(cliente.getSaldo().add(monto));
	        clienteService.guardarCliente(cliente); 

	        model.addAttribute("mensaje", "Has consignado $" + monto + " exitosamente.");
	    }

	    model.addAttribute("cliente", cliente);
	    return "menu";
	}

	@PostMapping("/transferir")
	public String transferir(@RequestParam("destinatario") Long destinatario,
	                         @RequestParam("monto") BigDecimal monto,
	                         HttpSession session, Model model) {
	    Cliente cliente = (Cliente) session.getAttribute("cliente");
	    if (cliente == null) {
	        return "redirect:/login";
	    }

	    Cliente destino = clienteService.obtenerClientePorDocumento(destinatario);
	    if (destino == null) {
	        model.addAttribute("error", "El destinatario no existe.");
	    } else if (monto.compareTo(BigDecimal.ZERO) <= 0) {
	        model.addAttribute("error", "El monto debe ser mayor que cero.");
	    } else if (cliente.getSaldo().compareTo(monto) < 0) {
	        model.addAttribute("error", "Fondos insuficientes para transferir.");
	    } else {
	        cliente.setSaldo(cliente.getSaldo().subtract(monto));
	        destino.setSaldo(destino.getSaldo().add(monto));
	        clienteService.guardarCliente(cliente);
	        clienteService.guardarCliente(destino);
	        model.addAttribute("mensaje", "Transferencia de $" + monto + " a " + destino.getNombre() + " exitosa.");
	    }

	    model.addAttribute("cliente", cliente);
	    return "menu";
	}
}
