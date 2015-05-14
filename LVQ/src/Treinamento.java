import javax.xml.namespace.QName;


public class Treinamento {
	
	//Objeto lvq, que possui os vetores de pesos, e a matriz de dados de teste.
	LVQ lvq;
	OperacaoVetores operacaoVetores = new OperacaoVetores();
	
	public Treinamento (LVQ lvq){
		this.lvq = lvq;
	}
	
	//promove uma interacao do algoritmo de aprendizado da LVQ
	//trabalha em cima dos vetores de dadosEntrada e atraves de algumas operacoes, modifica as unidades de saida 
	public void Epoca(){
		
		//para cada vetor de dados de Entrada
		for(int i = 0; i < lvq.dadosEntrada.length; i++){
			
			//descobre o indice da unidade de saida mais proxima desse vetor de dados em questao. 
			int indiceWJ = operacaoVetores.menorDistancia(lvq.dadosEntrada[i], lvq.vetoresDePesos);
			
			//atraves do indice descoberto, atribui a unidadedesaida (vetor de pesos) mais proxima ao vetor WJ.
			double [] WJ = lvq.vetoresDePesos[indiceWJ].clone();
			
			//atualiza o peso dessa unidadeDeSaida descoberta.
			AtualizacaoPesos(WJ , i, indiceWJ);
			
			
		}
	}
	
	//Atraves da unidadeDeSaida (vetor de pesos) considerada mais proxima de um dado de entrada,
	//realiza a atualizacao desse vetor de pesos, podendo o aproximar ou afastar do dado de entrada, a depender
	//da classe desses dois componentes.
	//recebe, WJ uma copia do vetor de peso em questao, i o indice que indentifica o dado de entrada no vetor de dados de entrada.
	// e um indice WJ que indentifica o vetor de pesos no vetor de unidadeDeSaida.
	public void AtualizacaoPesos(double [] WJ ,int i, int indiceWJ){
		
		//caso a classe WJ (posicionada na ultima posicao do vetor de pesos) seja igual a classe dos dados de entrada,
		//que tambem esta localizado na ultima posicao do vetor, realiza aproximacao dos pesos
		if(WJ[WJ.length-1] == lvq.dadosEntrada[i][lvq.dadosEntrada[i].length-1]){
			
			//declara vetor aux que recebe a subtracao do vetor de dadosEntrada pelo vetor WJ (peso mais proximo dos dadosEntrada)
			double [] aux = operacaoVetores.operacaoSimples(lvq.dadosEntrada[i], WJ, 1);
			
			//multiplica o resultado da operaco anterior com a taxa de aprendizado
			aux = operacaoVetores.mutiplicacaoArrayComDouble(aux, lvq.taxaDeAprendizado);
			
			//soma resultado anterior com WJ (peso antigo) e joga em WJ
			WJ = operacaoVetores.operacaoSimples(WJ, aux, 0);
			
			//atualiza novo vetor peso, com resultado obtido ate agora
			lvq.vetoresDePesos[indiceWJ] = WJ;
		}	
		
		//caso as classes sejam diferentes, realiza afastamento do peso.
		else{
			
			double [] aux = operacaoVetores.operacaoSimples(lvq.dadosEntrada[i], WJ, 1);
			
			aux = operacaoVetores.mutiplicacaoArrayComDouble(aux, lvq.taxaDeAprendizado);
			
			WJ = operacaoVetores.operacaoSimples(WJ, aux, 1);
			
			lvq.vetoresDePesos[indiceWJ] = WJ;
		}
	} 
	
	
}
//Classe suporte de Treinamento com alguns metodos essenciais na realizacao de operacao entre vetores
class OperacaoVetores{
	
	//metodo que realiza operacoes algebrica de multiplicacao entre um vetor e um valor numerico
	//recebe um dado vetor e um valor numerico
	//retorna o novo vetor produzido pela multiplicacao do vetor por um valor numerico
	public double [] mutiplicacaoArrayComDouble(double [] array1, double numeric){
		//copia array, para o retorno manter a ultima coluna (classe intacta)
		double [] retorno = array1.clone();
			//menos a ultima posicao (classe)
			for(int i =0; i < array1.length-1; i++){//operacao de multicacao de vetor com numerico
				
				retorno[i] = array1[i] * numeric;
			}
		
		return retorno;
	}
	
