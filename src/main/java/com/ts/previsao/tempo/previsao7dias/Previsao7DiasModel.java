package com.ts.previsao.tempo.previsao7dias;

import java.util.Calendar;
import java.util.List;

import com.google.gson.Gson;
import com.ts.previsao.tempo.cidade.Cidade;
import com.ts.previsao.tempo.cidade.CidadeDAO;
import com.ts.previsao.tempo.cidade.CidadeModel;
import com.ts.previsao.tempo.cidade.CidadeRepository;
import com.ts.previsao.tempo.previsao.PrevisaoModel;
import com.ts.previsao.tempo.previsao.Previsoes;
import com.ts.previsao.tempo.utils.CommonsUtils;

public class Previsao7DiasModel {

	private Calendar dataHoje = Calendar.getInstance();;
	private String dataAtualFormatada;

	public Previsao7DiasModel() {
		this.dataAtualFormatada = this.formataDataAtual();
	}

	public String getPrevisao(String uf, String cidade) throws Exception {
		CidadeRepository cidadeEncontrada = this.filtraCidade(uf, cidade);
		Previsoes previsoes = this.getPrevisao(cidadeEncontrada.getId());
		return new Gson().toJson(previsoes);
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

	public CidadeRepository converteParaCidadeRepository(Cidade cidade) {
		CidadeRepository cidadeRepository = new CidadeRepository();
		cidadeRepository.setId(cidade.getId());
		cidadeRepository.setNome(cidade.getNome());
		cidadeRepository.setUf(cidade.getUf());
		cidadeRepository.setAtualizacao(this.dataAtualFormatada);
		return cidadeRepository;
	}

	public Cidade buscaCidade(String uf, String nome) throws Exception {
		CidadeModel cidadeModel = new CidadeModel();
		Cidade[] cidades = cidadeModel.xmlToObjectCidade(cidadeModel.getXMLCidade(nome));
		return cidadeModel.selecionaCidade(cidades, uf, nome);
	}

	public Previsoes getPrevisao(Integer id) throws Exception {
		PrevisaoModel previsaoModel = new PrevisaoModel();
		return previsaoModel.xmlToObjectPrevisao(previsaoModel.getXMLPrevisao(id));
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

	public String formataDataAtual() {
		StringBuilder dataBuilder = new StringBuilder();
		dataBuilder.append(dataHoje.DAY_OF_MONTH).append("/");
		dataBuilder.append(dataHoje.MONTH).append("/");
		dataBuilder.append(dataHoje.YEAR);
		return dataBuilder.toString();
	}

}
