package org.libertas.model;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.libertas.dao.GrupoDao;
import org.libertas.dao.ProdutoDao;
import org.libertas.pojo.Grupo;
import org.libertas.pojo.Produto;


public class InsereProduto implements Modelo {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			
			if (request.getParameter("descricao").equals("")) {
				return "Descricao é obrigatório!!!";
			}
			
			Produto p = new Produto();
			p.setIdproduto(Integer.parseInt(request.getParameter("idproduto")));
			p.setDescricao(request.getParameter("descricao"));
			p.setPreco(Double.parseDouble(request.getParameter("preco")));
			
			GrupoDao gdao = new GrupoDao();
			Grupo g = gdao.consultar( Integer.parseInt(request.getParameter("grupo")));
			
			p.setGrupo(g);
			
			ProdutoDao pdao = new ProdutoDao();
			pdao.inserir(p);
			if (p.getIdproduto()>0) {
				return "Produto alterado com sucesso!";
			} else {
				return "Produto inserido com sucesso!";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
