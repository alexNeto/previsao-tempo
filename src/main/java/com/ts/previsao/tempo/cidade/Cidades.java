package com.ts.previsao.tempo.cidade;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "cidades")
@XmlType(propOrder = { "cidade" })
public class Cidades {
	private Cidade[] cidade;

	public Cidade[] getCidade() {
		return cidade;
	}

	public void setCidade(Cidade[] cidade) {
		this.cidade = cidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(cidade);
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
		Cidades other = (Cidades) obj;
		if (!Arrays.equals(cidade, other.cidade))
			return false;
		return true;
	}

}