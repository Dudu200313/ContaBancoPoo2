package org.exemplo.persistencia.database.application;

import org.exemplo.persistencia.database.dao.ClienteDAO;
import org.exemplo.persistencia.database.dao.ContaDAO;
import org.exemplo.persistencia.database.dao.IEntityDAO;
import org.exemplo.persistencia.database.db.ConexaoBancoHibernate;
import org.exemplo.persistencia.database.enumerator.TipoConta;
import org.exemplo.persistencia.database.model.Cliente;
import org.exemplo.persistencia.database.model.Conta;

public class Application {

	public static void main(String[] args) {
		
		Cliente cliente = new Cliente("carol", "1234862");
		IEntityDAO<Cliente> cdao = new ClienteDAO(new ConexaoBancoHibernate());
		cdao.save(cliente);
		
		Conta conta = new Conta();
		conta.setCliente(cliente);
		conta.setTipoConta(TipoConta.POUPANCA);
		IEntityDAO<Conta> coDAO = new ContaDAO(new ConexaoBancoHibernate());
		
		
		
//		
	}
}
