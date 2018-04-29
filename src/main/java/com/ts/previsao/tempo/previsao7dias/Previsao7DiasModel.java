package com.ts.previsao.tempo.previsao7dias;

import java.util.List;

import com.google.gson.Gson;
import com.ts.previsao.tempo.cidade.Cidade;
import com.ts.previsao.tempo.cidade.CidadeDAO;
import com.ts.previsao.tempo.cidade.CidadeModel;
import com.ts.previsao.tempo.cidade.CidadeRepository;
import com.ts.previsao.tempo.previsao.Previsao;
import com.ts.previsao.tempo.previsao.PrevisaoModel;
import com.ts.previsao.tempo.utils.CommonsUtils;

public class Previsao7DiasModel {

	public String getPrevisao(String uf, String cidade) throws Exception {
		Cidade cidadeEncontrada = this.filtraCidade(uf, cidade);
		Previsao[] previsoes = this.getPrevisao(cidadeEncontrada.getId());
		return new Gson().toJson(previsoes);
	}

	public Cidade filtraCidade(String uf, String cidade) throws Exception {
		cidade = CommonsUtils.padronizaNomeDeCidade(cidade);
		CidadeModel cidadeModel = new CidadeModel();
		Cidade[] cidades = cidadeModel.xmlToObjectCidade(cidadeModel.getXMLCidade(cidade));
		return cidadeModel.selecionaCidade(cidades, uf, cidade);
	}

	public Previsao[] getPrevisao(Integer id) throws Exception {
		PrevisaoModel previsaoModel = new PrevisaoModel();
		return previsaoModel.xmlToObjectPrevisao(previsaoModel.getXMLPrevisao(id));
	}

	public CidadeRepository verificaSeCidadeEstaSalva(String uf, String nome) throws Exception {
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

}
