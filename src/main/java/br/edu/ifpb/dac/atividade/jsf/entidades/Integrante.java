package br.edu.ifpb.dac.atividade.jsf.entidades;

import java.time.LocalDate;

public class Integrante {
	private int id;
	private String nome;
	private LocalDate dataDeNascimento;
	private CPF cpf = new CPF();
	
	public Integrante() {
		
	}
	
	private Integrante(int id, String nome, LocalDate dataDeNascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataDeNascimento = dataDeNascimento;
	}
	
	public Integrante(String nome, LocalDate dataDeNascimento) {
		super();
		this.nome = nome;
		this.dataDeNascimento = dataDeNascimento;
	}

	public static Integrante off(int id, String nome, LocalDate dataDeNascimento) {
		return new Integrante(id,nome,dataDeNascimento);
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}


	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public CPF getCpf() {
		return cpf;
	}

	public void setCpf(CPF cpf) {
		this.cpf = cpf;
	}

    @Override
    public String toString() {
        return "Integrante{" + "id=" + id + ", nome=" + nome + ", dataDeNascimento=" + dataDeNascimento + ", cpf=" + cpf + '}';
    }
                
	

}
