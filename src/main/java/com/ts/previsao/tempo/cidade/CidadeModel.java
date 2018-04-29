package com.ts.previsao.tempo.cidade;

import static com.ts.previsao.tempo.utils.CommonsUtils.padronizaNomeDeCidade;
import static com.ts.previsao.tempo.utils.CommonsUtils.removeXMLMetaData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.ts.previsao.tempo.utils.Acoes;
import com.ts.previsao.tempo.utils.CommonsUtils;
import com.ts.previsao.tempo.utils.UrlBuilder;

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

	public Cidade[] xmlToObjectCidade(String xml) throws Exception {
		StringReader sr = new StringReader(xml);
		JAXBContext context = JAXBContext.newInstance(Cidades.class);
		Unmarshaller un = context.createUnmarshaller();
		Cidades cidades = (Cidades) un.unmarshal(sr);
		return cidades.getCidade();
	}

	public Cidade selecionaCidade(Cidade[] cidades, String uf, String nomeCidade) {
		Cidade cidadeEncontrada = null;
		for (Cidade cidade : cidades) {
			if (uf.equalsIgnoreCase(cidade.getUf().toLowerCase())) {
				String nomeDaCidade = cidade.getNome();
				nomeDaCidade = padronizaNomeDeCidade(nomeDaCidade);
				if (nomeDaCidade.contains(nomeCidade)) {
					cidadeEncontrada = cidade;
				}
			}
		}
		return cidadeEncontrada;
	}

	public CidadeRepository filtraCidade(String uf, String cidade) throws Exception {
		String nome = CommonsUtils.padronizaNomeDeCidade(cidade);
		CidadeRepository cidadeRepository = getCidadeSalva(uf, nome);
		if (cidadeRepository == null) {
			CidadeDAO cidadeDao = new CidadeDAO();
			cidadeRepository = converteParaCidadeRepository(buscaCidade(uf, nome));
			cidadeDao.insertCidade(cidadeRepository);
		}
		return cidadeRepository;
	}

	public CidadeRepository getCidadeSalva(String uf, String nome) throws Exception {
		CidadeDAO cidadeDao = new CidadeDAO();
		CidadeRepository cidadeEncontrada = null;
		List<CidadeRepository> cidades = cidadeDao.selectAllCidade();
		for (CidadeRepository cidade : cidades) {
			if (cidade.getUf().equals(uf) && cidade.getNome().equals(nome)) {
				cidadeEncontrada = cidade;
			}
		}
		return cidadeEncontrada;
	}

	public Cidade buscaCidade(String uf, String nome) throws Exception {
		CidadeModel cidadeModel = new CidadeModel();
		Cidade[] cidades = cidadeModel.xmlToObjectCidade(cidadeModel.getXMLCidade(nome));
		return cidadeModel.selecionaCidade(cidades, uf, nome);
	}

	public CidadeRepository converteParaCidadeRepository(Cidade cidade) {
		CidadeRepository cidadeRepository = new CidadeRepository();
		cidadeRepository.setId(cidade.getId());
		cidadeRepository.setNome(cidade.getNome());
		cidadeRepository.setUf(cidade.getUf());
		cidadeRepository.setAtualizacao(CommonsUtils.formataDataAtual());
		return cidadeRepository;
	}
}
