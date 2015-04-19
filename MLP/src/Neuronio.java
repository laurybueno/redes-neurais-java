
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
			if(Math.random() > 0.5)
				this.peso[i] = Math.random();
			else
				this.peso[i] = Math.random()*(-1);
	
	}

	// Altera o peso da sinapse entre este neurônio e o neurônio N da camada anterior
	public void setPeso(int N, double peso){
		this.peso[N] = peso;
	}
	
	// Retorna o peso da sinapse entre este neurônio e o neurônio N da camada anterior
	public double getPeso(int N){
		return this.peso[N];
	}
	
	
	//************* Controle de feedforward *********************//
	// esse método dá apenas um passo na operação de feedForward, ou seja, ele passa da entrada para a camadaEscondida, ou da escondida para a saída
	public double feedForward(Tupla entrada, double vies){
		
		double soma = 0;
		int peso_ativo = 0;
		
		// prepara o somatório
		while(entrada.haProx()){
			soma += entrada.valor()*this.peso[peso_ativo];
			peso_ativo++;
		}
		soma += vies;
		
		return ativacao(soma);
	}
	
	
	//************* Funções de ativação e derivada *********************//
	
	double fAtivacao;
	
	// Executa a função de ativação bipolar sigmóide do neurônio genérico
	// Recebe o valor de entrada dado ao neurônio e retorna o valor de saída
	// A saída deverá variar entre -1 e 1
	public double ativacao(double entrada){
		this.fAtivacao = ((2/(1 + Math.exp(entrada))) - 1);
		return this.fAtivacao;
	}
	
	// Executa a derivada da função de ativação bipolar sigmóide
	// Esta derivação se vale de uma simplificação apresentada por Laurene Fausett no livro "Fundamentals of Neural Networks" 
	public double derivada(double entrada){
		return (1/2)*(1+this.fAtivacao)*(1-this.fAtivacao);
	}

}
