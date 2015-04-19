public class Dados{
	UnidadeDeSaida [] unidadesDeSaida;
	double[][] dadosEntrada;
	double taxaDeAprendizado;
	int M;
	
	//contrutor de inicializacao principais dados do algoritmo
	public Dados()
	{
		String nomeArquivo = "pre processamento/optdigitsNormalizado.txt";
		//R = numero de atributos da base de dados que serao analisados (64)
		M =10; //quantidade de clusters (como a base de dados analisa os 10 digitos)
		unidadesDeSaida = new UnidadeDeSaida [M]; //vetor de Unidades de Saida do Algoritmo
		taxaDeAprendizado=1;
		Input arquivo = new Input(); //Objeto de Leitura dos dados
		dadosEntrada = arquivo.matrizDados(nomeArquivo); //vetor de dados de entrada
		
		//double[] centroide = centroMassa(dados);
	}
	
	//metodo de Inicializacao dos M vetores de peso (sendo M a quantidade de UnidadeDeSaida)
	//atribuindo para os M vetores de pesos as primeiras entradas de classes diferentes
	public void VetoresPeso()
	{
		for(int i = 0; i < M; i++){
			unidadesDeSaida[i].W = PrimeiraEntrada(i);
		}
	}
	
	//Encontra a primeira entrada encontrado de uma classe n
	//recebe parametro n, que eh o numero da classe, e retorno um array de dados 
	//para o primeiro valor encontrado 
	public double [] PrimeiraEntrada(int n){
		for(int i = 0; i< dadosEntrada.length; i++){
			if(n == dadosEntrada[i][dadosEntrada.length-1]){
				double [] aux = dadosEntrada[i];
				return aux;
				//TODO exclusao elemento atribuido
			}
		}
		return null;
	}
	/*
	public void DeletaLinhaDadosEntrada(int linha){
		double [][] novoDadosEntrada = dadosEntrada;
		for(int i =0; i < dadosEntrada.length; i++){
			if(i == linha) 
		}
	} 
	*/
	
}