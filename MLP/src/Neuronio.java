
public class Neuronio {
	
	// Define as características de um neurônio genérico na rede neural MLP
	
	
	//************* Formação da instância de neurônio *********************//
	
	// Armazena quantos neurônios existem na camada anterior
	// Essa informação é necessária para que este neurônio saiba quantos pesos deverá armazenar
	double vies;
	double[] peso;
	
	
	
	// No momento de sua criação, cada neurônio saberá quantos neurônios estão na camada anterior 
	public Neuronio(int neuroniosCamadaAnt){
		// prepara o array de pesos
		peso = new double[neuroniosCamadaAnt];
	}
	
	
	//************* Definição de pesos *********************//

	// Define todos os pesos deste neurônio aleatoriamente
	// Este método poderá ser chamado na inicialização de uma rede para treinamento
	public void reset(){
		
		// Como Math.random sempre retorna valores positivos, é preciso acrescentar mais uma camada de aleatoriedade para variar o sinal
		for(int i = 0; i < this.peso.length; i++)
			if(Math.random() > 0.5)
				this.peso[i] = Math.random();
			else
				this.peso[i] = Math.random()*(-1);
		
		// determina o vies
		if(Math.random() > 0.5)
			this.vies = Math.random();
		else
			this.vies = Math.random()*(-1);
	
	
	}

	// Altera o peso da sinapse entre este neurônio e o neurônio N da camada anterior
	public void setPeso(int N, double variacao){
		this.peso[N] += variacao;
	}
	
	// Retorna o peso da sinapse entre este neurônio e o neurônio N da camada anterior
	public double getPeso(int N){
		return this.peso[N];
	}
	
	public void setVies(double vies){
		this.vies = vies;
	}
	
	public double getVies(){
		return this.vies;
	}
	
	//************* Controle de feedforward *********************//
	
	// esse método dá apenas um passo na operação de feedForward, ou seja, ele passa da entrada para a camadaEscondida, ou da escondida para a saída
	public double feedForward(Tupla entrada){
		
		double soma = 0;
		int peso_ativo = 0;
		
		entrada.reset();
			
		// prepara o somatório
		while(entrada.haProx()){
			soma += entrada.valor()*this.peso[peso_ativo];
			peso_ativo++;
		}
		soma += vies;
		return ativacao(soma);
	}
	
	// caso a tupla não tenha sido instanciada externamente, ela será instanciada agora
	public double feedForward(double[] entrada){
		return feedForward(new Tupla(entrada));
	}
	
	
	//************* Funções de ativação e derivada *********************//
	
	double fAtivacao;
	
	// Executa a função de ativação bipolar sigmóide do neurônio genérico
	// Recebe o valor de entrada dado ao neurônio e retorna o valor de saída
	// A saída deverá variar entre -1 e 1
	public double ativacao(double entrada){
		entrada = entrada*(-1);
		this.fAtivacao = ((2/(1 + Math.exp(entrada))) - 1);
		return this.fAtivacao;
	}
	
	// Executa a derivada da função de ativação bipolar sigmóide
	// Esta derivação se vale de uma simplificação apresentada por Laurene Fausett no livro "Fundamentals of Neural Networks" 
	public double derivada(){
		return ((1+this.fAtivacao)*(1-this.fAtivacao))/2;
	}
	
	//************* Funções de mapeamento e desempenho *********************//
	
	/* Cria um cósigo hash para a instância atual de neurônio
	*  Para tanto, ele multiplica todos os pesos uns pelos outros e retorna o inteiro resultanta.
	*/
	public int hashCode(){
		double produto = vies;
		
		for(int i = 0; i < peso.length; i++){
			produto *= peso[i];
		}
		
		// Passa seis casas decimais do produto para a parte inteira do número.
		// Dessa forma, melhoram as chances de o intValue retornar um valor único para essa combinação de pesos.
		produto *= 1000000;
		
		Double ret = new Double(produto);
		
		return ret.intValue();
	}
	
	// Guarda informações que serão usadas para calcular o erro quadrado da instância de Neuronio
	
	double tk; // target pattern que o neurônio deveria ter alcançado no feedForward
	
	// erroQuadrado só será computado para neurônios de saída
	public double erroQuadrado(){
		return Math.abs(tk - fAtivacao);
	}
	
	

}
