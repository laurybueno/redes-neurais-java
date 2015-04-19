public class Inicializa{
	UnidadeDeSaida [] unidadesDeSaida;
	double[][] dados;
	
	//metodo principal de inicializacao dos dados do algoritmo
	public void Dados()
	{
		String nomeArquivo = "pre processamento/optdigitsNormalizado.txt";
		//R = numero de atributos da base de dados que serao analisados (64)
		int M =10; //quantidade de clusters (como a base de dados analisa os 10 digitos)
		UnidadeDeSaida [] unidadesDeSaida = new UnidadeDeSaida [M]; //vetor de Unidades de Saida do Algoritmo
		int taxaDeAprendizado=1;
		Input arquivo = new Input();
		double[][] dados = arquivo.matrizDados(nomeArquivo); //vetor de dados de entrada
		
		//double[] centroide = centroMassa(dados);
	}
	
	//metodo de Inicializacao dos M vetores de peso (sendo M a quantidade de UnidadeDeSaida)
	public void InicializaVetoresPeso(dados int M)
	{
		for(int i = 0; i < M; i++){
			
		}
			

	}
	
	//Encontra a primeira entrada encontrado de uma classe n
	public double [] PrimeiraEntrada(int dados, int M){
		for(int i = 0; i< dados.length; i++){
			if(M == dados[i][dados.length-1])
				return dados[i];
		}
		return null;
	}
	
}