
public class LVQ {
	public static void main (String []args){
		Dados dados = new Dados();//Inicializa principais dados do algoritmo
		dados.VetoresPeso(); //metodo que determina os pesos iniciais
		UnidadeDeSaida [] unidadesDeSaida = dados.unidadesDeSaida;
		double[][] dadosEntrada = dados.dadosEntrada;
		
		/*Valores passados pelo Usuario - Inicio*/
		int numeroFixo =10;
		double valorMinimo = 0.1;
		double taxaDeAprendizado = dados.taxaDeAprendizado;
		double valorReducaoTaxaAprendizado = 0.1;
		/*Valores passados pelo Usuario - Fim */
		
		int numeroInteracoes = 0; //contador para quantidade de Interacoes do Algoritmo
		
		while(!(paradaNumeroFixo(numeroInteracoes, numeroFixo) && paradaValorMinimo(taxaDeAprendizado, valorMinimo))){
			for(int i = 0; i < dadosEntrada.length; i++){
				int indiceWJ = menorDistancia(dadosEntrada[i], unidadesDeSaida); //array de pesos mais proximo do dadoEntrada
				double [] WJ = unidadesDeSaida[indiceWJ].W;
				if(WJ[WJ.length-1] == dadosEntrada[i][dadosEntrada[i].length-1]){
					double [] aux = operacaoEntreArray(dadosEntrada[i], WJ, 1);
					aux = mutiplicacaoArrayComDouble(aux, taxaDeAprendizado);
					WJ = operacaoEntreArray(WJ, aux, 0);
					unidadesDeSaida[indiceWJ].W = WJ;
				}	
				else{
					double [] aux = operacaoEntreArray(dadosEntrada[i], WJ, 1);
					aux = mutiplicacaoArrayComDouble(aux, taxaDeAprendizado);
					WJ = operacaoEntreArray(WJ, aux, 1);
					unidadesDeSaida[indiceWJ].W = WJ;
				}
			}
			taxaDeAprendizado = taxaDeAprendizado - valorReducaoTaxaAprendizado;
			
			numeroInteracoes++;
		}
		
	}
	
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
		if(operacao == 0){
			for(int i =0; i < array1.length; i++){
				retorno[i] = array1[i] + array2[i];
			}
		}
		else if(operacao ==1){
			for(int i =0; i < array1.length; i++){
				retorno[i] = array1[i] - array2[i];
			}
		}
		else if(operacao ==2){
			for(int i =0; i < array1.length; i++){
				retorno[i] = array1[i] * array2[i];
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
			double [] pesos = aux.W;
			UnidadeDeSaida auxProx = unidades[i];
			double [] pesosProx = auxProx.W;
						
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
