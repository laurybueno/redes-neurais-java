
public class Neuronio {
	
	// Define as características de um neurônio genérico na rede neural MLP
	
	
	//************* Formação da instância de neurônio *********************//
	
	// Armazena quantos neurônios existem na camada anterior
	// Essa informação é necessária para que este neurônio saiba quantos pesos deverá armazenar
	int neuroniosCamadaAnt;
	double[] peso;
	
	// No momento de sua criação, cada neurônio saberá quantos neurônios estão na camada anterior 
	public Neuronio(int neuroniosCamadaAnt){
		this.neuroniosCamadaAnt = neuroniosCamadaAnt;
		
		// prepara o array de pesos
		peso = new double[neuroniosCamadaAnt];
	}
	
	
	//************* Definição de pesos *********************//

	// Define todos os pesos deste neurônio aleatoriamente
	// Este método poderá ser chamado na inicialização de uma rede para treinamento
	public void setPeso(){
		
		// Como Math.random sempre retorna valores positivos, é preciso acrescentar mais uma camada de aleatoriedade para variar o sinal
		for(int i = 0; i < this.neuroniosCamadaAnt; i++)
			double peso = Math.random(); // gera um número entre 0 e 0.999...
			if(peso > 0.5)
				this.peso[i] = peso;
			else
				this.peso[i] = peso * (-1);
	
	}

	// Altera o peso da sinapse entre este neurônio e o neurônio N da camada anterior
	public void setPeso(int N, double peso){
		this.peso[N] = peso;
	}
	
	// Retorna o peso da sinapse entre este neurônio e o neurônio N da camada anterior
	public double getPeso(int N){
		return this.peso[N];
	}
	
	
	//************* Funções de ativação e derivada *********************//
	
	// Executa a função de ativação bipolar sigmóide do neurônio genérico
	// Recebe o valor de entrada dado ao neurônio e retorna o valor de saída
	// A saída deverá variar entre -1 e 1
	public static double ativacao(double entrada){
		return ((2/(1 + Math.exp(entrada))) - 1);
	}
	
	// Executa a derivada da função de ativação bipolar sigmóide
	// Esta derivação se vale de uma simplificação apresentada por Laurene Fausett no livro "Fundamentals of Neural Networks" 
	public static double derivada(double entrada){
		double fAtivacao = Neuronio.ativacao(entrada);
		return (1/2)*(1+fAtivacao)*(1-fAtivacao);
	}

}
