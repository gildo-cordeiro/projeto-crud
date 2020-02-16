package com.gildocordeiro.esig.controller;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;

import com.gildocordeiro.esig.model.Cliente;
import com.gildocordeiro.esig.repositories.ClienteRepository;

@ManagedBean
@ViewScoped
public class ClienteController {
	
	@Autowired
	private ClienteRepository repository;
	
	private List<Cliente> clientes;
	
	private Cliente cliente = new Cliente();
	
	private boolean modeEdicao = false;
	
	@PostConstruct
	public void init() {
		clientes = repository.findAll();
	}
	
	public void salvar() {
		repository.save(cliente);
		 
		if(!modeEdicao)
			clientes.add(cliente);
		
		cliente = new Cliente();
		modeEdicao =  false;
	}
	
	public void cancelar() {
		cliente = new Cliente();
		modeEdicao = false;
	}
	
	public void excluir(Cliente c) {
		clientes.remove(c);
		repository.delete(c);
	}
	
	public void editar(Cliente c) {
		this.cliente = c;
		modeEdicao = true;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}


	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}


	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public boolean isModeEdicao() {
		return modeEdicao;
	}

	public void setModeEdicao(boolean modeEdicao) {
		this.modeEdicao = modeEdicao;
	}
	

}
