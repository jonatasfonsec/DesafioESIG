package com.esig.finanweb.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.esig.finanweb.model.PessoaSalarioConsolidado;
import com.esig.finanweb.service.ConsolidacaoService;

@ManagedBean
@ViewScoped
public class ConsolidacaoBean {

    private ConsolidacaoService consolidacaoService = new ConsolidacaoService();
    private List<PessoaSalarioConsolidado> resultados;

    public void consolidarSalarios() {
        resultados = consolidacaoService.consolidarSalarios();
    }

    public List<PessoaSalarioConsolidado> getResultados() {
        return resultados;
    }
}
