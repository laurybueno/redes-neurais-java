public class Main {
	
	/* 
	Esta classe é o ponto de entrada para a rede neural MLP.
	Aqui, é definido se a rede neural está em modo de treinamento ou execução, e qual será a sua estrutura inicial.
	Também é daqui que vem a interface básica para controle do usuário.
	*/
	
	public static void main(String[] args){
		if(args.length < 9) {
			System.out.println("Uso: MLP [arquivo de entrada] [arquivo de validacao] [arquivo de teste] " +
					"[taxa de aprendizado inicial] [num. de neuronios na camada escondida] [inicializacao de pesos aleatoria (true/false)] [intervalos de validacao] [fracassos aceitos] [repeticoes]");
			System.exit(1);
		}
		
		int repeticoes = Integer.parseInt(args[8]);
		
		try {
			double taxaAprendizadoInicial = Double.parseDouble(args[3]);
			int neuroniosCamadaEscondida = Integer.parseInt(args[4]);
			boolean pesosAleatorios = Boolean.parseBoolean(args[5]);
			
			int intervalo = Integer.parseInt(args[6]);
			int fracassos = Integer.parseInt(args[7]);
			
			for (int i = 0; i < repeticoes; i++) {
				// mudança para receber arquivos de holdout sequenciais
				Tupla[] entrada = converteTupla(Arquivo.csvToDouble(args[0]+i+".csv"));
				Tupla[] validacao = converteTupla(Arquivo.csvToDouble(args[1]+i+".csv"));
				Tupla[] teste = converteTupla(Arquivo.csvToDouble(args[2]+i+".csv"));
				
				Rede mlp = new Rede(entrada[0].length(),neuroniosCamadaEscondida,10,pesosAleatorios);
				
				Rede.Treinamento train = mlp.new Treinamento(entrada, validacao, teste, taxaAprendizadoInicial);
				
				train.executar(intervalo,fracassos);
			}
			
			// prepara e grava os sumários de pós-processamento
			PosProcessamento.getInstance().gravaArq();
			PosProcessamento.clear();
			
			
		} catch(Exception e) {
			System.out.println("Argumentos preenchidos incorretamente! Consulte o arquivo readme.txt para mais detalhes.");
			System.exit(1);
		}
		
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
