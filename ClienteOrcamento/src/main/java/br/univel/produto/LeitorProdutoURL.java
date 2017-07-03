package br.univel.produto;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LeitorProdutoURL {

	private Pattern pattern = Pattern.compile("([0-9]+)(.*)US\\$ (.*)");

	public List<Produto> lerProdutos() throws Exception {
		List<Produto> lista = new ArrayList<Produto>();

		URL url = new URL("http://www.master10.com.py/lista-txt");
		URLConnection urlCon = url.openConnection();

		InputStream is = urlCon.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);

		BufferedReader in = new BufferedReader(isr);

		String linha;
		while ((linha = in.readLine()) != null) {
			if (linha.matches("[0-9]+.*")) {
				Produto p = lerProduto(linha);
				lista.add(p);
			}
		}

		return lista;
	}

	private Produto lerProduto(String linha) {

		Matcher mat = pattern.matcher(linha);

		Produto produto = new Produto();
		if (mat.matches()) {
			String strId = mat.group(1).trim();
			produto.setId(Long.parseLong(strId));

			String desc = mat.group(2).trim();
			produto.setDescricao(desc);

			String strValorOrg = mat.group(3).trim();
			produto.setValorDolar(new BigDecimal(strValorOrg.replaceAll("\\.", "").replaceAll(",", ".")));
		} else
			throw new RuntimeException("Linha invalida" + linha);

		return produto;
	}

	public static void main(String[] args) throws Exception {
		String url = "http://www.master10.com.py/lista-txt";

		LeitorProdutoURL lpu = new LeitorProdutoURL();

		List<Produto> lista = lpu.lerProdutos();
		lista.forEach((e) -> System.out.println(e.toString()));
	}

}
