package com.ts.previsao.tempo.previsao;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ts.previsao.tempo.cidade.Cidade;

@XmlRootElement(name = "cidade")
@XmlType(propOrder = { "nome. uf, atualizacao, previsao" })
public class Previsoes {

	@XmlElement(name = "cidade")
	private Cidade cidade;
	@XmlElement(name = "previsao")
	private Previsao[] previsao;

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Previsao[] getPrevisao() {
		return previsao;
	}

	public void setPrevisao(Previsao[] previsao) {
		this.previsao = previsao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + Arrays.hashCode(previsao);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Previsoes other = (Previsoes) obj;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (!Arrays.equals(previsao, other.previsao))
			return false;
		return true;
	}

}
