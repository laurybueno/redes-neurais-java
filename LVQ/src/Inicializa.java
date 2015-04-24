import java.util.*;
//Classe Responsavel por fazer a inicializacao da Unidade de Saida(vetores de pesos), e receber os dados de Entrada
public class Inicializa{
	
	double [][] vetoresDePesos; //vetores de pesos(matriz que carregas os neuronios de saida)
	double [][] dadosEntrada; //matriz de dados de entrada
	
	
	//contrustor de inicializacao principais dados do algoritmo
	//recebe nome do arquivo de entrada de dados, e a quantidade de neuronios por classe(N)
	public Inicializa(String nomeArquivo, int N)
	{
		//R = numero de atributos da base de dados que serao analisados (64)
		
		N =N*10; //quantidade de Neuronios de saida por LVQ. N*10 pois existem 10 tipos de classes.
		
		vetoresDePesos = new double [N][]; //vetor de Unidades de Saida do Algoritmo
		
		Input arquivo = new Input(); //Objeto de Leitura dos dados
		
		dadosEntrada = arquivo.arquivoToMatrizDouble(nomeArquivo); //vetor de dados de entrada
		//double[] centroide = centroMassa(dados);
	}
	
	//----------INICIALIZACAO DOS PESOS--------------|-primeiros m vetores-INICIO//
	
	//supondo que as classes vao de [0~9]
	//metodo de Inicializacao dos N*10 vetores de peso (sendo N a quantidade de neuronios por classe)
	//atribuindo para os N*10 vetores de pesos as primeiras entradas de classes diferentes
	public void PesosPrimeiraEntrada()
	{
		//laco que inicializa os arrays de pesos como os primeiros dados de classes diferentes
		//repetindo o processo no caso da existencia mais de um array de pesos por classe.
		
		for(int i = 0; i < vetoresDePesos.length; i++){
			vetoresDePesos[i] = this.EncontraPrimeiraEntrada(i%10);
		}
	}
	
	//Encontra a primeira entrada de uma classe "c"
	//recebe parametro "c", que eh o numero da classe, e retorno um array de dados 
	//para o primeiro valor encontrado da classe "c" 
	public double [] EncontraPrimeiraEntrada(int c){
		
		//laco que procura o primeiro dado que aparece, e que eh da classe "c"
		for(int i = 0; i< dadosEntrada.length; i++){
			
			//caso encontrado o dado
			if(c == dadosEntrada[i][dadosEntrada[i].length-1]){
				
				//copia o dado para uma classe auxiliar
				double [] aux;
				aux = dadosEntrada[i].clone();
				
				//e deleta o dado do array de dados, pois ele vai ser desconsiderado no treinamento
				this.DeletaLinhaDadosEntrada(i);
				
				//retorna o dado copiado
				return aux;
			}
		}
		//caso nao encontrado retorna null
		return null;
	}
	
	//Deleta determinada linha da matriz de dadosEntrada, dada pelo indice da linha
	//recebe inteiro com o valor da linha.
	public void DeletaLinhaDadosEntrada(int linha){
		
		//cria collection baseada em dadosEntrada
		List<double []> auxDadosEntrada = new ArrayList<double[]>(Arrays.asList(dadosEntrada)); 
		
		// remove determinada 'linha' da matriz 
		auxDadosEntrada.remove(linha);
		
		//agrega a collection sem a linha deletada na antiga matriz
		dadosEntrada = auxDadosEntrada.toArray(new double [][]{}); 
	} 
	
	//----------INICIALIZACAO DOS PESOS--------------|-primeiros m vetores-FIM//
	
	//----------INICIALIZACAO DOS PESOS--------------|-InicializacaoRandomica-Inicio//
	//TODO especificar dominio da aleatoriedade.
	public void PesosRandom(){
		//recebe tamanho do Vetor de Dados de Entrada 
		int lengthDadosEntrada = dadosEntrada[0].length; 
		
		// Como Math.random sempre retorna valores positivos, é preciso acrescentar mais uma camada de aleatoriedade para variar o sinal
		for(int i = 0; i < vetoresDePesos.length; i++){
			
			//cria um novo vetor nulo com o mesmo tamanho de uma entrada.
			double [] pesosAleatorios = new double [lengthDadosEntrada];
			
			//seta na ultima possicao o valor das classes.
			pesosAleatorios[pesosAleatorios.length-1] = i%10; 
			
			//percorre novo vetor atribuindo valores random para todos campos, menos o ultimo.
			for(int j = 0; j < pesosAleatorios.length-1; j++){
				if(Math.random() > 0.5)
					pesosAleatorios[j] = Math.random();
				else
					pesosAleatorios[j] = Math.random()*(-1);
			}
			//atribui array de numeros aleatorios para cada linha da matriz de vetoresPeso.
			vetoresDePesos[i] = pesosAleatorios; 
		}
	}
	//----------INICIALIZACAO DOS PESOS--------------|-InicializacaoRandomica-Inicio//
	
	
	//----------INICIALIZACAO DOS PESOS--------------|-Inicializacao Pesos Nulos-Inicio//
	//metodo responsavel por incializar os pesos com valores zero
	public void PesosNulos(){
		
		//recebe tamanho do Vetor de Dados de Entrada 
		int lengthDadosEntrada = dadosEntrada[0].length; 
		for(int i =0; i < vetoresDePesos.length; i++){//percorre os vetores de Pesos
			
			//cria um novo vetor nulo com o mesmo tamanho de uma entrada.
			double [] novoPeso = new double[lengthDadosEntrada];
			
			//seta na ultima possicao o valor das classes.
			novoPeso[novoPeso.length-1] = i%10; 
			
			//associa vetor ao arranjo de vetores de dados
			vetoresDePesos[i] = novoPeso;
			
		}
	}
	//----------INICIALIZACAO DOS PESOS--------------|-Inicializacao Pesos Nulos-Fim//


}