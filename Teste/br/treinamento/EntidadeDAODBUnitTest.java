package br.treinamento;

import java.io.FileInputStream;
import java.sql.Date;
import java.util.Calendar;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

public class EntidadeDAODBUnitTest extends DatabaseTestCase {

	@Override
	protected IDatabaseConnection getConnection() throws Exception {

		return new DatabaseConnection(ConnectionFactory.getConnection());
	}

	public void test() {

	}

	@Override
	protected IDataSet getDataSet() throws Exception {

		IDataSet dataSet = FlatXmlDataSetBuilder().build(
				new FileInputStream("EntidadeXML.xml"));

		ReplacementDataSet rDataSet = new ReplacementDataSet(dataSet);
		rDataSet.addReplacementObject("null", null);
		Calendar hoje = Calendar.getInstance();
		hoje.add(Calendar.DAY_OF_MONTH, 0);
		rDataSet.addReplacementObject("hoje", hoje);
		Calendar ontem = Calendar.getInstance();
		ontem.add(Calendar.DAY_OF_MONTH, -1);
		rDataSet.addReplacementObject("ontem", ontem.getTime());

		return rDataSet;
	}

}
