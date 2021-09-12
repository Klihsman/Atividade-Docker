/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.atividade.jsf.controller;

import br.edu.ifpb.dac.atividade.jsf.entidades.CPF;
import br.edu.ifpb.dac.atividade.jsf.entidades.Integrante;
import br.edu.ifpb.dac.atividade.jsf.servico.IntegranteServico;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class ControlerIntegrante implements Serializable {

    private Integrante integrante;
    private IntegranteServico is;
    private String datanasc;
    private String CPF;
    private List<Integrante> listIntegrante;

    public ControlerIntegrante() {
        integrante = new Integrante();
        listIntegrante = new ArrayList<>();
        is = new IntegranteServico();
    }

    public String buscarCpf() {
        listIntegrante = is.localizarintegranteCPF(CPF);
        if (listIntegrante.size() == 0) {
            listIntegrante = new ArrayList<Integrante>();
            return null;
        }
        return null;
    }

    public String redirect() {

        return "integrante/edit";
    }

    public String salvar() {
        integrante.setCpf(new CPF(CPF));
        integrante.setDataDeNascimento(LocalDate.parse(datanasc));
        is.add(integrante);
        return "list";
    }

    public Integrante getIntegrante() {
        return integrante;
    }

    public void setIntegrante(Integrante integrante) {
        this.integrante = integrante;
    }

    public String getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(String datanasc) {
        this.datanasc = datanasc;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public List<Integrante> getIntegrantes() {
        return is.todas();
    }

    public String remove(int id) {
        is.removeIntegrante(id);
        return null;
    }

    public String edite(Integrante integrante) {
        this.integrante = integrante;
        this.datanasc = integrante.getDataDeNascimento().toString();
        this.CPF = integrante.getCpf().formatado();
        return "edit";
    }

    public List<Integrante> getListIntegrante() {
        return listIntegrante;
    }

    public void setListIntegrante(List<Integrante> listIntegrante) {
        this.listIntegrante = listIntegrante;
    }
}
