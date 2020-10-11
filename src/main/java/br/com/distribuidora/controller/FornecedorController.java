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
import br.com.distribuidora.mode.Fornecedor;
import br.com.distribuidora.repository.FornecedorRepository;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@PostMapping
	public Fornecedor Salvar(@RequestBody Fornecedor fornecedor) {
		this.fornecedorRepository.save(fornecedor);
		return fornecedor;
	}
	
	@GetMapping
	public List<Fornecedor> Listar(){
		return this.fornecedorRepository.findAll();
	}
	
	@DeleteMapping("/{id}")
	public String Remover(@PathVariable("id") Integer id) {
		this.fornecedorRepository.deleteById(id);
		return "Fornecedor informado deletado com sucesso!";
	}
	
	@PutMapping("/{id}")
	public Fornecedor Editar(@PathVariable("id") Integer id, @RequestBody Fornecedor fornecedor) {
		Fornecedor fornecedorBD = this.fornecedorRepository.findById(id).get();
		BeanUtils.copyProperties(fornecedor, fornecedorBD, "id");
		this.fornecedorRepository.save(fornecedorBD);
		return fornecedorBD;
	}
	
}
