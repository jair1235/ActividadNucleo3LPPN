package co.edu.poli.cajeroapp.repositories;

import org.springframework.data.repository.CrudRepository;

import co.edu.poli.cajeroapp.entities.Cliente;

public interface IClienteRepository extends CrudRepository<Cliente, Long>{

}
