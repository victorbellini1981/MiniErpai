package org.libertas.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.libertas.dao.ProdutoDao;
import org.libertas.pojo.Produto;

import com.google.gson.Gson;

public class ConsultarProduto implements Modelo {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) {
		
		int idProduto = Integer.parseInt(request.getParameter("idproduto"));
		ProdutoDao pdao = new ProdutoDao();
		Produto p = pdao.consultar(idProduto);
		Gson gson = new Gson();
		
		return gson.toJson(p);
	}

}
