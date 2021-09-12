package br.edu.ifpb.dac.atividade.jsf.conversor;

import br.edu.ifpb.dac.atividade.jsf.entidades.Integrante;
import br.edu.ifpb.dac.atividade.jsf.servico.IntegranteServico;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "converter.Integrante")
public class IntegranteConversor implements Converter {

    private IntegranteServico service = new IntegranteServico();

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
         
        if(value == null){
            return null;
        }
        System.out.println("passando o valor do integrante "+value);
        
        Integrante integrante = this.service.buscarPorId(Integer.parseInt(value));
        System.err.println("o int co coverte "+integrante);
       
        return integrante;
         

    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if(value == null){
            return null;

        }

        Integrante integrante = (Integrante) value;
        System.err.println("o int co coverte 9 "+integrante);
        return String.valueOf(integrante.getId());

    }
}
