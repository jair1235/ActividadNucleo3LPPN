package co.edu.poli.cajeroapp.services;

import java.math.BigDecimal;

import co.edu.poli.cajeroapp.entities.Cliente;

public interface IClienteService {
	Cliente validarCliente(Long documento, String clave);
	void consignar(Cliente cliente, BigDecimal monto);
	void retirar(Cliente cliente, BigDecimal monto);
	Cliente obtenerClientePorDocumento(Long documento);
    void guardarCliente(Cliente cliente); 

}
