package br.edu.ifpb.dac.atividade.jsf.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Banda implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private int id;
	private String localDeOrigem;
	private String nomeFantasia;
	private List<Integrante> integrantes;
	
	public Banda() {
            this.id =0;
		this.integrantes = new ArrayList<>();
	}

	public Banda(String localDeOrigem,String nomeFantasia) {
		super();
		this.localDeOrigem = localDeOrigem;
		this.nomeFantasia = nomeFantasia;
	}
	
	public void AddIntegrante(Integrante i) {
		this.integrantes.add(i);
	}
	public void RemoverIntegrante(Integrante i) {
		this.integrantes.remove(i);
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocalDeOrigem() {
		return localDeOrigem;
	}

	public void setLocalDeOrigem(String localDeOrigem) {
		this.localDeOrigem = localDeOrigem;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public List<Integrante> getIntegrantes() {
		return Collections.unmodifiableList(this.integrantes);
	}

	public void setIntegrantes(List<Integrante> integrantes) {
		this.integrantes = integrantes;
	}

    @Override
    public String toString() {
        return "Banda{" + "id=" + id + ", localDeOrigem=" + localDeOrigem + ", nomeFantasia=" + nomeFantasia + ", integrantes=" + integrantes + '}';
    }
         

}