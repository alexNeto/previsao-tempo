package com.ts.previsao.tempo.cidade;

import com.ts.previsao.tempo.utils.Acoes;
import com.ts.previsao.tempo.utils.UrlBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class CidadeModel {

    private UrlBuilder urlBuilder;

    public CidadeModel() {
        this.urlBuilder = new UrlBuilder();
    }

    public String getXMLCidade(String cidade) throws Exception {
        String linha, resultado = "";
        String urlListaCidade = this.urlBuilder.make(Acoes.PROCURAR_CIDADE, cidade);
        URL url = new URL(urlListaCidade);
        URLConnection conexao = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conexao.getInputStream(), Charset.forName("ISO-8859-1")));
        while ((linha = reader.readLine()) != null) {
            resultado += linha;
        }
        return this.removeMetaData(resultado);
    }

    public Cidade[] xmlToObjectCidade(String xml) throws Exception {
        StringReader sr = new StringReader(xml);
        JAXBContext context = JAXBContext.newInstance(Cidades.class);
        Unmarshaller un = context.createUnmarshaller();
        Cidades cidades = (Cidades) un.unmarshal(sr);
        return cidades.getCidade();
    }

    public String removeMetaData(String xml) {
        return xml.replace("<?xml version='1.0' encoding='ISO-8859-1'?>", "");
    }
}
