package org.libertas.model;

import java.io.Console;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.libertas.dao.VendaDao;
import org.libertas.pojo.RelatorioGrp;
import org.libertas.pojo.Venda;

import com.google.gson.Gson;

public class ListarRelatorioGrp implements Modelo {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) {
		try {
			VendaDao vdao = new VendaDao();
			List<Venda> lista = vdao.listar();
			LinkedList<RelatorioGrp> relatoriogrupos = new LinkedList<RelatorioGrp>();
			for (Venda ven : lista) {
				boolean verif = false;
				if(relatoriogrupos.size() == 0) {
					RelatorioGrp r = new RelatorioGrp();
					r.setIdGrp(ven.getProduto().getGrupo().getIdgrupo());
					r.setGrupo(ven.getProduto().getGrupo().getGrupo());
					r.setValorTotal(ven.getValorTotal());
					relatoriogrupos.add(r);
					
				}
				else {
					for (RelatorioGrp rel : relatoriogrupos) {
						if(ven.getPessoa().getCidade().getIdcidade() == rel.getIdGrp()) {
							double valortotal = rel.getValorTotal() + ven.getValorTotal();
							rel.setValorTotal(valortotal);
							verif = true;
						}
					}
					if(verif == false) {
						RelatorioGrp relat = new RelatorioGrp();
						relat.setIdGrp(ven.getProduto().getGrupo().getIdgrupo());
						relat.setGrupo(ven.getProduto().getGrupo().getGrupo());
						relat.setValorTotal(ven.getValorTotal());
						relatoriogrupos.add(relat);
					}
				}
			}
			Gson gson = new Gson();
			String json = gson.toJson(relatoriogrupos);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


}
