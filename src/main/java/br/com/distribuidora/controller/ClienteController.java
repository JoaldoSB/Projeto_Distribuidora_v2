package br.com.distribuidora.controller;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.distribuidora.mode.Cliente;
import br.com.distribuidora.service.ClienteService;

@RestController
@RequestMapping("/clientes/api/")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@PostMapping("/v1")
	public Cliente salvar1(@RequestBody Cliente cliente) {
		return this.clienteService.Salvar(cliente);
	}	
	
	@PostMapping("/v2")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Cliente> salvar2(@RequestBody Cliente cliente) {
		return ResponseEntity.ok().body(this.clienteService.Salvar(cliente));
	}	

	
	@GetMapping("/v1")
	public List<Cliente> listar1(){
		return this.clienteService.listaClientes();
	}
	
	@GetMapping("/v2")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Cliente>> listar2(){
		return ResponseEntity.ok().body(this.clienteService.listaClientes());
	}

	@DeleteMapping("/v1/{id}")
	public String remover1(@PathVariable("id") Integer id) {
		this.clienteService.remover(this.clienteService.buscarPorId(id));
		return "Cliente informado deletado com sucesso!";
	}
	
	@DeleteMapping("/v2/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public String remover2(@PathVariable("id") Integer id) {
		this.clienteService.remover(this.clienteService.buscarPorId(id));
		return "Cliente informado deletado com sucesso!";
	}
	
	@PutMapping("/v1/{id}")
	public Cliente buscarClienteId1(@PathVariable("id") Integer id, @RequestBody Cliente cliente) {
		Cliente clienteBD = this.clienteService.buscarPorId(id);
		BeanUtils.copyProperties(cliente, clienteBD, "id");
		this.clienteService.Salvar(clienteBD);
		return clienteBD;	
	}
	
	@PutMapping("/v2/{id}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Cliente> buscarClienteId2(@PathVariable("id") Integer id, @RequestBody Cliente cliente) {
		Cliente clienteBD = this.clienteService.buscarPorId(id);
		BeanUtils.copyProperties(cliente, clienteBD, "id");
		return ResponseEntity.ok().body(this.clienteService.Salvar(clienteBD));
		//return clienteBD;	
	}
	
}
