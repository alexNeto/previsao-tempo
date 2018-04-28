package com.ts.previsao.tempo.previsao;

<<<<<<< HEAD
import com.ts.previsao.tempo.cidade.Cidade;
import com.ts.previsao.tempo.cidade.Cidades;
import com.ts.previsao.tempo.utils.Acoes;
import com.ts.previsao.tempo.utils.UrlBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class PrevisaoModel {

    private UrlBuilder urlBuilder;

    public PrevisaoModel() {
        this.urlBuilder = new UrlBuilder();
    }

    public String getXMLCidade(Integer codigoCidade) throws Exception {
        String charset = StandardCharsets.ISO_8859_1.name();
        String linha, resultado = "";
        String urlListaCidade = this.urlBuilder.make(Acoes.PREVISAO_7_DIAS, codigoCidade.toString());
//        String parametro = String.format(urlListaCidade, URLEncoder.encode(cidade, charset))
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
        /* a base do XML é uma marcação de nome cidades */
        JAXBContext context = JAXBContext.newInstance(Cidades.class);
        Unmarshaller un = context.createUnmarshaller();
        Cidades cidades = (Cidades) un.unmarshal(sr);
        return cidades.getCidade();
    }

    public String removeMetaData(String xml) {
        return xml.replace("<?xml version='1.0' encoding='ISO-8859-1'?>", "");
    }
=======
public class PrevisaoModel {
>>>>>>> c3429390b812758d8f4e4bda6ded032fc6d74498
}
