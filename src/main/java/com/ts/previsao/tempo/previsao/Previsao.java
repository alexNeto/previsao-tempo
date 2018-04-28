package com.ts.previsao.tempo.previsao;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "previsao")
@XmlType(propOrder = { "dia", "tempo", "maxima", "minima", "iuv" })
public class Previsao {

	@XmlElement(name = "dia")
	private String dia;
	@XmlElement(name = "tempo")
	private String tempo;
	@XmlElement(name = "maxima")
	private Double maxima;
	@XmlElement(name = "minima")
	private Double minima;
	@XmlElement(name = "iuv")
	private Double iuv;

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getTempo() {
		return tempo;
	}

	public void setTempo(String tempo) {
		this.tempo = tempo;
	}

	public Double getMaxima() {
		return maxima;
	}

	public void setMaxima(Double maxima) {
		this.maxima = maxima;
	}

	public Double getMinima() {
		return minima;
	}

	public void setMinima(Double minima) {
		this.minima = minima;
	}

	public Double getIuv() {
		return iuv;
	}

	public void setIuv(Double iuv) {
		this.iuv = iuv;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dia == null) ? 0 : dia.hashCode());
		result = prime * result + ((iuv == null) ? 0 : iuv.hashCode());
		result = prime * result + ((maxima == null) ? 0 : maxima.hashCode());
		result = prime * result + ((minima == null) ? 0 : minima.hashCode());
		result = prime * result + ((tempo == null) ? 0 : tempo.hashCode());
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
		Previsao other = (Previsao) obj;
		if (dia == null) {
			if (other.dia != null)
				return false;
		} else if (!dia.equals(other.dia))
			return false;
		if (iuv == null) {
			if (other.iuv != null)
				return false;
		} else if (!iuv.equals(other.iuv))
			return false;
		if (maxima == null) {
			if (other.maxima != null)
				return false;
		} else if (!maxima.equals(other.maxima))
			return false;
		if (minima == null) {
			if (other.minima != null)
				return false;
		} else if (!minima.equals(other.minima))
			return false;
		if (tempo == null) {
			if (other.tempo != null)
				return false;
		} else if (!tempo.equals(other.tempo))
			return false;
		return true;
	}
}
