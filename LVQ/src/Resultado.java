//Classe para fazer a analise do resultado
public class Resultado {
	public int[][] CriaMatrizConfusao(){
		return new int[10][10];
	}
	
	//funcao para adicionar um em um determinado local da matriz de confusao.
	//recebe a matriz de confusao e o local onde deve ser adicionado(linha e coluna) e retorna a nova matriz de confusao
	public int[][] adicionaMatriz(int[][] matriz, int classeReal, int classeObservada ){
		matriz[classeReal][classeObservada]++;
		return matriz;
	}
	
	//funcao para calcular a quantidade de verdadeiros positivos
	// recebe como parametro a matriz de confuaso e a classe que se quer saber quantos acertos teve
	public int verdadeiroPositivo(int classe, int[][] confusao){
		return confusao[classe][classe];//retorna o unico elemento em que acertou
	}
	
	//funcao para calcular a quantidade de falso positivo
	//recebe como parametro a amtriz de confusao e a classe em que se quer saber a quantidade de falsos positivos
	public int falsoPositivo(int classe, int[][] confusao){
		int contador =0;
		for(int i=0;i<confusao.length;i++){//percorre todas as linhas da matriz de confusao
			if(i!=classe){
				contador=contador+ confusao[i][classe];//soma todas os elementos da coluna da classe que ele falou ser da classe, apesar de ser de outra
			}
		}
		return contador;
	}
	
	//funcao para calcular a quantidade de falso negativo
	//recebe como parametro a matriz de confusao e a classe em que se quer saber a quantidade de falsos negativos
	public int falsoNegativo(int classe, int[][] confusao){
		int contador=0;
		for(int i=0;i<confusao[classe].length;i++){//percorre todas as colunas da matriz de confusao
			if(i!=classe){
				contador=contador+ confusao[classe][i];
			}
			
		}
		
		return contador;
	}
	
	//funcao para calcular a quantidade de verdadeiros negativos
	// recebe como parametro a classe a qual se quer saber a quantidade de verdadeiros negativos e a matriz de confusao.
	public int verdadeiroNegativo(int classe, int[][]confusao){
		int contador=0;
		for(int i=0;i<confusao.length;i++){
			for(int j=0;j<confusao[i].length;i++){
				if (i!=classe && j!=classe){
					contador=contador + confusao[i][j]; //soma todos os elementos que nao sao linha nem coluna da classe
				}
			}
		}
		return contador;
	}
	
	//funcao para calcular a taxa de verdadeiro positivo seguindo a equacao vp/(vp+fn)
	public double taxaVerdadeiroPositivo(int[][] matriz, int classe){
		int vp = verdadeiroPositivo(classe, matriz); //verdadeiro positivo
		int fn = falsoNegativo(classe, matriz); //falso negativo
		double taxa = vp/(vp+fn);
		return taxa;
	}
	
	//funcao para calcular a taxa de falso positivo seguindo a equacao fp/(vn+fp)
	public double taxaFalsoPositivo(int[][] matriz, int classe){
		int fp = falsoPositivo(classe, matriz);
		int vn = verdadeiroNegativo(classe, matriz);
		double taxa = fp/(vn+fp);
		return taxa;
	}
	
	//funcao para calcular a taxa de verdadeiro negativo seguindo a equacao vn/(fp + vn)
	public double taxaVerdadeiroNegativo(int[][] matriz, int classe){
		int vn = verdadeiroNegativo(classe, matriz);
		int fp = falsoPositivo(classe, matriz);
		double taxa = vn/(fp + vn);
		return taxa;
	}
	
	//funcao para calcular a precisao seguindo a equacao vn/(fp + vn)
	public double precisao(int[][] matriz, int classe){
		int vp = verdadeiroPositivo(classe, matriz);
		int fp = falsoPositivo(classe, matriz);
		double taxa = vp/(vp+fp);
		return taxa;
	}
	
	//funcao para calcular proporcao de rejeicoes corretas dentre os exemplos preditos como negativos
	public double pedritividadeNegativa(int[][] matriz, int classe){
		int vn = verdadeiroNegativo(classe, matriz);
		int fn = falsoNegativo(classe, matriz);
		double taxa = vn/(vn+fn);
		return taxa;
	}
	
	//funcao para calcular o numeor de falsos positivos dentre os exemplos classificados como positivos
	public double falsaDescoberta(int[][] matriz, int classe){
		int fp = falsoPositivo(classe, matriz);
		int vp = verdadeiroPositivo(classe, matriz);
		double taxa = fp/(vp+fp);
		return taxa;
	}
	
	//funcao para calcular a quantiadde total de verdadeiros positivos(diagonal principal)
	public int acuracia(int[][] matriz){
		int cont =0;
		for(int i=0;i<matriz.length;i++){
			cont=cont+matriz[i][i];
		}
		return cont;
	}
	
	//funcao para retornar a quantidade de exemplos classificados incorretamente
	// na verdade eh o total menos os acertados
	public int erro(int[][] matriz){
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
}
