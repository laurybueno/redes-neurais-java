
public class Tupla {
	
	/* 
	 * Esta clase representa cada tupla de trazida dos dados e 
	 * ajuda outras classes em sua manipulação.
	 */
	
	private double[] dado;
	private int classe;
	
	private int posicao_atual;
	
	// construtor recebe e copia para sua instância todos os dados da tupla dada e sua classe, quando disponível
	public Tupla(double [] dado, int classe){
		this.classe = classe;
		this.dado = dado.clone();
		this.posicao_atual = 0;
	}
	
	// quando a classe não estiver disponível, e portanto a rede estiver em modo de execução, este construtor é chamado
	public Tupla(double[] dado){
		this.classe = -1;
		this.dado = dado.clone();
		this.posicao_atual = 0;		
	}
	
	// retorna o próximo valor não lido desta tupla
	public double valor(){
		int ret = this.posicao_atual;
		this.posicao_atual++;
		return this.dado[ret];
	}
	
	// retorna um valor específico desta tupla
	public double valor(int n){
		return this.dado[n];
	}

	// retorna a classe desta tupla
	public int classe(){
		return this.classe;
	}
	
	// retorna todos os dados para clonagem
	public double[] valores(){
		return this.dado.clone();
	}
	
	// relata para uma classe externa se ainda há atributos para serem lidos nesta tupla
	public boolean haProx(){
		return this.posicao_atual < dado.length;
	}
	
	// prepara a leitura de atributos da tupla para uma nova rodada 
	public void reset(){
		this.posicao_atual = 0;
	}
	
	// clona a tupla
	public Tupla clonar(){
		return new Tupla(this.valores(),this.classe());
	}
	
	
}
