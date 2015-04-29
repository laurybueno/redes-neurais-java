//Classe para fazer a analise do resultado
public class MatrizConfusao {
	private int[][] matriz;
	public MatrizConfusao(){
		this.matriz = new int[10][10];
	}
	
	
	//funcao para adicionar um em um determinado local da matriz de confusao.
	//recebe a matriz de confusao e o local onde deve ser adicionado(linha e coluna) e retorna a nova matriz de confusao
	public void adicionaMatriz(int classeReal, int classeObservada ){
		matriz[classeReal][classeObservada]++;
	}
	
	//funcao para calcular a quantidade de verdadeiros positivos
	// recebe como parametro a matriz de confuaso e a classe que se quer saber quantos acertos teve
	public int verdadeiroPositivo(int classe){
		return matriz[classe][classe];//retorna o unico elemento em que acertou
	}
	
	//funcao para calcular a quantidade de falso positivo
	//recebe como parametro a amtriz de confusao e a classe em que se quer saber a quantidade de falsos positivos
	public int falsoPositivo(int classe){
		int contador =0;
		for(int i=0;i<matriz.length;i++){//percorre todas as linhas da matriz de confusao
			if(i!=classe){
				contador=contador+ matriz[i][classe];//soma todas os elementos da coluna da classe que ele falou ser da classe, apesar de ser de outra
			}
		}
		return contador;
	}
	
	//funcao para calcular a quantidade de falso negativo
	//recebe como parametro a matriz de confusao e a classe em que se quer saber a quantidade de falsos negativos
	public int falsoNegativo(int classe){
		int contador=0;
		for(int i=0;i<matriz[classe].length;i++){//percorre todas as colunas da matriz de confusao
			if(i!=classe){
				contador=contador+ matriz[classe][i];
			}
			
		}
		
		return contador;
	}
	
	//funcao para calcular a quantidade de verdadeiros negativos
	// recebe como parametro a classe a qual se quer saber a quantidade de verdadeiros negativos e a matriz de confusao.
	public int verdadeiroNegativo(int classe){
		int contador=0;
		for(int i=0;i<matriz.length;i++){
			for(int j=0;j<matriz[i].length;i++){
				if (i!=classe && j!=classe){
					contador=contador + matriz[i][j]; //soma todos os elementos que nao sao linha nem coluna da classe
				}
			}
		}
		return contador;
	}
	
	//funcao para calcular a taxa de verdadeiro positivo seguindo a equacao vp/(vp+fn)
	public double taxaVerdadeiroPositivo( int classe){
		int vp = verdadeiroPositivo(classe); //verdadeiro positivo
		int fn = falsoNegativo(classe); //falso negativo
		double taxa = vp/(vp+fn);
		return taxa;
	}
	
	//funcao para calcular a taxa de falso positivo seguindo a equacao fp/(vn+fp)
	public double taxaFalsoPositivo( int classe){
		int fp = falsoPositivo(classe);
		int vn = verdadeiroNegativo(classe);
		double taxa = fp/(vn+fp);
		return taxa;
	}
	
	//funcao para calcular a taxa de verdadeiro negativo seguindo a equacao vn/(fp + vn)
	public double taxaVerdadeiroNegativo( int classe){
		int vn = verdadeiroNegativo(classe);
		int fp = falsoPositivo(classe);
		double taxa = vn/(fp + vn);
		return taxa;
	}
	
	//funcao para calcular a precisao seguindo a equacao vn/(fp + vn)
	public double precisao(int classe){
		int vp = verdadeiroPositivo(classe);
		int fp = falsoPositivo(classe);
		double taxa = vp/(vp+fp);
		return taxa;
	}
	
	//funcao para calcular proporcao de rejeicoes corretas dentre os exemplos preditos como negativos
	public double pedritividadeNegativa( int classe){
		int vn = verdadeiroNegativo(classe);
		int fn = falsoNegativo(classe);
		double taxa = vn/(vn+fn);
		return taxa;
	}
	
	//funcao para calcular o numeor de falsos positivos dentre os exemplos classificados como positivos
	public double falsaDescoberta(int classe){
		int fp = falsoPositivo(classe);
		int vp = verdadeiroPositivo(classe);
		double taxa = fp/(vp+fp);
		return taxa;
	}
	
	//funcao para calcular a quantiadde total de verdadeiros positivos(diagonal principal)
	public int acuracia(){
		int cont =0;
		for(int i=0;i<matriz.length;i++){
			cont=cont+matriz[i][i];
		}
		return cont;
	}
	
	
	//funcao para retornar a quantidade de exemplos classificados incorretamente
	// na verdade eh o total menos os acertados
	public int erro(){
		int cont =0;
		for(int i=0;i<matriz.length;i++){
			for (int j=0;j<matriz[i].length;j++){
				if(i!=j){
					cont=cont+matriz[i][j];
				}
			}
		}
		return cont;
	}
	
	//funcao para calcular o indice de kappa
	double indiceKappa(){
		int diagonalPrincipal = acuracia();//somatoria da diagonal principal
		int n = totalAmostras();//total de amostras
		int numeroClasses = matriz.length;
		int somaProdutosLinhasColunas = somaProdutos();
		
		int partedecima = n *diagonalPrincipal - somaProdutosLinhasColunas;
		int partedebaixo = n*n - somaProdutosLinhasColunas;
		double kappa = partedecima/partedebaixo;
		
		return kappa;
		
	}
	
	//funcao para calcular a soma do produto da soma entre colunas e linhas (linha i * coluna i + linha n *coluna....)
	//ela eh usada para calcular o indice de kappa
	int somaProdutos(){
		int[] linhas=somaLinhas();
		int[] colunas = somaColunas();
		int produto =0;
		for(int i=0;i<linhas.length;i++){
			int aux= linhas[i]*colunas[i];
			produto=produto + aux;
		}
		return produto;
	}
	
	//retorna a soma de cada linha
	int[] somaLinhas(){
		int[] soma = new int[matriz.length];
		for(int i=0;i<matriz.length;i++){
			for(int j=0;j<matriz[i].length;j++){
				soma[i] = soma[i] +matriz[i][j];
			}
		}
		return soma;
	}
	
	int[] somaColunas(){
		int[] soma = new int[matriz[0].length];
		for(int i=0;i<matriz.length;i++){
			for(int j=0;j<matriz[i].length;j++){
				soma[j]=soma[j]+matriz[i][j];
			}
		}
		return soma;
	}
	
	int totalAmostras(){
		int cont=0;
		for(int i=0;i<matriz.length;i++){
			for (int j=0; j<matriz[i].length;j++){
				cont=cont+matriz[i][j];
			}
		}
		return cont;
	}
}