	//metodo que realiza operacoes algebrica elementos dos vetores
	//recebe dois vetores e um codigo que define o tipo de operacao, e retorna a os resultados de sua operacao.
	//operacao = 0 soma, 1 subtracao
	public double [] operacaoSimples (double [] array1, double [] array2, int operacao){
		//copia array, para o retorno manter a ultima coluna (classe intacta)
		double [] retorno = array2.clone(); 
		
		if(operacao == 0){ //operacao de soma de arrays
			
			for(int i =0; i < array1.length-1; i++){ //menos a ultima posicao (classe)
				retorno[i] = array1[i] + array2[i];
			}
		}
		
		else if(operacao ==1){ //operacao de subtracao de vetores
			for(int i =0; i < array1.length-1; i++){ //menos a ultima posicao (classe)
				retorno[i] = array1[i] - array2[i];
			}
		}
		
		return retorno;
	}
	
	//metodo que descobre qual a menor distancia quando comparado um vetor de dados de entrada e outros M vetores de pesos.
	//recebe vetor de dados de entrada e um vetor de vetores de pesos (Matriz de pesos)
	//e retorna o index vetor de pesos da menor distancia em relacao a dadosEntrada
	public int menorDistancia(double [] dadosEntrada, double [][] vetoresDePesos){
		int quantVetPesos = vetoresDePesos.length; //descobre quando neuronios de saida existem.
		double [] distancias = new double [quantVetPesos];//cria array que ira receber todas as distancias
		
		//descobre todas distancias existentes entre os neuronios e um dado de entrada
		for(int i =0 ; i < vetoresDePesos.length; i++){
			distancias[i] = distanciaEuclidiana(dadosEntrada, vetoresDePesos[i]);
		}
		//valor inicial declarado como o menor
		double menor = distancias[0];
		
		//primeira posicao declarada inicialmente como a menor
		int indiceMenor =0;
		
		//comeca do um pois a primeira posicao jah foi pega
		for(int i =1 ; i < distancias.length; i ++){
			if(distancias[i]<menor){
				menor = distancias[i];
				indiceMenor = i;
			}
		}
		
		return indiceMenor; //retorna indice do vetor de peso mais proximo do dadoEntrada em questao
	}	

	//metodo para calcular a distancia euclidiana entre dois pontos
	//recebe como paramentro dois vetores e retorna a distancia euclidiana
	public double distanciaEuclidiana(double[] vetor, double [] array){
		
		double aux=0;//auxiliar somar as distancias locais
		
		for (int i=0;i < vetor.length-1; i++){// percorre o arranjo para pegar todos os atributos, menos o último (classe)
			
			double local = vetor[i]- array[i]; //pega a distancia local
			
			local = Math.pow(local, 2);// eleva a distancia local ao quadrado
			aux=aux+local;
		}
		aux =Math.sqrt(aux);//tira a raiz quadrada da soma das distancias locais
		return aux;
	}
	
	//metodo para calcular distancia entre dois pontos
	//recebe como parametro dois vetores e retorna a distancia
	//se indice =2, é distancia euclidiana. Se indice =1, é distancia de manhattan
	public double distancia(double[] vetor, double[] array, int indice){
		double aux=0;
		
		for (int i=0;i < vetor.length-1; i++){// percorre o arranjo para pegar todos os atributos, menos o último (classe)
			double local = vetor[i]-array[i];//pega distancia local
			local = Math.pow(local, indice);//eleva ao indice
			aux=aux+local;
		}
		//fazer para calcular a ene-ezima raiz. Esse math.pow nao funciona para mim, na internet fala que funciona :(s
		aux = Math.pow(aux, 1/indice);
		return aux;
		
	}
}


