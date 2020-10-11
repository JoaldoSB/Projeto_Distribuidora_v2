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
import br.com.distribuidora.mode.Usuario;
import br.com.distribuidora.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping
	public Usuario Salvar(@RequestBody Usuario usuario) {
		this.usuarioRepository.save(usuario);
		return usuario;
	}
	
	@GetMapping
	public List<Usuario> Listar(){
		return this.usuarioRepository.findAll();
	}
	
	@DeleteMapping("/{id}")
	public String Remover(@PathVariable("id") Integer id) {
		this.usuarioRepository.deleteById(id);
		return "Usuario informado deletado com sucesso!";
	}
	
	@PutMapping("/{id}")
	public Usuario Editar(@PathVariable("id") Integer id, @RequestBody Usuario usuario) {
		Usuario usuarioBD = this.usuarioRepository.findById(id).get();
		BeanUtils.copyProperties(usuario, usuarioBD, "id");
		this.usuarioRepository.save(usuarioBD);
		return usuarioBD;
	}

}
