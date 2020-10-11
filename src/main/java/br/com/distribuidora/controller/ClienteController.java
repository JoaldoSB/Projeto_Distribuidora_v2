package br.com.distribuidora.controller;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.distribuidora.mode.Cliente;
import br.com.distribuidora.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@PostMapping
	public Cliente salvar(@RequestBody Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}	
	
	@GetMapping
	public List<Cliente> listar(){
		return this.clienteRepository.findAll();
	}
	
	@DeleteMapping("/{id}")
	public String remover(@PathVariable("id") Integer id) {
		this.clienteRepository.deleteById(id);
		return "Cliente informado deletado com sucesso!";
	}
	
	@PutMapping("/{id}")
	public Cliente editar(@PathVariable("id") Integer id, @RequestBody Cliente cliente) {
		Cliente clienteBD = this.clienteRepository.findById(id).get();
		BeanUtils.copyProperties(cliente, clienteBD, "id");
		this.clienteRepository.save(clienteBD);
		return clienteBD;
	}
	
}
