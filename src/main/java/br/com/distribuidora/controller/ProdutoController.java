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
import br.com.distribuidora.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@PostMapping
	public Produto Salvar(@RequestBody Produto produto) {
		this.produtoService.salvar(produto);
		return produto;
	}
	
	@GetMapping
	public List<Produto> Listar(){
		return this.produtoService.listarProduto();
	}
	
	@DeleteMapping("/{id}")
	public String Remover(@PathVariable("id") Integer id) {
		this.produtoService.removerProduto(this.produtoService.buscarPorId(id));
		return "Produto informado deletado com sucesso!";
	}
	

 	@PutMapping("/{id}")
	public Produto buscarProdutoId(@PathVariable("id") Integer id, @RequestBody Produto produto) {
		Produto produtoBD = this.produtoService.buscarPorId(id);
		BeanUtils.copyProperties(produto, produtoBD, "id");
		this.produtoService.salvar(produtoBD);
		return produtoBD;
	}


}
