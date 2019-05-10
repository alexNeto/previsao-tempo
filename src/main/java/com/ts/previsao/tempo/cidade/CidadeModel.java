package com.ts.previsao.tempo.cidade;

import com.ts.previsao.tempo.utils.Acoes;
import com.ts.previsao.tempo.utils.CommonsUtils;
import com.ts.previsao.tempo.utils.UrlBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import static com.ts.previsao.tempo.utils.CommonsUtils.*;

public class CidadeModel {

    private UrlBuilder urlBuilder;

    public CidadeModel() {
        this.urlBuilder = new UrlBuilder();
    }

    public String getXMLCidade(String cidade) throws Exception {
        String partialResponse;
        BufferedReader reader = null;
        URL url = new URL(this.urlBuilder.make(Acoes.PROCURAR_CIDADE, cidade));
        reader = new BufferedReader(new InputStreamReader(url.openStream(), Charset.forName("ISO-8859-1")));
        partialResponse = reader.readLine();
        reader.close();
        return removeXMLMetaData(partialResponse);
    }

    Cidade[] xmlToObjectCidade(String xml) throws Exception {
        StringReader sr = new StringReader(xml);
        JAXBContext context = JAXBContext.newInstance(Cidades.class);
        Unmarshaller un = context.createUnmarshaller();
        Cidades cidades = (Cidades) un.unmarshal(sr);
        return cidades.getCidade();
    }

    private Cidade selecionaCidade(Cidade[] cidades, String uf, String nomeCidade) {
        Cidade cidadeEncontrada = null;
        for (Cidade cidade : cidades) {
            cidadeEncontrada = procuraCidade(cidade, uf, nomeCidade);
        }
        return cidadeEncontrada;
    }

    private Cidade procuraCidade(Cidade cidade, String uf, String nomeCidade) {
        if (uf.equalsIgnoreCase(cidade.getUf()))
            if (padronizaNomeDeCidade(cidade.getNome()).contains(nomeCidade))
                return cidade;
        return new Cidade();
    }


    public CidadeRepository filtraCidade(String uf, String cidade) throws Exception {
        String nome = padronizaNomeDeCidade(cidade);
        CidadeRepository cidadeRepository = getCidadeSalva(uf, nome);
        CidadeDAO cidadeDao = new CidadeDAO();
        if (cidadeRepository == null) {
            cidadeRepository = converteParaCidadeRepository(buscaCidade(uf, nome));
            cidadeRepository.setAtualizacao(formataDataAtual());
            cidadeDao.insertCidade(cidadeRepository);
        } else {
            cidadeRepository.setAtualizacao(formataDataAtual());
            cidadeDao.atualizaCidade(cidadeRepository);
        }
        return cidadeRepository;
    }

    CidadeRepository getCidadeSalva(String uf, String nome) throws Exception {
        CidadeDAO cidadeDao = new CidadeDAO();
        CidadeRepository cidadeEncontrada = null;
        List<CidadeRepository> cidades = cidadeDao.selectAllCidade();
        for (CidadeRepository cidade : cidades) {
            if (cidade.getUf().equalsIgnoreCase(uf) && padronizaNomeDeCidade(cidade.getNome()).equalsIgnoreCase(nome)) {
                cidadeEncontrada = cidade;
            }
        }
        return cidadeEncontrada;
    }

    private Cidade buscaCidade(String uf, String nome) throws Exception {
        CidadeModel cidadeModel = new CidadeModel();
        Cidade[] cidades = cidadeModel.xmlToObjectCidade(cidadeModel.getXMLCidade(nome));
        return cidadeModel.selecionaCidade(cidades, uf, nome);
    }

    private CidadeRepository converteParaCidadeRepository(Cidade cidade) {
        CidadeRepository cidadeRepository = new CidadeRepository();
        cidadeRepository.setId(cidade.getId());
        cidadeRepository.setNome(cidade.getNome());
        cidadeRepository.setUf(cidade.getUf());
        cidadeRepository.setAtualizacao(CommonsUtils.formataDataAtual());
        return cidadeRepository;
    }
}
