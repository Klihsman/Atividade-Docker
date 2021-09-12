package br.edu.ifpb.dac.atividade.jsf.servico;

import java.util.List;

import br.edu.ifpb.dac.atividade.jsf.entidades.Banda;
import br.edu.ifpb.dac.atividade.jsf.repository.BandaPercistencia;

public class BandaServico {

	private BandaPercistencia bandas;
	
	public BandaServico() {
		bandas = new BandaPercistencia();
	}

	public void Add(Banda b) {
            if(b.getId()!=0){
		bandas.atualizar(b);
            }
            else bandas.addBanda(b);
	}
	
	public List<Banda> lista(){
		return bandas.todas();
	}

    public void removeBanda(int id) {
        bandas.removeBanda(id);
	}
	
	public List<Banda> localizarLocalDeOrigem(String origem){
		return	bandas.localizarLocalDeOrigem(origem);
	}
}