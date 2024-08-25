package com.esig.finanweb.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.esig.finanweb.model.CargoVencimento;
import com.esig.finanweb.model.Pessoa;
import com.esig.finanweb.model.PessoaSalarioConsolidado;
import com.esig.finanweb.model.Vencimento;

public class ConsolidacaoService {

    private SessionFactory sessionFactory;
    private List<PessoaSalarioConsolidado> resultados;

    public ConsolidacaoService() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void deletarDadosExistentes() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM PessoaSalarioConsolidado").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public List<PessoaSalarioConsolidado> consolidarSalarios() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        // Deletar dados existentes
        deletarDadosExistentes();

        List<Pessoa> pessoas = session.createQuery("SELECT p FROM Pessoa p", Pessoa.class).getResultList();
        resultados = new ArrayList<>();

        for (Pessoa pessoa : pessoas) {
            BigDecimal salario = BigDecimal.ZERO;
            for (CargoVencimento cv : pessoa.getCargo().getCargoVencimentos()) {
                Vencimento vencimento = cv.getVencimento();
                if ("CREDITO".equals(vencimento.getTipo())) {
                    salario = salario.add(vencimento.getValor());
                } else if ("DEBITO".equals(vencimento.getTipo())) {
                    salario = salario.subtract(vencimento.getValor());
                }
            }

            PessoaSalarioConsolidado psc = new PessoaSalarioConsolidado();
            psc.setPessoaId(pessoa.getId());
            psc.setNomePessoa(pessoa.getNome());
            psc.setNomeCargo(pessoa.getCargo().getNome());
            psc.setSalario(salario);
            
            session.save(psc);
            resultados.add(psc);
        }

        session.getTransaction().commit();
        session.close();
        return resultados;
    }
}
