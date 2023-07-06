package org.exemplo.persistencia.database.application;

import java.util.List;
import java.util.Scanner;

import org.exemplo.persistencia.database.dao.ClienteDAO;
import org.exemplo.persistencia.database.dao.ContaDAO;
import org.exemplo.persistencia.database.dao.IEntityDAO;
import org.exemplo.persistencia.database.db.ConexaoBancoHibernate;
import org.exemplo.persistencia.database.enumerator.TipoConta;
import org.exemplo.persistencia.database.model.Cliente;
import org.exemplo.persistencia.database.model.Conta;

public class Application {

	public static void main(String[] args) {
		
		
		IEntityDAO<Cliente> daocliente = new ClienteDAO(new ConexaoBancoHibernate());
		IEntityDAO<Conta> daoconta = new ContaDAO(new ConexaoBancoHibernate());

		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("\nBem-vindo ao banco! O que você gostaria de fazer?\n");
			System.out.println("1. Cadastrar novo cliente");
			System.out.println("2. Selecionar cliente existente");
			System.out.println("3. Listar clientes");
			System.out.println("4. Excluir conta");
			System.out.println("5. Sair");

			int opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
			
			case 1: //CRIAR CONTA
				System.out.println("Digite o nome do cliente:");
				String nome = scanner.nextLine();
				System.out.println("Digite o CPF do cliente:");
				String cpf = scanner.nextLine();
				
				
				
				Cliente cliente = new Cliente(cpf, nome);
				daocliente.save(cliente);
				break;

			case 2: // FAZER LOGIN POR CPF
			    System.out.println("Digite o id do cliente:");
			    int id = scanner.nextInt();
			    Cliente cliente1 = null;
			    try {
			      
			        
			        cliente1 = daocliente.findById(id);

			        if (cliente1 == null) {
			            System.out.println("Cliente não encontrado");
			            break;
			        }

			        System.out.println("Cliente selecionado: " + cliente1.getNome());
			        System.out.println("\nO que você gostaria de fazer?\n");
			        System.out.println("1. Criar nova conta");
			        System.out.println("2. Ver informações das contas");
			        System.out.println("3. Realizar Depósito");
			        System.out.println("4. Realizar Saque");
			        System.out.println("5. Realizar Transferência");
			        System.out.println("6. Ver extrato");
			        System.out.println("7. Excluir conta");
			        System.out.println("8. Balanço entre contas");
			        System.out.println("9. Sair");

			        opcao = scanner.nextInt();
			        scanner.nextLine();
			    }catch(Exception e) {
			    	System.out.println(e);
			    }
				switch (opcao) {
					case 1: //Criar uma conta Corrente/Poupança
						System.out.println("Escolha o tipo de conta que voce deseja criar:");
						System.out.println("Opcao 1: Conta Corrente\nOpcao 2: Conta Poupanca");
						int opcao2 = scanner.nextInt();
						
						Conta conta = new Conta ();
						conta.setCliente(cliente1);
						
						switch (opcao2) {
							case 1:
								conta.setTipoConta(TipoConta.CORRENTE);
								break;	
								
							case 2:
								conta.setTipoConta(TipoConta.POUPANCA);		
							default:
								break;
						}
						
						daoconta.save(conta);
						daocliente.update(cliente1); 
				
						
//						cliente.adicionarConta(conta);
//						PersistenciaEmArquivo.getInstance().atualizarClienteCadastro(cliente);
//						break;
	
//					case 2: //VER INFORMAÇÃO DE CONTA
//						if (cliente.getContas().size() == 0) {
//							System.err.println("Nao ha contas associada a este cliente.");
//						} else {
//							for (IConta c : cliente.getContas()) {
//								System.out.println(c);
//							}
//						}
//						break;
//						
//					case 3: //REALIZAR SAQUE
//						if (cliente.getContas().size() == 0) {
//							System.err.println("Nao ha contas associada a este cliente.");
//						} else {
//							for (IConta c : cliente.getContas()) {
//								System.out.println(c);
//							}
//						}
//							System.out.println("Insira o numero da conta, em que voce deseja realizar o deposito?");
//							int opcaoContaDeposito = 0;
//							double quantia = 0.0;
//							opcaoContaDeposito = scanner.nextInt();
//							System.out.println("Insira o valor da quantia a ser depositada: ");
//							quantia = scanner.nextDouble();
//							IConta temp = cliente.localizarContaNumero(opcaoContaDeposito);
//						
//						if (temp != null) {
//						temp.depositar(new BigDecimal(quantia));
//						PersistenciaEmArquivo.getInstance().atualizarClienteCadastro(cliente);
//						}
//						break;
//						
//					case 4: //REALIZAR SAQUE
//						if (cliente.getContas().size() == 0) {
//							System.out.println("Nao ha contas associadas a este cliente.");
//						} else {
//							for(IConta c : cliente.getContas()) {
//								System.out.println(c);
//							}
//						}
//						System.out.println("De qual conta deseja sacar?");
//						int opcaoContaSaque = 0;
//						double quantia1 = 0.0;
//						opcaoContaSaque = scanner.nextInt();
//						System.out.println("Insira o valor:");
//						quantia1 = scanner.nextDouble();
//						
//						IConta temp1 = cliente.localizarContaNumero(opcaoContaSaque);
//						if (temp1 != null) {
//							temp1.sacar(new BigDecimal(quantia1));
//							PersistenciaEmArquivo.getInstance().atualizarClienteCadastro(cliente);
//						}
//						break;
//				
//					case 5: //REALIZAR TRANSFERENCIA
//						if (cliente.getContas().size() == 0) {
//							System.out.println("Nao ha contas associadas a este cliente.");
//						} else {
//							for (IConta c : cliente.getContas()) {
//								System.out.println(c);
//						}
//						System.out.println("Digite o número da conta de origem: ");
//				        int numeroContaOrigem = scanner.nextInt();
//				        IConta contaOrigem = cliente.localizarContaNumero(numeroContaOrigem);
//		
//				        System.out.println("Digite o número da conta de destino: ");
//				        int numeroContaDestino = scanner.nextInt();
//				        IConta contaDestino = cliente.localizarContaNumero(numeroContaDestino);
//		
//				        if (contaOrigem == null || contaDestino == null) {
//				            System.out.println("Conta de origem ou destino não encontrada.");
//				        } else {
//				            System.out.println("Digite o valor da transferência: ");
//				            double quantia2 = scanner.nextDouble();
//				            scanner.nextLine(); 
//		
//				            contaOrigem.transferir(contaDestino, new BigDecimal(quantia2));
//				            PersistenciaEmArquivo.getInstance().atualizarClienteCadastro(cliente);
//				        	}
//						}
//						break;
//					
//					case 6: //VER EXTRATO
//						if (cliente.getContas().size() == 0) {
//							System.out.println("Nao ah contas associadas a este cliente.");
//						} else {
//							for (IConta c : cliente.getContas()) {
//								System.out.println(c);
//							}
//						}
//						System.out.println("Insira o numero da conta:");
//						int numeroConta = scanner.nextInt();
//						
//						IConta conta1 = cliente.localizarContaNumero(numeroConta);
//					     if (conta1.getTransacoes().size() == 0) {
//					       System.out.println("Sem transacao");
//					     } else {
//					    	 System.out.println("Insira o mes:");
//					    	 int opcaoMonth = scanner.nextInt();
//					    	 System.out.println("Insira o ano:");
//					    	 int opcaoYear = scanner.nextInt();
//					    	 conta1.imprimirExtratoConta(opcaoMonth, opcaoYear);
//					     }
//					     break;
//					     
					case 7: //EXCLUIR A CONTA
						
						
						if (cliente1.getContas().size() == 0) {
							System.err.println("Nao ha contas associada a este cliente.");
						} else {
							for (Conta c : cliente1.getContas()) {
								System.out.println(c);
							}
						}
						System.out.println("Insira o numero da conta:");
						int numeroConta1 = scanner.nextInt();
						Conta conta1 = daoconta.findById(numeroConta1);
						daoconta.delete(conta1);
						daocliente.update(cliente1);
						break;
				}
//				}
//					default:
//						System.out.println("Opção inválida");
//						break;
//						
//					case 8: //BALANÇO 
//						cliente.balancoEntreContas();
//						break;
//						
//					case 9: //SAIR
//						System.out.println("Até logo!");
//						System.exit(0);
//					}	
//			break;
////				
				case 3: //LISTAGEM DE CLIENTES
             					
					List<Cliente> clientes = daocliente.findAll();
					
					for (Cliente clientel : clientes) {
						System.out.println(clientel);
					}
					
					break;
					
				case 4: //REMOVER CLIENTE
					System.out.println("Insira o id do cliente:");
					int idremover = scanner.nextInt();
					System.out.println();

					cliente = daocliente.findById(idremover);
					if (cliente == null) {
						System.out.println("Cliente não encontrado");
					} else {
						daocliente.delete(cliente);
						System.out.println("Cliente removido com sucesso");
					}
					break;
					
				case 5: //SAIR
					System.out.println("Até logo!");
					System.exit(0);

				default:
					System.out.println("Opção inválida");
					break;
			    
			}
			
		}
	}
}