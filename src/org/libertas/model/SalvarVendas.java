package org.libertas.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.libertas.dao.PessoaDao;
import org.libertas.dao.ProdutoDao;
import org.libertas.dao.VendaDao;
import org.libertas.pojo.Pessoa;
import org.libertas.pojo.Produto;
import org.libertas.pojo.Venda;

public class SalvarVendas implements Modelo {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			if (request.getParameter("idvenda")==null) {
				return "idvenda é obrigatório!";
			}
			if (request.getParameter("pessoa")==null) {
				return "cliente é obrigatório!";
			}
			if (request.getParameter("produto")==null) {
				return "produto é obrigatório!";
			}
			
			
			Venda v = new Venda();
			v.setIdvenda(Integer.parseInt(request.getParameter("idvenda")));
			
			PessoaDao cdao = new PessoaDao();
			Pessoa c = cdao.consultar( Integer.parseInt(request.getParameter("pessoa")));
			
			ProdutoDao pdao = new ProdutoDao();
			Produto p = pdao.consultar( Integer.parseInt(request.getParameter("produto")));
			
						
			v.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
			v.setData(request.getParameter("data"));
			v.setValorTotal(Double.parseDouble(request.getParameter("valorTotal")));
			v.setValorUnitario(Double.parseDouble(request.getParameter("valorUnitario")));
			v.setPessoa(c);
			v.setProduto(p);
			
			
			VendaDao vdao = new VendaDao();
			vdao.inserir(v);
			if (v.getIdvenda()>0) {
				return "registro alterado com sucesso";
			} else {
				return "registro inserido com sucesso";
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


}
