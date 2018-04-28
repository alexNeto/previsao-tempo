package com.ts.previsao.tempo.home;

import com.ts.previsao.tempo.cidade.CidadeModel;

public class HomeModel {

	public String info() throws Exception {
		CidadeModel cidade = new CidadeModel();
		String cidades = cidade.getXMLCidade("Sao").toString();
		System.out.println(cidades);
		cidades = cidade.xmlToObjectCidade(cidades).toString();
		return "200";
	}
}
