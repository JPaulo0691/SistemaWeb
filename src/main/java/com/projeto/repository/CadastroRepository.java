package com.projeto.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projeto.model.Cliente;

@Repository
public interface CadastroRepository extends CrudRepository<Cliente, String>{
	Cliente findById(Long id);
	Cliente deleteById(Long id);
}

// quando criar a sua repository vá para seu controller, e crie uma anotation chamada @Autowired
// para chamar a sua Repository.
