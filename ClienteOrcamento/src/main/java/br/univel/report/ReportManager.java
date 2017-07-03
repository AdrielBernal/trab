package br.univel.report;

import java.util.Locale;

import br.univel.orcamento.Orcamento;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class ReportManager {

	private static final String JASPER_ORCAMENTO = System.getProperty("user.dir") + "\\orcamento.jasper";

	public void exportarCustom(Orcamento c) {

		JRDataSource customDs = new CustomClienteReport(c);

		JasperPrint jp;
		try {
			jp = JasperFillManager.fillReport(JASPER_ORCAMENTO, null, customDs);

			Locale locale = Locale.getDefault();
			JasperViewer.viewReport(jp, false, locale);
		} catch (JRException e) {
			e.printStackTrace();
		}

	}

}
