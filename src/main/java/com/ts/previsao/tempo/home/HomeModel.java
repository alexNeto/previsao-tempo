package com.ts.previsao.tempo.home;

import com.google.gson.Gson;

class HomeModel {

    String info() throws Exception {
        return new Gson().toJson(new Readme());

    }
}

class Readme {
    private Instrucoes comoUsar;

    Readme() {
        this.comoUsar = new Instrucoes();
    }

    public Instrucoes getComoUsar() {
        return comoUsar;
    }
}

class Instrucoes {
    private String acesse;
    private String exemplo;
    private String observacao;
    private String nomeDaCidade;
    private String exemploNomeDaCidade;

    Instrucoes() {
        this.acesse = "http://localhost:4567/uf/<sigla do estado>/cidade/<nome da cidade>";
        this.exemplo = "https://previsao-tempo.herokuapp.com/uf/sp/cidade/sao-jose-dos-campos";
        this.observacao = "sigla do estado em caixa baixa";
        this.nomeDaCidade = "nomes das cidades em caixa baixa, sem acentos ou tils, usando hifem para espaços";
        this.exemploNomeDaCidade = "sao-jose-dos-campos";
    }

    public String getAcesse() {
        return acesse;
    }

    public String getExemplo() {
        return exemplo;
    }

    public String getObservacao() {
        return observacao;
    }

    public String getNomeDaCidade() {
        return nomeDaCidade;
    }

    public String getExemploNomeDaCidade() {
        return exemploNomeDaCidade;
    }
}