package br.univel.report;

import java.util.Iterator;

import br.univel.orcamento.Orcamento;
import br.univel.produto.Produto;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class CustomClienteReport implements JRDataSource {

	private Orcamento orcamento;
	private Iterator<Produto> it;
	private Produto selecionado;

	public CustomClienteReport(Orcamento c) {
		this.orcamento = c;
		this.it = c.getProdutos().iterator();
	}

	@Override
	public Object getFieldValue(JRField field) throws JRException {
		if ("nome".equals(field.getName())) {
			return orcamento.getDescricao();
		}
		if ("id_produto".equals(field.getName())) {
			return selecionado.getId();
		}
		if ("desc_produto".equals(field.getName())) {
			return selecionado.getDescricao();
		}
		if ("val_prod".equals(field.getName())) {
			return selecionado.getValorDolar();
		}
		if ("val_total".equals(field.getName())) {
			return orcamento.getValorTotalDolar();
		}

		throw new RuntimeException("Deu ruim!");
	}

	@Override
	public boolean next() throws JRException {
		if (this.it.hasNext()) {
			this.selecionado = it.next();
			return true;
		}
		return false;
	}

}
