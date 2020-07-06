package org.libertas.model;

import java.io.Console;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.libertas.dao.VendaDao;
import org.libertas.pojo.RelatorioCid;
import org.libertas.pojo.Venda;

import com.google.gson.Gson;

public class ListarRelatorioCid implements Modelo {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) {
		try {
			VendaDao vdao = new VendaDao();
			List<Venda> lista = vdao.listar();
			LinkedList<RelatorioCid> relatoriocidades = new LinkedList<RelatorioCid>();
			for (Venda ven : lista) {
				boolean verif = false;
				if(relatoriocidades.size() == 0) {
					RelatorioCid r = new RelatorioCid();
					r.setIdCid(ven.getPessoa().getCidade().getIdcidade());
					r.setCidade(ven.getPessoa().getCidade().getCidade());
					r.setTotalVendido(ven.getValorTotal());
					relatoriocidades.add(r);
					
				}
				else {
					for (RelatorioCid rel : relatoriocidades) {
						if(ven.getPessoa().getCidade().getIdcidade() == rel.getIdCid()) {
							double valortotal = rel.getTotalVendido() + ven.getValorTotal();
							rel.setTotalVendido(valortotal);
							verif = true;
						}
					}
					if(verif == false) {
						RelatorioCid relat = new RelatorioCid();
						relat.setIdCid(ven.getPessoa().getCidade().getIdcidade());
						relat.setCidade(ven.getPessoa().getCidade().getCidade());
						relat.setTotalVendido(ven.getValorTotal());
						relatoriocidades.add(relat);
					}
				}
			}
			Gson gson = new Gson();
			String json = gson.toJson(relatoriocidades);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


}
