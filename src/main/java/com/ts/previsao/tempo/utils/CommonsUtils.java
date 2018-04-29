package com.ts.previsao.tempo.utils;

import java.text.DateFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

public class CommonsUtils {

	public static final Pattern DIACRITICS = Pattern.compile("[\\p{InCombiningDiacriticalMarks}\\p{IsLm}\\p{IsSk}]+");

	public static String removeXMLMetaData(String xml) {
		return xml.replace("<?xml version='1.0' encoding='ISO-8859-1'?>", "");
	}

	public static String padronizaNomeDeCidade(String nomeCidade) {
		String cidade;
		cidade = removeAcentos(nomeCidade);
		cidade = cidade.replace("-", " ");
		return cidade.toLowerCase();
	}

	public static String removeAcentos(String string) {
		String semAcentos = Normalizer.normalize(string, Normalizer.Form.NFD);
		semAcentos = DIACRITICS.matcher(semAcentos).replaceAll("");
		return semAcentos;
	}

	public static String convertSeparadorData(String data) {
		return data.replace("-", "/");
	}

	public static String formataDataAtual() {
		final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		final Calendar cal = Calendar.getInstance();
		return df.format(cal.getTime());
	}
}