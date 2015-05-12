
public class LVQ {
	
	double [][] vetoresDePesos; //matriz que carregas os vetores de pesos (unidade de saida)
	double [][] dadosEntrada; //matriz de dados de Entrada (Treinamento)
	double [][] dadosValidacao; //matriz de dados de Entrada (Validacao)
	LVQ melhorEstado; //Guarda Referencia do melhor estado de si mesmo
	
	int contadorPioras; // conta quantas vezes a LVQ teve uma piora no estado
	int numeroMaxPioras; // limitaQuantidade de vezes que houve piora
	double diferencaEntreErros; //mede a diferenca de erro do melhorEstado com estado rodando
	int numeroIteracoes; //contador de Iteracoes(Epocas)
	int numeroFixo; //numero que ira restringir ate que Epoca a LVQ pode chegar
	double taxaDeAprendizado; //taxa de Aprendizado
	double reducaoAprendizado; //valor que reduz a taxa de Aprendizado
	double valorMinimo; //valorMinimo que a taxa de aprendizado pode chegar
	CondicaoParada parada = new CondicaoParada(); //objeto de condicao de parada
	
	//Construtor de inicializacao do LVQ que recebe como parametro um objeto Inicializa que inicializa os pesos e os dados de Entrada do LVQ.
	//Além de receber por parametros dados destinados a serem definidos pelo Usuario.
	public LVQ( Inicializa inicializa, int numeroFixo, double taxaDeAprendizado, double reducaoAprendizado, double valorMinimo, int maxPioras){
		
		this.dadosEntrada = inicializa.dadosEntrada.clone();
		this.dadosValidacao = inicializa.dadosValidacao.clone();
		this.vetoresDePesos = inicializa.vetoresDePesos.clone();

		//Dados passados pelo Usuario - Inicio
		
		this.numeroFixo = numeroFixo;
		this.taxaDeAprendizado = taxaDeAprendizado;
		this.reducaoAprendizado = reducaoAprendizado;
		this.valorMinimo = valorMinimo;
		this.numeroMaxPioras = maxPioras;
		//Dados passados pelo Usuario - Fim
	}
	
