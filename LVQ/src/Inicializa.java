import java.util.*;
//Classe Responsavel por fazer a inicializacao da Unidade de Saida(vetores de pesos), e receber os dados de Entrada
public class Inicializa{
	UnidadeDeSaida [] unidadesDeSaida; //vetores de pesos
	double[][] dadosEntrada; //matriz de dados de entrada
	
	//contrutor de inicializacao principais dados do algoritmo
	//recebe nome do arquivo de entrada de dados, e a quantidade de neuronios por classe
	public Inicializa(String nomeArquivo, int N)
	{
		//R = numero de atributos da base de dados que serao analisados (64)
		N =10; //quantidade de Neuronios por cada classe
		unidadesDeSaida = new UnidadeDeSaida [N]; //vetor de Unidades de Saida do Algoritmo
		Input arquivo = new Input(); //Objeto de Leitura dos dados
		dadosEntrada = arquivo.matrizDados(nomeArquivo); //vetor de dados de entrada
		//double[] centroide = centroMassa(dados);
	}
	
	//supondo que as classes vao de [0~9]
	//metodo de Inicializacao dos N*10 vetores de peso (sendo N a quantidade de neuronios por classe)
	//atribuindo para os N*10 vetores de pesos as primeiras entradas de classes diferentes
	public void PesosPrimeiraEntrada()
	{
		//laco que inicializa os arrays de pesos como os primeiros dados de classes diferentes
		//repetindo o processo no caso da existencia mais de um array de pesos por classe.
		for(int i = 0; i < unidadesDeSaida.length; i++){
			unidadesDeSaida[i].VetorPesos = this.EncontraPrimeiraEntrada(i%10);
		}
	}
	
	//Encontra a primeira entrada de uma classe "c"
	//recebe parametro "c", que eh o numero da classe, e retorno um array de dados 
	//para o primeiro valor encontrado da classe "c" 
	public double [] EncontraPrimeiraEntrada(int c){
		//laco que procura o primeiro dado que aparece, e que eh da classe "c"
		for(int i = 0; i< dadosEntrada.length; i++){
			//caso encontrado o dado
			if(c == dadosEntrada[i][dadosEntrada.length-1]){
				//copia o dado para uma classe auxiliar
				double [] aux = dadosEntrada[i];
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
		List<double []> auxDadosEntrada = new ArrayList<double[]>(Arrays.asList(dadosEntrada)); //cria collection baseada em dadosEntrada
		auxDadosEntrada.remove(linha); // remove determinada 'linha' da matriz 
		dadosEntrada = auxDadosEntrada.toArray(new double [][]{}); //agrega a collection sem a linha deletada na antiga matriz
	} 
	
	
}