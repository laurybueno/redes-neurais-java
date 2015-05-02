
public class LVQ {
	
	double [][] vetoresDePesos; //matriz que carregas os vetores de pesos (unidade de saida)
	double [][] dadosEntrada; //matriz de dados de Entrada (podendo ser de: Treinamento, Teste ou Validacao)
	int numeroIteracoes; //contador de Iteracoes(Epocas)
	int numeroFixo; //numero que ira restringir ate que Epoca a LVQ pode chegar
	double taxaDeAprendizado; //taxa de Aprendizado
	double reducaoAprendizado; //valor que reduz a taxa de Aprendizado
	double valorMinimo; //valorMinimo que a taxa de aprendizado pode chegar
	CondicaoParada parada = new CondicaoParada();
	
	//Construtor de inicializacao do LVQ que recebe como parametro um objeto Inicializa que inicializa os pesos e os dados de Entrada do LVQ.
	//Além de receber por parametros dados destinados a serem definidos pelo Usuario.
	public LVQ( Inicializa inicializa, int numeroFixo, double taxaDeAprendizado, double reducaoAprendizado, double valorMinimo){
		
		this.dadosEntrada = inicializa.dadosEntrada.clone();
		this.vetoresDePesos = inicializa.vetoresDePesos.clone();
		
		//Dados passados pelo Usuario - Inicio
		
		this.numeroFixo = numeroFixo;
		this.taxaDeAprendizado = taxaDeAprendizado;
		this.reducaoAprendizado = reducaoAprendizado;
		this.valorMinimo = valorMinimo;
		
		//Dados passados pelo Usuario - Fim
	}
	
	//construtor criado para realizar clonagem da lvq
	public LVQ (LVQ original){
		this.vetoresDePesos = this.clonaMatriz(original.vetoresDePesos);
		this.dadosEntrada = this.clonaMatriz(original.dadosEntrada);
		this.numeroIteracoes = original.numeroFixo; 
		this.numeroFixo = original.numeroFixo;
		this.taxaDeAprendizado = original.taxaDeAprendizado; 
		this.reducaoAprendizado = original.reducaoAprendizado; 
		this.valorMinimo = original.valorMinimo; 
		
	}
	
	//metodo que clona uma matriz
	public double [][] clonaMatriz(double [][] original){
		double [][] clone = new double[original.length][];
		for(int i =0; i < original.length; i++ ){
			clone[i] = original[i].clone();
		}
		return clone;
	}
	
	//Espaco para criacao do metodo que realiza o aprendizado da LVQ.
	public void Aprendizado(){
		
		this.numeroIteracoes = 1; //inicializar do contador de Epocas(iteracoes)
		//TODO condicao do while temporaria, ainda falta verifica de parada
		boolean testa = parada.NumeroFixo(numeroIteracoes, numeroFixo);
		
		while(testa){//enquanto não houver uma condicao de parada. Continua a realizar a Epoca
			//escreve no arquivo de log
			Log log = new Log();
			log.escreveLogRedes(numeroIteracoes, reducaoAprendizado, taxaDeAprendizado, 0, vetoresDePesos, "log/log0");
			LVQ copia = new LVQ(this);
			
			Treinamento treina = new Treinamento(copia); // cria objeto que executara o treino, passando o LVQ como parametro
			
			treina.Epoca(); //realiza uma epoca
			
			this.vetoresDePesos = treina.lvq.vetoresDePesos.clone(); //atualiza valores das unidadesDeSaida para o LVQ.
			
			this.AtualizaAprendizado();//reduz taxa de aprendizado
			
			this.numeroIteracoes++;
			testa = parada.NumeroFixo(numeroIteracoes, numeroFixo);
			
			//metodo de teste chamado somente para testes iniciais

		}
	}
	
	//Espaco para criacao do metodo que realiza a validacao
	public void Validacao(){
		
	}
	
	//Espaco para criacao do metodo que realiza os testes.
	public void Teste(String nomeArquivo){
		//Objeto de Leitura dos dados
		Input arquivo = new Input();
		
		//vetorDeDados de Teste
		double [][] dadosTeste = arquivo.arquivoToMatrizDouble(nomeArquivo);	
		
		//declara objeto com metodos de operacao entre vetores
		OperacaoVetores operacao = new OperacaoVetores();
		
		//cria copia desse mesmo LVQ
		LVQ copia = new LVQ(this);
		
		//declara array com as classes que a lvq sugeriu (resposta). 
		double [] resultado = new double[dadosTeste.length];
		
		//percorre as linhas da matriz com os dados de entrada
		for(int i =0; i<dadosTeste.length; i++){
			//verifica qual vetor de pesos com a menor distancia de um dos dados de Teste.
			int indexVet = operacao.menorDistancia(dadosTeste[i], copia.vetoresDePesos);
			//copia a classe desse vetor de pesos encontrado para o array com os resultados
			
			resultado[i] = copia.vetoresDePesos[indexVet][copia.vetoresDePesos[0].length-1];
		}
		
		//---------parte dedicada somente a testes do algoritmo --------INICIO//
		
		for(int i=0; i<resultado.length;i++){
			if(resultado[i]==dadosTeste[i][dadosTeste[0].length-1]){
				System.out.print(true + " ");
			}
			else{
				System.out.print(false + " ");
			}
			
		}
		
		System.out.println();
		//---------parte dedicada somente a testes do algoritmo -----------FIM//
		
	}
	
	//Metodo responsavel por reduzir a taxa de aprendizado por um valor delimitado (reducaoAprendizado)
	public void AtualizaAprendizado(){
			this.taxaDeAprendizado= this.taxaDeAprendizado - this.reducaoAprendizado; //reduz taxa de aprendizado
	}
	
	//Classe suporte ao Aprendizado com alguns metodos essenciais na verificacao de condicao de parada do aprendizado
	class CondicaoParada{
		
		//metodo de condicao de parada, restingindo pelo numero de interacoes
		//recebe numero de interacoes ocorrida(numeroInteracoes) e numero maximo permitido(numeroFixo)
		//caso deva ocorrer a parada return true, caso contrario false.
		public boolean NumeroFixo(int numeroInteracoes,int numeroFixo){
			if(numeroFixo >= numeroInteracoes)
				return true;
			return false;
		}
		
		//metodo de condicao de parada, restingindo pela taxa de Aprendizado 
		//recebe um double da taxa de Aprendizado e numero minimo permitida(valorMinimo)
		//caso deva ocorrer a parada return true, caso contrario false.
		public boolean ValorMinimo(double taxaDeAprendizado, double valorMinimo){
			if(valorMinimo < taxaDeAprendizado)
				return true;
			return false;
		}
	}
}


