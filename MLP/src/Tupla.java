
public class Tupla {
	
	/* 
	 * Esta clase representa cada tupla de trazida dos dados e 
	 * ajuda outras classes em sua manipulação.
	 */
	
	private double[] dado;
	private int classe;
	
	private int posicao_atual;
	
	// construtor recebe e copia para sua instância a classe e todos os dados da tupla dada
	public Tupla(double [] dado, int classe){
		this.classe = classe;
		this.dado = dado.clone();
		this.posicao_atual = 0;
	}
	
	// retorna o próximo valor não lido desta tupla
	public double valor(){
		int ret = this.posicao_atual;
		this.posicao_atual++;
		return this.dado[ret];
	}
	
	// retorna a classe desta tupla
	public int classe(){
		return this.classe;
	}
	
	// relata para uma classe externa se ainda há atributos para serem lidos nesta tupla
	public boolean haProx(){
		return this.posicao_atual < dado.length;
	}
	
	// prepara a leitura de atributos da tupla para uma nova rodada 
	public void reset(){
		this.posicao_atual = 0;
	}
	
}
