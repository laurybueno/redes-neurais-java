
public class Rede {

	/*
	 * Esta classe controla o funcionamento da Rede Neural MLP.
	 * Se ela for instanciada em modo de treinamento, ela mesma o controlará assim que o treinamento for requisitado.
	 * Por outro lado, se ela for instanciada em modo de execução, ela irá preparar os neurônios com pesos pesos e devolver 
	 * quase imediatamente o controle para a classe Main.
	 */

	
	//************* Formação da instância de rede neural *********************//
	// Vale notar que como os neurônios da primeira camada apenas servem para receber dados, eles não precisam ser instanciados
	
	Neuronio[] camadaEscondida;
	Neuronio[] camadaSaida;
	
	double viesEscondida;
	double viesSaida;

	// Construtor determina quantos neuronios deverão ser criados a partir do comprimento dos arrays de pesos recebidos.
	// Como a camada de entrada não precisa ser instanciada, ela também não tem pesos para guardar.
	// Este construtor será invocado no caso de a rede estar sendo criada em modo de execução.
	public Rede(double[][] camadaEscondida, double[][] camadaSaida, double viesEscondida, double viesSaida){
		
		/*	Neste contexto, camadaEscondida.length é o número de neurônios na camada escondida.
		 *  Enquanto camadaEscondida[0].length, é o número de pesos que cada um desses neurônios 
		 *  precisa armazenar e também o número de neurônios na camada de entrada.
		 */

		// reserva espaço nos arrays para todos os neurônios que serão criados a seguir
		this.camadaEscondida = new Neuronio[camadaEscondida.length];
		this.camadaSaida = new Neuronio[camadaSaida.length];
		
		// armazena o viés de cada camada
		this.viesEscondida = viesEscondida;
		this.viesSaida = viesSaida;


		// Os dois loops a seguir criam os neurônios da camada escondida e inserem os pesos dados
		for(int p = 0; p < camadaEscondida.length; p++){
			// este primeiro loop tem suas iterações controladas pelo número de neurônios na camada de entrada
			this.camadaEscondida[p] = new Neuronio(camadaEscondida[0].length); // cada neurônio é criado com a informação de quantos neurônios existem na camada de entrada

			for(int k = 0; k < camadaEscondida[0].length; k++)
				// este segundo loop tem suas iterações controladas pela quantidade de pesos que devem ser configurados
				this.camadaEscondida[p].setPeso(k, camadaEscondida[p][k]);
		}

		// Os dois loops a seguir criam os neurônios da camada de saída
		for(int x = 0; x < camadaSaida.length; x++){
			// este primeiro loop tem suas iterações controladas pelo número de neurônios na camada de saída
			this.camadaSaida[x] = new Neuronio(camadaSaida[0].length); // cada neurônio é criado com a informação de quantos neurônios existem na camada escondida

			for(int z = 0; z < camadaSaida[0].length; z++)
				// este segundo loop tem suas iterações controladas pela quantidade de pesos que devem ser configurados
				this.camadaSaida[x].setPeso(z, camadaEscondida[x][z]);
		}

	}
	
	
	// Esse construtor será invocado se a rede for iniciada em modo de treinamento
	// A definição de pesos será delegada para a classe Neuronio
	// Este método assume que o viés de cada camada será determinado externamente
	public Rede(int camadaEntrada, int camadaEscondida, int camadaSaida,
			double viesEscondida, double viesSaida){
		
		// reserva espaço nos arrays para todos os neurônios que serão criados a seguir
		this.camadaEscondida = new Neuronio[camadaEscondida];
		this.camadaSaida = new Neuronio[camadaSaida];
		
		// armazena o viés de cada camada
		this.viesEscondida = viesEscondida;
		this.viesSaida = viesSaida;
		
		// cria os neurônios escondidos
		for(int j = 0; j < camadaEscondida; j++){
			this.camadaEscondida[j] = new Neuronio(camadaEntrada);
			this.camadaEscondida[j].setPeso();
		}
		
		// cria os neurônios de saída
		for(int k = 0; k < camadaSaida; k++){
			this.camadaSaida[k] = new Neuronio(camadaEscondida);
			this.camadaSaida[k].setPeso();
		}
		
	}

	
	//************* Controle para treinamento *********************//
	// A classe aninhada a seguir é dedicada ao controle de fluxo de treinamento da rede neural MLP	
	
	class Treinamento {

		// Variável que armazena as linhas de dados entregues a esta classe para o treinamento.
		Tupla[] entrada;
		
		// taxa de aprendizado deste treinamento
		double aprendizado;
		
		// construtor recebe todas as linhas do banco de dados 
		public Treinamento(double[][] dados, int[] classe, double aprendizado){
			this.entrada = new Tupla[classe.length];
			this.aprendizado = aprendizado;
			
			// cria todas as tuplas de treinamento
			for(int i = 0; i < classe.length; i++){
				this.entrada[i] = new Tupla(dados[i],classe[i]);
			}
			
		}

		
		// ponto de entrada para o algoritmo de treinamento
		public Rede run(int treinamentos){

			// Loop das épocas de treinamento
			for(int epoca = 1; epoca <= treinamentos; epoca++){
				
				// Loop das tuplas em cada época
				for(int linhaDeDados = 0; linhaDeDados < entrada.length; linhaDeDados++){
					
				}
			} // encerra loop das épocas
			
			// retorna a rede neural modificada
			return Rede.this;
		}
		
		// uma sessao de treinamento inclui uma operação de feedforward e backpropagation
		private void sessao(Tupla tupla){
			
			
		}

	} // fim da classe aninhada de Treinamento
	
	//************* Controle para execução *********************//
	// classe run de Rede executa a operação de FeedForward e retorna a classe a que a tupla deve pertencer
	public int run(Tupla tupla){
		
		
		
	}






}