	//construtor criado para realizar clonagem da lvq
	public LVQ (LVQ original){
		this.vetoresDePesos = this.clonaMatriz(original.vetoresDePesos);
		this.dadosEntrada = this.clonaMatriz(original.dadosEntrada);
		this.dadosValidacao = this.clonaMatriz(original.dadosValidacao);
		this.contadorPioras = original.contadorPioras;
		this.numeroMaxPioras = original.numeroMaxPioras;
		this.diferencaEntreErros = original.diferencaEntreErros;
		this.numeroIteracoes = original.numeroIteracoes; 
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
	public void Aprendizado(String nomeArquivoLog){
		
		this.numeroIteracoes = 1; //inicializar do contador de Epocas(iteracoes)
		//TODO condicao do while temporaria, ainda falta verifica de parada
		
		while(this.testaParada()){//enquanto não houver uma condicao de parada. Continua a realizar a Epoca
			LVQ copia = new LVQ(this);
			
			// cria objeto que executara o treino, passando o LVQ como parametro
			Treinamento treina = new Treinamento(copia); 
			
			//realiza uma epoca
			treina.Epoca(); 
			
			//atualiza valores das unidadesDeSaida para o LVQ.
			this.vetoresDePesos = treina.lvq.vetoresDePesos.clone(); 
			
			//reduz taxa de aprendizado
			this.AtualizaAprendizado();
			
			//Mostra Progresso Na Tela
			this.ProgressaoTreinamento(nomeArquivoLog);
			
			//incrementa numero de interacoes
			this.numeroIteracoes++;			

		}
	}
	
	//Metodo que responsavel por mostrar na tela a progressao do treinamento 
	public void ProgressaoTreinamento(String nomeArquivoLog){
		
		//intervalo entre epocas, que deve haver quando executar a progessaoTreinamento
		int epocas = 20;	
		
		//verifica se deve mostrar pogresso para epoca corrente.
		if((this.numeroIteracoes % epocas)==0){ 
			
			//faz validacao sobre treinamento
			double [] validacaoSobreSi = this.ValidacaoSobreTreinamento();
			
			//calcula erro sobre treinamento
			double ErroSobreTrein = CalculaTaxaErro(validacaoSobreSi, this.dadosEntrada);
			
			//faz validacao sobre dados de Validacao
			double [] validacaoComum = this.Validacao();
			
			//calcula erro sobre validacao
			double ErroSobreValidacao = CalculaTaxaErro(validacaoComum, this.dadosValidacao);
			
			//printa os erros na Tela, junto do contador de Epoca
			System.out.println("Epoca: " + this.numeroIteracoes);
			System.out.println("Erro sobre Treinamento: "+ ErroSobreTrein);
			System.out.println("Erro sobre Validacao: "+ ErroSobreValidacao);
			System.out.println();
			
			//guarda uma referencia de LVQ com o melhor resultado acada um ciclo de epoca determinado
			this.GuardaMelhorResultado();
			
			
			
			Log log = new Log();
			log.escreveLogRedes(numeroIteracoes, reducaoAprendizado, taxaDeAprendizado, 0, vetoresDePesos, nomeArquivoLog);
			log.escreveAprendizado(nomeArquivoLog, this.numeroIteracoes, ErroSobreTrein, ErroSobreValidacao);
			
		}
	}
	
	//Espaco para criacao do metodo que realiza a validacao
	public double [] Validacao(){
		//declara objeto com metodos de operacao entre vetores
		OperacaoVetores operacao = new OperacaoVetores();
		
		//cria copia desse mesmo LVQ
		LVQ copia = new LVQ(this);
		
		//declara array com as classes que a lvq sugeriu (resposta). 
		double [] resultado = new double[this.dadosValidacao.length];
		
		//percorre as linhas da matriz com os dados de entrada
		for(int i =0; i<this.dadosValidacao.length; i++){
			
			//verifica qual vetor de pesos com a menor distancia de um dos dados de Teste.
			int indexVet = operacao.menorDistancia(this.dadosValidacao[i], copia.vetoresDePesos);
			
			//copia a classe desse vetor de pesos encontrado para o array com os resultados
			resultado[i] = copia.vetoresDePesos[indexVet][copia.vetoresDePesos[0].length-1];
		}
		
		return resultado;
	}
	
	public double [] ValidacaoSobreTreinamento()
	{
		//declara objeto com metodos de operacao entre vetores
		OperacaoVetores operacao = new OperacaoVetores();
		
		//cria copia desse mesmo LVQ
		LVQ copia = new LVQ(this);
		
		//declara array com as classes que a lvq sugeriu (resposta). 
		double [] resultado = new double[this.dadosEntrada.length];
		
		//percorre as linhas da matriz com os dados de entrada
		for(int i =0; i<this.dadosEntrada.length; i++){
			
			//verifica qual vetor de pesos com a menor distancia de um dos dados de Teste.
			int indexVet = operacao.menorDistancia(this.dadosEntrada[i], copia.vetoresDePesos);
			
			//copia a classe desse vetor de pesos encontrado para o array com os resultados
			resultado[i] = copia.vetoresDePesos[indexVet][copia.vetoresDePesos[0].length-1];
		}
		
		return resultado;
	}
	
	//Espaco para criacao do metodo que realiza os testes.
	public double [] Teste(String nomeArquivo){
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
		
		//retorna classes determinadas pela LVQ
		double erro=0;
		erro = CalculaTaxaErro(resultado, dadosTeste);
		System.out.println();
		System.out.println("Erro sobre Teste: "+ erro);

		return resultado;
	}
	
	public void GuardaMelhorResultado(){
		
		//caso ainda nao existe referencia para algum melhor estado,
		//declara como melhorEstado = estadoAtual
		if(this.melhorEstado == null){
			//cria copia desse mesmo LVQ
			melhorEstado = new LVQ(this);
			
			//inicia diferenca entre erros como zero
			melhorEstado.diferencaEntreErros =0;
			
			//inicia contador de pioras
			this.contadorPioras = 0;
		}
		else{
			//Faz a validacao para o Melhor Estado
			double [] validacaoMelhorEstado = melhorEstado.Validacao();
			
			//determina os erros da validacao para o melhorEstado
			double ErroMelhorEstado = CalculaTaxaErro(validacaoMelhorEstado, melhorEstado.dadosValidacao);
			
			//Faz a validacao para o Estado atual
			double [] validacaoAtual = this.Validacao();
			
			//determina os erros da validacao para o estado atual
			double ErroAtual = CalculaTaxaErro(validacaoAtual, this.dadosValidacao);
			
			//calcula diferenca entre erros
			double diferencaErros = ErroMelhorEstado - ErroAtual;
			
			//Caso erro do MelhorEstado e pior que do Estado Atual
			if(diferencaErros > 0){
				//reinicia contador
				this.contadorPioras = 0;
				
				//atualiza MelhorEstado
				melhorEstado = new LVQ(this);
			}
			//Caso erro do Melhor Estado e melhor que do Estado Atual e piora atual é pior que piora anterior
			if(diferencaErros < 0 && melhorEstado.diferencaEntreErros > diferencaErros){
				melhorEstado.diferencaEntreErros = diferencaErros;
				this.contadorPioras++;
			}
		}
	}
	//metodo que testa todas condições de parada
	public boolean testaParada(){
		CondicaoParada parada = new CondicaoParada();
		boolean testa = true;
		
		//testa pela quantidade de epocas
		if(this.numeroFixo != -1 && testa == true){
			testa = testa && parada.testaNumeroFixo(this.numeroIteracoes, this.numeroFixo);
			if(testa == false)
				System.out.println("---Parada pela quantidade de epocas permitada---");
		}
		
		//testa pelo valor minimo permitido para a taxa de aprendizado
		if(this.valorMinimo != -1 && testa == true){
			testa = testa && parada.testaValorMinimo(this.taxaDeAprendizado, this.valorMinimo);
			
			//caso deve ocorrer parada
			if(testa==false){
				System.out.println("---Parada por valor minimo da taxa de aprendizado---");
				
				//verifica se existe um melhor estado
				if(melhorEstado != null){
					//Faz a validacao para o Melhor Estado
					double [] validacaoMelhorEstado = melhorEstado.Validacao();
					
					//determina os erros da validacao para o melhorEstado
					double ErroMelhorEstado = CalculaTaxaErro(validacaoMelhorEstado, melhorEstado.dadosValidacao);
					
					//Faz a validacao para o Estado atual
					double [] validacaoAtual = this.Validacao();
					
					//determina os erros da validacao para o estado atual
					double ErroAtual = CalculaTaxaErro(validacaoAtual, this.dadosValidacao);
					
					//calcula diferenca entre erros
					double diferencaErros = ErroMelhorEstado - ErroAtual;
					
					//caso erro atual e pior que do melhor estado
					if(diferencaErros < 0){
						//recupera melhor estado
						this.vetoresDePesos = melhorEstado.vetoresDePesos;
						System.out.println();
						System.out.println("Neuronios de Saída com melhor estado "+melhorEstado.numeroIteracoes+" recuperados !");
					}
				}
			}
		}
		
		//testa para o valor maximo permitido de pioras
		if(this.numeroMaxPioras != -1 && testa == true){
			testa = testa && parada.testaQntPioras(this.contadorPioras, this.numeroMaxPioras);
			//caso deve ocorrer parada
			if(testa==false){
				System.out.println("---Parada pela quantidade de pioras permitida---");
				
				//restaura o Pesos do Melhor Estado
				this.vetoresDePesos = melhorEstado.vetoresDePesos;
				System.out.println();
				System.out.println("Neuronios de Saída da Epoca "+melhorEstado.numeroIteracoes+" recuperados !");
				
			}
		}
		
		return testa;
	}
	
	//Metodo responsavel por reduzir a taxa de aprendizado por um valor delimitado (reducaoAprendizado)
	public void AtualizaAprendizado(){
			this.taxaDeAprendizado= this.taxaDeAprendizado - this.reducaoAprendizado; //reduz taxa de aprendizado
	}
	
	//Metodo que calcula Taxa De Erro
	//recebe como parametro array com resultados de algum teste para verificacao
	//recebe como parametro matriz com dados que se usou para extrair resultado
	public double CalculaTaxaErro(double [] resultado, double [][] dadosVerificacao){
		int erros =0;
		
		//laco que conta quantidade de erros
		for(int i=0; i<resultado.length;i++){
			
			//caso classe do resultado eh diferente da classe de dados 
			if(resultado[i] != dadosVerificacao[i][dadosVerificacao[0].length-1]){
				//conta erro
				erros++;
			}
		}
		int quantidadeDados = dadosVerificacao.length;
		
		double taxaErro = (double) erros/ (double)quantidadeDados;
		return taxaErro;
	}
	
	//Classe suporte ao Aprendizado com alguns metodos essenciais na verificacao de condicao de parada do aprendizado
	class CondicaoParada{
		
		//metodo de condicao de parada, restingindo pelo numero de interacoes
		//recebe numero de interacoes ocorrida(numeroInteracoes) e numero maximo permitido(numeroFixo)
		//caso deva ocorrer a parada return false, caso contrario true.
		public boolean testaNumeroFixo(int numeroInteracoes,int numeroFixo){
			if(numeroFixo >= numeroInteracoes)
				return true;
			return false;
		}
		
		//metodo de condicao de parada, restingindo pela taxa de Aprendizado 
		//recebe um double da taxa de Aprendizado e numero minimo permitida(valorMinimo)
		//caso deva ocorrer a parada return false, caso contrario true.
		public boolean testaValorMinimo(double taxaDeAprendizado, double valorMinimo){
			if(valorMinimo < taxaDeAprendizado)
				return true;
			return false;
		}
		
		//metodo de condicao de parada, restingindo pelo contador de Pioras
		//caso deva ocorrer a parada return false, caso contrario true.
		public boolean testaQntPioras (int pioras, int maxPioras){
			if(maxPioras > pioras)
				return true;
			return false;
		}
	}
}


