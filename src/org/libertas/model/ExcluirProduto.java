package org.libertas.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.libertas.dao.ProdutoDao;

public class ExcluirProduto implements Modelo {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) {
		
		int idProduto = Integer.parseInt(request.getParameter("idproduto"));
		ProdutoDao pdao = new ProdutoDao();
		pdao.excluir(idProduto);
		
		return "Produto excluído com sucesso!";
	}

}
