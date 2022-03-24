package com.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projeto.model.Cliente;
import com.projeto.repository.CadastroRepository;

@Controller
public class ClienteController {
	
	@Autowired
	CadastroRepository cadastramento;
	
	@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
	public String form() {
		return "cadastros/cadastroCliente";
	}
	
	@RequestMapping(value = "/cadastro", method = RequestMethod.POST)
	public String form(Cliente cliente) {
		
		// persistindo no BD
		cadastramento.save(cliente);		
		return "redirect:/cadastro";
	}
	
	@RequestMapping("/lista")
	public ModelAndView listaCliente() {
		
		ModelAndView mv = new ModelAndView("cadastros/listarCliente.html");
		Iterable<Cliente> clientes = cadastramento.findAll();		
		// no atributo nome é o msm que foi definido lá no index => <div th:each = "cliente: ${clientes}">
		mv.addObject("clientes", clientes);
		
		return mv;
	}
	
	@RequestMapping(value = "/lista/{id}")
	public String deletarCadastro(@PathVariable("id")Long id) {
		
		Cliente cliente = cadastramento.findById(id);
		cadastramento.delete(cliente);		
		return "cadastros/listarCliente";
	}
	
	@RequestMapping(value = "/atualizar/{id}")
	public String atualizarCadastro(@PathVariable("id")Long id, Cliente clientes) {
		
		Cliente cliente = cadastramento.findById(id);
		cliente.setNome(clientes.getNome());
		cliente.setBairro(clientes.getBairro());
		cliente.setTelefone(clientes.getTelefone());
		cliente.setEndereco(clientes.getEndereco());
		
		cadastramento.save(cliente);
		
		return "cadastros/atualizarClientes";
	}
	
	
	
	
	
}
