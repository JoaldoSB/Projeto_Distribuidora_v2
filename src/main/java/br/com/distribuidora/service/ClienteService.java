package br.com.distribuidora.service;
import java.util.List;
import br.com.distribuidora.mode.Cliente;

public interface ClienteService {
	
	Cliente Salvar(Cliente cliente);
	List<Cliente> listaClientes();
	void remover(Cliente cliente);
	Cliente buscarPorId(int idCliente);

}
