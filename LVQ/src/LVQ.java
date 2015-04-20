
public class LVQ {
	
	UnidadeDeSaida [] unidadesDeSaida;
	double [][] dadosEntrada;
	
	public LVQ( Inicializa inicializa){
		
		//Declaracoes dos dados principais - Inicio		
		inicializa.PesosPrimeiraEntrada();//metodo que determina os pesos iniciais
		//Declaracoes dos dados principais - Fim
		
		this.dadosEntrada = inicializa.dadosEntrada;
		this.unidadesDeSaida = inicializa.unidadesDeSaida;
	}
	
	//Metodo principal da geracao da LVQ, onde a LVQ e treinada segundo uma entrada dada.
	//Ele recebe por parametro valores importantes do treinamento como: dados de Entrada (dadosEntrada), 
	//um array com as Unidades de Saida (unidadesDeSaida), taxa de aprendizado (taxaDeAprendizado), valor 
	//da reducao da taxa de Aprendizado (reducaoAprendizado), e valores de criterios de parada como numeroFixo e valorMinimo
	//Por fim retorna o array de UnidadeDeSaida jah treinados
	static UnidadeDeSaida [] Treinamento(double [][] dadosEntrada, UnidadeDeSaida [] unidadesDeSaida, double taxaDeAprendizado, 
										 double reducaoAprendizado, int numeroFixo , double valorMinimo){
		
		int numeroInteracoes = 0; //contador para quantidade de Interacoes do Algoritmo
		while(!(paradaNumeroFixo(numeroInteracoes, numeroFixo) && paradaValorMinimo(taxaDeAprendizado, valorMinimo))){
			for(int i = 0; i < dadosEntrada.length; i++){
				int indiceWJ = menorDistancia(dadosEntrada[i], unidadesDeSaida); //array de pesos mais proximo do dadoEntrada
				double [] WJ = unidadesDeSaida[indiceWJ].VetorPesos;
				if(WJ[WJ.length-1] == dadosEntrada[i][dadosEntrada[i].length-1]){
					double [] aux = operacaoEntreArray(dadosEntrada[i], WJ, 1);
					aux = mutiplicacaoArrayComDouble(aux, taxaDeAprendizado);
					WJ = operacaoEntreArray(WJ, aux, 0);
					unidadesDeSaida[indiceWJ].VetorPesos = WJ;
				}	
				else{
					double [] aux = operacaoEntreArray(dadosEntrada[i], WJ, 1);
					aux = mutiplicacaoArrayComDouble(aux, taxaDeAprendizado);
					WJ = operacaoEntreArray(WJ, aux, 1);
					unidadesDeSaida[indiceWJ].VetorPesos = WJ;
				}
			}
			taxaDeAprendizado = taxaDeAprendizado - reducaoAprendizado;
			
			numeroInteracoes++;
		}
		return unidadesDeSaida;
	}
	
	//metodo que realiza operacoes algebrica de multiplicacao entre um array e um valor numerico
	//recebe um dado array e um valor numerico
	//retorna o novo array produzido pela multiplicacao do array por um valor numerico
	static double [] mutiplicacaoArrayComDouble(double [] array1, double numeric){
		double [] retorno = new double [array1.length-1];
			for(int i =0; i < array1.length; i++){
				retorno[i] = array1[i] * numeric;
			}
		return retorno;
	}
	
	//metodo que realiza operacoes algebrica elementos dos arrays
	//recebe dois arrays e um codigo que define o tipo de operacao, e retorna a os resultados de sua operacao.
	//operacao = 0 soma, 1 subtracao
	static double [] operacaoEntreArray (double [] array1, double [] array2, int operacao){
		double [] retorno = new double [array1.length-1]; 
		if(operacao == 0){ //operacao de soma de arrays
			for(int i =0; i < array1.length; i++){
				retorno[i] = array1[i] + array2[i];
			}
		}
		else if(operacao ==1){ //operacao de subtracao de arrays
			for(int i =0; i < array1.length; i++){
				retorno[i] = array1[i] - array2[i];
			}
		}
		
		return retorno;
	}
	
	//metodo que descobre qual a menor distancia quando comparado um array de dados de entrada e outros M arrays de pesos.
	//recebe array de dados de entrada e um array de UnidadeDeSaida
	//e retorna o index array de pesos da menor distancia em relacao a dadosEntrada
	static int menorDistancia(double [] dadosEntrada, UnidadeDeSaida [] unidades){
		int indiceMenor = 0;
		
		for(int i =0 ; i < unidades.length-1; i++){
			UnidadeDeSaida aux = unidades[i]; 
			double [] pesos = aux.VetorPesos;
			UnidadeDeSaida auxProx = unidades[i];
			double [] pesosProx = auxProx.VetorPesos;
			// verifica qual indice com a menor distancia euclidiana
			if(distanciaEuclidiana(dadosEntrada, pesos) > distanciaEuclidiana(dadosEntrada, pesosProx))
				indiceMenor = i+1;
		}
		return indiceMenor;
	}
	
	//metodo de condicao de parada, restingindo pelo numero de interacoes
	//recebe numero de interacoes ocorrida(numeroInteracoes) e numero maximo permitida(numeroFixo)
	//caso deva ocorrer a parada return true, caso contrario false.
	static boolean paradaNumeroFixo(int numeroInteracoes,int numeroFixo){
		if(numeroFixo < numeroInteracoes)
			return true;
		return false;
	}
	
	//metodo de condicao de parada, restingindo pela taxa de Aprendizado 
	//recebe um double da taxa de Aprendizado e numero minimo permitida(valorMinimo)
	//caso deva ocorrer a parada return true, caso contrario false.
	static boolean paradaValorMinimo(double taxaDeAprendizado, double valorMinimo){
		if(valorMinimo < taxaDeAprendizado)
			return true;
		return false;
	}
	
	//metodo para calcular a distancia euclidiana entre dois pontos
	//recebe como paramentro dois vetores e retorna a distancia euclidiana
	static double distanciaEuclidiana(double[] vetor, double [] array){
		double aux=0;//auxiliar somar as distancias locais
		for (int i=0;i<vetor.length;i++){// percorre o arranjo para pegar todos os atributos
			double local = vetor[i]- array[i]; //pega a distancia local
			local = Math.pow(local, 2);// eleva a distancia local ao quadrado
			aux=aux+local;
		}
		aux =Math.sqrt(aux);//tira a raiz quadrada da soma das distancias locais
		return aux;
	}
	
	// não sei se sera usado. pensei que isso fosse ser usado para calcular centroide. Nao sei o que eh centroide.
	//metodo para descobrir a media de uma coluna
	// recebe como parametro uma matriz e a coluna desejada
	//retorna a media aritmetica
	static double mediaColuna(double[][] matriz, int coluna){
		double media =0;
		for(int i=0;i<matriz.length;i++){// percorre todas as linhas da matriz
			media = media + matriz[i][coluna];// soma todos os elementos de uma coluna
		}
		media = media/matriz.length; //divide pela quantidade de linhas
		return media;
	}
	
	//funcao para calcular o centor de massa (talvez seja a mesma coisa que o centroide)
	//recebe como parametro a matriz com os pontos e retorna um vetor com o ponto que eh o centro de massa
	static double[] centroMassa(double[][] matriz){
		double[] centroMassa = new double[matriz[0].length];
		for(int i=0;i<centroMassa.length;i++){// percorre o vetor com o centro de massa
			centroMassa[i]=mediaColuna(matriz, i);// a variavel do ponto eh igual ah media de todos os pontos daquela variavel
		}
		return centroMassa;
	}
}
