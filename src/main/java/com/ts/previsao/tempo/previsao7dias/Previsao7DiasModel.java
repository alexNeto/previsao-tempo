package com.ts.previsao.tempo.previsao7dias;

import com.google.gson.Gson;
import com.ts.previsao.tempo.cidade.CidadeModel;
import com.ts.previsao.tempo.cidade.CidadeRepository;
import com.ts.previsao.tempo.previsao.PrevisaoModel;
import com.ts.previsao.tempo.previsao.Previsoes;

public class Previsao7DiasModel {

	public String getPrevisao(String uf, String cidade) throws Exception {
		CidadeModel cidadeModel = new CidadeModel();
		CidadeRepository cidadeEncontrada = cidadeModel.filtraCidade(uf, cidade);
		Previsoes previsoes = this.getPrevisao(cidadeEncontrada.getId());
		return new Gson().toJson(previsoes);
	}

	public Previsoes getPrevisao(Integer id) throws Exception {
		PrevisaoModel previsaoModel = new PrevisaoModel();
		return previsaoModel.xmlToObjectPrevisao(previsaoModel.getXMLPrevisao(id));
	}

}
