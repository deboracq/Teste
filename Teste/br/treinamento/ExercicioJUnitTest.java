package br.treinamento;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ExercicioJUnitTest {
	private ExercicioJUnit entidade;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void setUpBefore() {
		entidade = new ExercicioJUnit();
	}

	@After
	public void tearDownAfter() {
		entidade = null;
	}

	@Test
	public void testVerificarEntidadeValida() {
		testValidarCamposObrigatorios_nome();
		testValidarCamposObrigatorios_nrDoc();
		testValidarCamposObrigatorios_tipoDoc();
		testValidarCamposObrigatorios_dtInicial();
		testValidarCamposObrigatorios_dtFinal();
		testValidarRegras_nome();
	}

	@Test
	public void testValidarCamposObrigatorios_nome() {
		Entidade entidadeTest;
		entidadeTest = new Entidade();
		Calendar dataTest = Calendar.getInstance();

		entidadeTest.setNumeroDocumento((long) 123456789);
		entidadeTest.setTipoDocumento(1);
		dataTest.set(2014, 2, 11);
		entidadeTest.setDataInicial(dataTest.getTime());
		dataTest.set(2014, 2, 22);
		entidadeTest.setDataFinal(dataTest.getTime());

		try {
			assertFalse(entidade.verificarEntidadeValida(entidadeTest));
			fail("Nao deveria chegar nesta linha");
		} catch (Exception e) {
			assertEquals("O nome e obrigatorio", e.getMessage());
		}
	}

	@Test
	public void testValidarCamposObrigatorios_nrDoc() {
		Entidade entidadeTest;
		entidadeTest = new Entidade();
		Calendar dataTest = Calendar.getInstance();

		entidadeTest.setNome("Nome_do_Usuario");
		entidadeTest.setTipoDocumento(1);
		dataTest.set(2014, 2, 11);
		entidadeTest.setDataInicial(dataTest.getTime());
		dataTest.set(2014, 2, 22);
		entidadeTest.setDataFinal(dataTest.getTime());

		try {
			assertFalse(entidade.verificarEntidadeValida(entidadeTest));
			fail("Nao deveria chegar nesta linha");
		} catch (Exception e) {
			assertEquals("O numero do documento e obrigatorio", e.getMessage());
		}
	}

	@Test
	public void testValidarCamposObrigatorios_tipoDoc() {
		Entidade entidadeTest;
		entidadeTest = new Entidade();
		Calendar dataTest = Calendar.getInstance();

		entidadeTest.setNome("Nome_do_Usuario");
		entidadeTest.setNumeroDocumento((long) 123456789);
		dataTest.set(2014, 2, 11);
		entidadeTest.setDataInicial(dataTest.getTime());
		dataTest.set(2014, 2, 22);
		entidadeTest.setDataFinal(dataTest.getTime());

		try {
			assertFalse(entidade.verificarEntidadeValida(entidadeTest));
			fail("Nao deveria chegar nesta linha");
		} catch (Exception e) {
			assertEquals("O tipo do documento e obrigatorio", e.getMessage());
		}
	}

	@Test
	public void testValidarCamposObrigatorios_dtInicial() {
		Entidade entidadeTest;
		entidadeTest = new Entidade();
		Calendar dataTest = Calendar.getInstance();

		entidadeTest.setNome("Nome_do_Usuario");
		entidadeTest.setNumeroDocumento((long) 123456789);
		entidadeTest.setTipoDocumento(1);
		dataTest.set(2014, 2, 22);
		entidadeTest.setDataFinal(new Date(dataTest.getTimeInMillis()));

		try {
			assertFalse(entidade.verificarEntidadeValida(entidadeTest));
			fail("Nao deveria chegar nesta linha");
		} catch (Error e) {
			e.printStackTrace();
		} catch (Exception e) {
			assertEquals("O periodo deve ser informado por completo",
					e.getMessage());

		}
	}

	@Test
	public void testValidarCamposObrigatorios_dtFinal() {
		Entidade entidadeTest;
		entidadeTest = new Entidade();
		Calendar dataTest = Calendar.getInstance();

		entidadeTest.setNome("Nome_do_Usuario");
		entidadeTest.setNumeroDocumento((long) 123456789);
		entidadeTest.setTipoDocumento(1);
		dataTest.set(2014, 2, 11);
		entidadeTest.setDataInicial(new Date(dataTest.getTimeInMillis()));

		try {
			assertFalse(entidade.verificarEntidadeValida(entidadeTest));
			fail("Nao deveria chegar nesta linha");
		} catch (Exception e) {
			assertEquals("O periodo deve ser informado por completo",
					e.getMessage());
		}

	}

	@Test
	public void testValidarRegras_nome() {
		Entidade entidadeTest;
		entidadeTest = new Entidade();
		Calendar dataTest = Calendar.getInstance();

		entidadeTest.setNome("Nome");
		entidadeTest.setNumeroDocumento((long) 123456789);
		entidadeTest.setTipoDocumento(1);
		dataTest.set(2014, 2, 11);
		entidadeTest.setDataInicial(new Date(dataTest.getTimeInMillis()));
		dataTest.set(2014, 2, 22);
		entidadeTest.setDataFinal(new Date(dataTest.getTimeInMillis()));

		try {
			assertFalse(entidade.verificarEntidadeValida(entidadeTest));
			fail("Nao deveria chegar nesta linha");
		} catch (Exception e) {
			assertEquals("O nome nao pode ter menos que 5 caracteres",
					e.getMessage());
		}
	}

	@Test
	public void testValidarRegras_nomeGrande() {
		Entidade entidadeTest;
		entidadeTest = new Entidade();
		Calendar dataTest = Calendar.getInstance();

		entidadeTest
				.setNome("Nome muito grande com mais de trinta caracteres para testar a funcionalidade");
		entidadeTest.setNumeroDocumento((long) 123456789);
		entidadeTest.setTipoDocumento(1);
		dataTest.set(2014, 2, 11);
		entidadeTest.setDataInicial(new Date(dataTest.getTimeInMillis()));
		dataTest.set(2014, 2, 22);
		entidadeTest.setDataFinal(new Date(dataTest.getTimeInMillis()));

		try {
			assertFalse(entidade.verificarEntidadeValida(entidadeTest));
			fail("Nao deveria chegar nesta linha");
		} catch (Exception e) {
			assertEquals("O nome nao pode ter mais que 30 caracteres",
					e.getMessage());
		}
	}


	@Test
	public void testValidarRegras_dtInicialMenorAtual() {
		Entidade entidadeTest;
		entidadeTest = new Entidade();
		Calendar dataTest = Calendar.getInstance();

		entidadeTest.setNome("Nome Sobrenome");
		entidadeTest.setNumeroDocumento((long) 123456789);
		entidadeTest.setTipoDocumento(1);
		dataTest.set(2013, 2, 11);
		entidadeTest.setDataInicial(new Date(dataTest.getTimeInMillis()));
		dataTest.set(2014, 2, 22);
		entidadeTest.setDataFinal(new Date(dataTest.getTimeInMillis()));
		

		try {
			assertFalse(entidade.verificarEntidadeValida(entidadeTest));
			fail("Nao deveria chegar nesta linha");
		} catch (Exception e) {
			assertEquals("A data inicial nao pode ser menor que a data atual",
					e.getMessage());
		}
	}

	@Test
	public void testValidarRegras_dtFinalMenorInicial() {
		Entidade entidadeTest;
		entidadeTest = new Entidade();
		Calendar dataTest = Calendar.getInstance();

		entidadeTest.setNome("Nome Sobrenome");
		entidadeTest.setNumeroDocumento((long) 123456789);
		entidadeTest.setTipoDocumento(1);
		dataTest.set(2014, 2, 11);
		entidadeTest.setDataInicial(new Date(dataTest.getTimeInMillis()));
		dataTest.set(2014, 2, 1);
		entidadeTest.setDataFinal(new Date(dataTest.getTimeInMillis()));

		try {
			assertFalse(entidade.verificarEntidadeValida(entidadeTest));
			fail("Nao deveria chegar nesta linha");
		} catch (Exception e) {
			assertEquals("A data final nao pode ser menor que a data inicial",
					e.getMessage());
		}
	}

	@Test
	public void testValidarRegras_tpDocErrado() {
		Entidade entidadeTest;
		entidadeTest = new Entidade();
		Calendar dataTest = Calendar.getInstance();

		entidadeTest.setNome("Nome Sobrenome");
		entidadeTest.setNumeroDocumento((long) 123456789);
		entidadeTest.setTipoDocumento(3);
		dataTest.set(2014, 2, 11);
		entidadeTest.setDataInicial(new Date(dataTest.getTimeInMillis()));
		dataTest.set(2014, 2, 22);
		entidadeTest.setDataFinal(new Date(dataTest.getTimeInMillis()));

		try {
			assertFalse(entidade.verificarEntidadeValida(entidadeTest));
			fail("Nao deveria chegar nesta linha");
		} catch (Exception e) {
			assertEquals("Tipo de documento invalido", e.getMessage());
		}
	}

	@Test
	public void testValidarRegras_emailIncompleto() {
		Entidade entidadeTest;
		entidadeTest = new Entidade();
		Calendar dataTest = Calendar.getInstance();

		entidadeTest.setNome("Nome Sobrenome");
		entidadeTest.setNumeroDocumento((long) 123456789);
		entidadeTest.setTipoDocumento(2);
		dataTest.set(2014, 2, 11);
		entidadeTest.setDataInicial(new Date(dataTest.getTimeInMillis()));
		dataTest.set(2014, 2, 22);
		entidadeTest.setDataFinal(new Date(dataTest.getTimeInMillis()));
		entidadeTest.setEmail("emailcom");

		try {
			assertFalse(entidade.verificarEntidadeValida(entidadeTest));
			fail("Nao deveria chegar nesta linha");
		} catch (Exception e) {
			assertEquals("Endereco de email invalido", e.getMessage());
		}
	}

	@Test
	public void testValidarCPF_vazio() {
		String cpfTest;

		cpfTest = "";
		assertFalse(entidade.validaCPF(cpfTest));
	}

	@Test
	public void testValidarCPF_cpfmuitogrande() {
		String cpfTest;

		cpfTest = "123456789101112131415";
		assertFalse(entidade.validaCPF(cpfTest));
	}

	@Test
	public void testValidarCPF_cpfIncorreto() {
		String cpfTest;

		cpfTest = "00000000000";
		assertFalse(entidade.validaCPF(cpfTest));
	}

	@Test
	public void testValidarCPF_cpfIncorreto1() {
		String cpfTest;

		cpfTest = "11111111111";
		assertFalse(entidade.validaCPF(cpfTest));
	}

	@Test
	public void testValidarCPF_cpfIncorreto2() {
		String cpfTest;

		cpfTest = "22222222222";
		assertFalse(entidade.validaCPF(cpfTest));
	}

	@Test
	public void testValidarCPF_cpfIncorreto3() {
		String cpfTest;

		cpfTest = "33333333333";
		assertFalse(entidade.validaCPF(cpfTest));
	}

	@Test
	public void testValidarCPF_cpfIncorreto4() {
		String cpfTest;

		cpfTest = "44444444444";
		assertFalse(entidade.validaCPF(cpfTest));
	}

	@Test
	public void testValidarCPF_cpfIncorreto5() {
		String cpfTest;

		cpfTest = "55555555555";
		assertFalse(entidade.validaCPF(cpfTest));
	}

	@Test
	public void testValidarCPF_cpfIncorreto6() {
		String cpfTest;

		cpfTest = "66666666666";
		assertFalse(entidade.validaCPF(cpfTest));
	}

	@Test
	public void testValidarCPF_cpfIncorreto7() {
		String cpfTest;

		cpfTest = "77777777777";
		assertFalse(entidade.validaCPF(cpfTest));
	}

	@Test
	public void testValidarCPF_cpfIncorreto8() {
		String cpfTest;

		cpfTest = "88888888888";
		assertFalse(entidade.validaCPF(cpfTest));
	}

	@Test
	public void testValidarCPF_cpfIncorreto9() {
		String cpfTest;

		cpfTest = "99999999999";
		assertFalse(entidade.validaCPF(cpfTest));
	}

	@Test
	public void testcalcDigVerif_incorreto() {
		String cpfTest;

		cpfTest = "96257426101";
		assertFalse(entidade.validaCPF(cpfTest));
	}

	@Test
	public void testcalcDigVerif_correto() {
		String cpfTest;

		cpfTest = "96257426111";
		assertTrue(entidade.validaCPF(cpfTest));
	}
	@Test
	public void testcalcDigVerif_correto2() {
		String cpfTest;

		cpfTest = "16754518340";
		assertTrue(entidade.validaCPF(cpfTest));
	}
	@Test
	public void testcalcDigVerif_correto3() {
		String cpfTest;

		cpfTest = "62152465300";
		assertTrue(entidade.validaCPF(cpfTest));
	}
	}
