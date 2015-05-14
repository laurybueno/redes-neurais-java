import java.util.Arrays;


public class Main {
	
	/* 
	Esta classe é o ponto de entrada para a rede neural MLP.
	Aqui, é definido se a rede neural está em modo de treinamento ou execução, e qual será a sua estrutura inicial.
	Também é daqui que vem a interface básica para controle do usuário.
	*/
	
	
	// define quantos neurônios deverão existir na primeira camada
	int camadaEntrada = 10;
			
	// define quantos neurônios deverão existir na camada escondida
	int camadaEscondida = 10;
			
	// define quantos neurônios deverao existir na camada de saída
	int camadaSaida = 10;
	
	
	
	public static void main(String[] args){
		
		double[][] ent = Arquivo.csvToDouble("../dados/testes_brutos/treino.csv");
		double[][] val = Arquivo.csvToDouble("../dados/testes_brutos/validacao.csv");
		double[][] tes = Arquivo.csvToDouble("../dados/testes_brutos/teste.csv");
		
		Tupla[] entrada = converteTupla(ent);
		Tupla[] validacao = converteTupla(val);
		Tupla[] teste = converteTupla(tes);
		
		Rede mlp = new Rede(64,10,10);
		
		
		//System.out.println(mlp.toString());
		Rede.Treinamento train = mlp.new Treinamento(entrada, validacao, teste, 0.001);
		
		train.executar(100,2000);
		
		System.out.println(mlp.toString());
		
	}
	
	
	// Separa os dados de suas classes e retorna um array de Tuplas.
	// A última coluna de dados será sempre considerada a classe.
	public static Tupla[] converteTupla(double[][] entrada){
		
		double[][] dados = new double[entrada.length][(entrada[0].length-1)];
		int[] classe = new int[entrada.length];
		
		for(int i = 0; i < entrada.length; i++){

			for(int j = 0; j < entrada[0].length; j++){
				if(j < (entrada[0].length-1))
					dados[i][j] = entrada[i][j];
				else
					classe[i] = (int)entrada[i][j];
			}
		}
		
		// cria as tuplas correspondentes
		Tupla[] tupla = new Tupla[entrada.length];
		
		for (int i = 0; i < classe.length; i++) {
			tupla[i] = new Tupla(dados[i],classe[i]);
		}
		
		return tupla;
		
	}
	
	
	
	// printa um array bidimensional quadrado na tela e informa quantas linhas foram lidas
	public static void printDados(double [][] a)
	{

		int linhas = 0;
		for(int i = 0; i < a.length; i++)
		{
			for(int j = 0; j < a[0].length; j++)
			{
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
			linhas++;
		}
		System.out.println("Total de linhas encontradas: " + linhas);
	}
	
	// printa o array de classes
	public static void printClasses(int [] classe){
		for(int i = 0; i < classe.length; i++){
			System.out.println("Classe "+i+": " + classe[i]);
		}
	}
	

}
