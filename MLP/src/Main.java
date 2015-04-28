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
		
		double[][] entrada = Arquivo.csvToDouble("../dados/or.csv");
		
		Rede mlp = new Rede(2,2,2);
		
		
		// separa os dados de suas classes
		int[] classe = new int[entrada.length];
		double[][] dados = new double[entrada.length][(entrada[0].length-1)];
		
		for(int i = 0; i < entrada.length; i++){
			
			for(int j = 0; j < entrada[0].length; j++){
				if(j < 2)
					dados[i][j] = entrada[i][j];
				else
					classe[i] = (int)entrada[i][j];
			}
			
		}
		
		//System.out.println(mlp.toString());
		Rede.Treinamento train = mlp.new Treinamento(dados, classe, 1);
		
		train.executar(100);
		
		System.out.println(mlp.toString());
		
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
