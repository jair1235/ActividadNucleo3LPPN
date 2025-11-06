package co.edu.poli.cajeroapp.services.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.poli.cajeroapp.entities.Cliente;
import co.edu.poli.cajeroapp.repositories.IClienteRepository;
import co.edu.poli.cajeroapp.services.IClienteService;
@Service
public class ClienteService implements IClienteService {
	@Autowired 
	private IClienteRepository clienteRepo;
	@Override
	public Cliente validarCliente(Long documento, String clave) {
	    Cliente cliente = clienteRepo.findById(documento).orElse(null);

	    if (cliente == null) {
	        return null;
	    }

	    if (cliente.isBloqueado()) {
	        if (cliente.getUltimaConexion() != null &&
	            java.time.temporal.ChronoUnit.MINUTES.between(
	                cliente.getUltimaConexion(), 
	                java.time.LocalDateTime.now()
	            ) >= 10) {

	            // validacion de cliente a los 10 min de bloqueado
	            cliente.setBloqueado(false);
	            cliente.setIntentosFallidos(0);
	            clienteRepo.save(cliente);

	        } else {
	            return null; 
	        }
	    }

	    if (!cliente.getClave().equals(clave)) {
	        int intentos = (cliente.getIntentosFallidos() == null) ? 0 : cliente.getIntentosFallidos();
	        cliente.setIntentosFallidos(intentos + 1);

	        if (cliente.getIntentosFallidos() >= 3) {
	            cliente.setBloqueado(true);
	            cliente.setUltimaConexion(java.time.LocalDateTime.now());
	        }

	        clienteRepo.save(cliente);
	        return null;
	    }

	    cliente.setIntentosFallidos(0);
	    cliente.setUltimaConexion(java.time.LocalDateTime.now());
	    clienteRepo.save(cliente);

	    return cliente;
	}



	@Override
	public void consignar(Cliente cliente, BigDecimal monto) {
	    cliente.setSaldo(cliente.getSaldo().add(monto));
	    clienteRepo.save(cliente);
	}

	@Override
	public void retirar(Cliente cliente, BigDecimal monto) {
	    cliente.setSaldo(cliente.getSaldo().subtract(monto));
	    clienteRepo.save(cliente);
	}

	@Override
	public Cliente obtenerClientePorDocumento(Long documento) {
	    return clienteRepo.findById(documento).orElse(null);
	}

	@Override
	public void guardarCliente(Cliente cliente) {
	    clienteRepo.save(cliente);
	}


}
