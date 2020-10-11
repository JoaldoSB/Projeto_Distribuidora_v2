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
import br.com.distribuidora.mode.Endereco;
import br.com.distribuidora.repository.EnderecoRepository;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@PostMapping
	public Endereco Salvar(@RequestBody Endereco endereco) {
		this.enderecoRepository.save(endereco);
		return endereco;
	}
	
	@GetMapping
	public List<Endereco> Listar(){
		return this.enderecoRepository.findAll();
	}
	
	@DeleteMapping("/{id}")
	public String remover(@PathVariable("id") Integer id) {
		this.enderecoRepository.deleteById(id);
		return "Endereço informado deletado com sucesso!";
	}
	
	@PutMapping("/{id}")
	public Endereco editar(@PathVariable("id") Integer id, @RequestBody Endereco endereco) {
		Endereco enderecoBD = this.enderecoRepository.findById(id).get();
		BeanUtils.copyProperties(endereco, enderecoBD, "id");
		this.enderecoRepository.save(enderecoBD);
		return enderecoBD;
	}

}
