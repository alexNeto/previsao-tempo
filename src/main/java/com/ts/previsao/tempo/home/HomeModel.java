package com.ts.previsao.tempo.home;

import com.ts.previsao.tempo.cidade.Cidade;
import com.ts.previsao.tempo.cidade.CidadeModel;

public class HomeModel {

    public String info() throws Exception {
        CidadeModel cidade = new CidadeModel();
        Cidade cidades = cidade.xmlToObjectCidade(cidade.getXMLCidade("Sao"))[0];

        return cidades.getNome();
    }
}
