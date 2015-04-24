
public class LVQ {
	
	double [][] vetoresDePesos; //matriz que carregas os vetores de pesos (unidade de saida)
	double [][] dadosEntrada; //matriz de dados de Entrada (podendo ser de: Treinamento, Teste ou Validacao)
	int numeroIteracoes; //contador de Iteracoes(Epocas)
	int numeroFixo; //numero que ira restringir ate que Epoca a LVQ pode chegar
	double taxaDeAprendizado; //taxa de Aprendizado
	double reducaoAprendizado; //valor que reduz a taxa de Aprendizado
	double valorMinimo; //valorMinimo que a taxa de aprendizado pode chegar
	CondicaoParada parada = new CondicaoParada();
	
	//Construtor de inicializacao do LVQ que recebe como parametro um objeto Inicializa que inicializa os pesos e os dados de Entrada do LVQ.
	//Além de receber por parametros dados destinados a serem definidos pelo Usuario.
	public LVQ( Inicializa inicializa, int numeroFixo, double taxaDeAprendizado, double reducaoAprendizado, double valorMinimo){
		
		//Declaracoes dos dados principais - Inicio	
		
		//metodo que inicializa os vetores de pesos -via primeira entrada,
		//e inicializa a matriz de dados de Entrada.
		inicializa.PesosNulos();
		
		//Declaracoes dos dados principais - Fim
		
		this.dadosEntrada = inicializa.dadosEntrada;
		this.vetoresDePesos = inicializa.vetoresDePesos;
		
		//Dados passados pelo Usuario - Inicio
		
		this.numeroFixo = numeroFixo;
		this.taxaDeAprendizado = taxaDeAprendizado;
		this.reducaoAprendizado = reducaoAprendizado;
		this.valorMinimo = valorMinimo;
		
		//Dados passados pelo Usuario - Fim
	}
	
	//Espaco para criacao do metodo que realiza o aprendizado da LVQ.
	public void Aprendizado(){
		
		this.numeroIteracoes = 1; //inicializar do contador de Epocas(iteracoes)
		//TODO condicao do while temporaria, ainda falta verifica de parada
		boolean testa = parada.NumeroFixo(numeroIteracoes, numeroFixo);
		while(!(testa)){//enquanto não houver uma condicao de parada. Continua a realizar a Epoca
			
			Treinamento treina = new Treinamento(this); // cria objeto que executara o treino, passando o LVQ como parametro
			
			treina.Epoca(); //realiza uma epoca
			
			this.vetoresDePesos = treina.lvq.vetoresDePesos; //atualiza valores das unidadesDeSaida para o LVQ.
			
			this.AtualizaAprendizado();//reduz taxa de aprendizado
			
			this.numeroIteracoes++;
			testa = parada.NumeroFixo(numeroIteracoes, numeroFixo);
		}
	}
	
	//Espaco para criacao do metodo que realiza a validacao
	public void Validacao(){
		
	}
	
	//Espaco para criacao do metodo que realiza os testes.
	public void Teste(){
		
	}
	
	//Metodo responsavel por reduzir a taxa de aprendizado por um valor delimitado (reducaoAprendizado)
	public void AtualizaAprendizado(){
			this.taxaDeAprendizado= this.taxaDeAprendizado - this.reducaoAprendizado; //reduz taxa de aprendizado
	}
	
	//Classe suporte ao Aprendizado com alguns metodos essenciais na verificacao de condicao de parada do aprendizado
	class CondicaoParada{
		
		//metodo de condicao de parada, restingindo pelo numero de interacoes
		//recebe numero de interacoes ocorrida(numeroInteracoes) e numero maximo permitido(numeroFixo)
		//caso deva ocorrer a parada return true, caso contrario false.
		public boolean NumeroFixo(int numeroInteracoes,int numeroFixo){
			if(numeroFixo < numeroInteracoes)
				return true;
			return false;
		}
		
		//metodo de condicao de parada, restingindo pela taxa de Aprendizado 
		//recebe um double da taxa de Aprendizado e numero minimo permitida(valorMinimo)
		//caso deva ocorrer a parada return true, caso contrario false.
		public boolean ValorMinimo(double taxaDeAprendizado, double valorMinimo){
			if(valorMinimo < taxaDeAprendizado)
				return true;
			return false;
		}
	}
}


