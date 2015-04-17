
public class Rede {

	/*
	 * Esta classe controla o funcionamento da Rede Neural MLP.
	 * Se ela for instanciada em modo de treinamento, ela mesma o controlará assim que o treinamento for requisitado.
	 * Por outro lado, se ela for instanciada em modo de execução, ela irá preparar os neurônios com pesos pesos e devolver 
	 * quase imediatamente o controle para a classe Main.
	 */

	
	//************* Formação da instância de rede neural *********************//
	
	Neuronio[] camadaEntrada;
	Neuronio[] camadaEscondida;
	Neuronio[] camadaSaida;

	// Construtor determina quantos neuronios deverão ser criados a partir do comprimento dos arrays de pesos recebidos.
	// Como a camada de saída não guarda pesos, ela não recebe um array correspondente, mas apenas um int indicando com quantos neurônios ela deverá ser populada.
	// Este construtor será invocado no caso de a rede estar sendo criada em modo de execução.
	public Rede(double[][] camadaEntrada, double[][] camadaEscondida, int camadaSaida){

		// reserva espaço nos arrays para todos os neurônios que serão criados a seguir
		this.camadaEntrada = new Neuronio[camadaEntrada.length];
		this.camadaEscondida = new Neuronio[camadaEscondida.length];
		this.camadaSaida = new Neuronio[camadaSaida];


		// Os dois loops a seguir criam os neurônios da camada de entrada e inserem os pesos dados
		for(int i = 0; i < camadaEntrada.length; i++){
			// este primeiro loop tem suas iterações controladas pelo número de neurônios na camada de entrada
			this.camadaEntrada[i] = new Neuronio(camadaEscondida.length);

			for(int j = 0; j < camadaEntrada[0].length; j++)
				// este segundo loop tem suas iterações controladas pela quantidade de pesos que devem ser configurados
				this.camadaEntrada[i].setPeso(j, camadaEntrada[i][j]);
		}

		// Os dois loops a seguir criam os neurônios da camada escondida e inserem os pesos dados
		for(int p = 0; p < camadaEscondida.length; p++){
			// este primeiro loop tem suas iterações controladas pelo número de neurônios na camada escondida
			this.camadaEscondida[p] = new Neuronio(camadaSaida);

			for(int k = 0; k < camadaEscondida[0].length; k++)
				// este segundo loop tem suas iterações controladas pela quantidade de pesos que devem ser configurados
				this.camadaEscondida[p].setPeso(k, camadaEscondida[p][k]);
		}

		// O loop a seguir cria os neurônios da camada de saída
		for(int x = 0; x < camadaSaida; x++)
			// este loop tem suas iterações controladas pelo número de neurônios na camada de saída
			this.camadaSaida[x] = new Neuronio();

	}
	
	
	// Esse construtor será invocado se a rede for iniciada em modo de treinamento
	// A definição de pesos será delegada para a classe Neuronio
	public Rede(int camadaEntrada, int camadaEscondida, int camadaSaida){
		
		// reserva espaço nos arrays para todos os neurônios que serão criados a seguir
		this.camadaEntrada = new Neuronio[camadaEntrada];
		this.camadaEscondida = new Neuronio[camadaEscondida];
		this.camadaSaida = new Neuronio[camadaSaida];
		
		
		// cria os neurônios de entrada
		for(int i = 0; i < camadaEntrada; i++){
			this.camadaEntrada[i] = new Neuronio(camadaEscondida);
			this.camadaEntrada[i].setPeso();
		}
		
		// cria os neurônios escondidos
		for(int j = 0; j < camadaEscondida; j++){
			this.camadaEscondida[j] = new Neuronio(camadaSaida);
			this.camadaEscondida[j].setPeso();
		}
		
		// cria os neurônios de saída
		for(int k = 0; k < camadaSaida; k++){
			this.camadaSaida[k] = new Neuronio();
			this.camadaSaida[k].setPeso();
		}
		
	}

	
	//************* Controle para treinamento *********************//
	
	// Variáveis que armazenam os dados de Entrada e Saída Esperada entregues a esta classe para o treinamento
	// Os tipos ainda precisam ser determinados levando em conta como é o banco de dados usado
	Object[] Entrada;
	Object[] SaidaEsperada;
	
	
	
	// ponto de entrada para o algoritmo de treinamento
	public Rede treinamento(Object[] Entrada, Object[] SaidaEsperada, int treinamentos){
		this.Entrada = Entrada;
		this.SaidaEsperada = SaidaEsperada;
		
		// Loop das épocas de treinamento
		for(int epoca = 1; epoca <= treinamentos; epoca++){
			

			// loop das tuplas disponíveis nesta época
			for(int linhaAtual = 1; linhaAtual <= this.Entrada.length; linhaAtual++){


				// armazena os resultados das operações de Feed Forward da rede neural para a entrada atual
				double[] feedForward1 = new double[camadaEntrada.length];
				double[] feedForward2 = new double[camadaEscondida.length];

				for(int colunaAtual = 0; colunaAtual < camadaEntrada.length; colunaAtual++){
					// passa a entrada atual para cada neurônio da primeira camada e capta as saídas
					

				}

			}

			
			
			
		}
		
		
		// retorna a rede neural modificada
		return this;
	}
	
	
	
	
	
	
	
	
	
	
	//************* Controle para execução *********************//






}
