package com.ts.previsao.tempo.cidade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CidadeModelTest {

	private CidadeModel cidadeModel;
	private String fakeXml;

	@Before
	public void setUp() {
		this.cidadeModel = new CidadeModel();
		this.fakeXml = this.createFakeXml();
	}

	@Test
	public void testa_cidade_valida() throws Exception {
		String cidadeProcurada = this.cidadeModel.getXMLCidade("sao paulo");
		boolean contemTagCidade = cidadeProcurada.contains("<cidade>");
		boolean contemTagNome = cidadeProcurada.contains("<nome>");
		boolean contemTagUf = cidadeProcurada.contains("<uf>");
		boolean contemTagId = cidadeProcurada.contains("<id>");
		boolean resultado = contemTagCidade && contemTagNome && contemTagUf && contemTagId;
		assertTrue(resultado);
	}

	@Test
	public void testa_se_metodo_retira_metadado_de_xml() {
		String metadado = "<?xml version='1.0' encoding='ISO-8859-1'?>";
		assertEquals("", this.cidadeModel.removeMetaData(metadado));
	}
	
	@Test
	public void testa_se_transforma_xml_para_objeto() throws Exception {
		assertTrue((this.cidadeModel.xmlToObjectCidade(this.fakeXml) instanceof Cidade[]));
	}
	
	private String createFakeXml() {
		StringBuilder xmlBuilder = new StringBuilder();
		xmlBuilder.append("<cidades>");
		xmlBuilder.append("<cidade>");
		xmlBuilder.append("<nome>");
		xmlBuilder.append("São José");
		xmlBuilder.append("</nome>");
		xmlBuilder.append("<uf>");
		xmlBuilder.append("SC");
		xmlBuilder.append("</uf>");
		xmlBuilder.append("<id>");
		xmlBuilder.append("4909");
		xmlBuilder.append("</id>");
		xmlBuilder.append("</cidade>");		
		xmlBuilder.append("</cidades>");
		return xmlBuilder.toString();
	}

}
