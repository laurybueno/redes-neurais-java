
public class LVQ {
	
	UnidadeDeSaida [] unidadesDeSaida;
	double [][] dadosEntrada;
	
	public LVQ( Inicializa inicializa){
		
		//Declaracoes dos dados principais - Inicio	
		
		//metodo que inicializa os vetores de pesos -via primeira entrada,
		//e inicializa a matriz de dados de Entrada.
		inicializa.PesosPrimeiraEntrada();
		
		//Declaracoes dos dados principais - Fim
		
		this.dadosEntrada = inicializa.dadosEntrada;
		this.unidadesDeSaida = inicializa.unidadesDeSaida;
	}
	
	//Espaco para criacao do metodo que realiza o aprendizado da LVQ.
	public void Aprendizado(){
		
	}
	
	//Espaco para criacao do metodo que realiza a validacao
	public void Validacao(){
		
	}
	
	//Espaco para criacao do metodo que realiza os testes.
	public void Teste(){
		
	}
	
	//Classe suporte ao Aprendiza com alguns metodos essenciais na verificacao de condicao de parada do aprendizado
	public class CondicaoParada{
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
