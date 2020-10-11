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
import br.com.distribuidora.mode.Produto;
import br.com.distribuidora.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@PostMapping
	public Produto Salvar(@RequestBody Produto produto) {
		this.produtoRepository.save(produto);
		return produto;
	}
	
	@GetMapping
	public List<Produto> Listar(){
		return this.produtoRepository.findAll();
	}
	
	@DeleteMapping("/{id}")
	public String Remover(@PathVariable("id") Integer id) {
		this.produtoRepository.deleteById(id);
		return "Produto informado deletado com sucesso!";
	}
	
	@PutMapping("/{id}")
	public Produto Editar(@PathVariable("id") Integer id, @RequestBody Produto produto) {
		Produto produtoBD = this.produtoRepository.findById(id).get();
		BeanUtils.copyProperties(produto, produtoBD, "id");
		this.produtoRepository.save(produtoBD);
		return produtoBD;
	}
	
}
