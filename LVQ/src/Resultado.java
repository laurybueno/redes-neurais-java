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
	
	//funcao para calcular a taxa de verdadeiro positivo seguindo a equacao vp/(vp+fn)
	// verdadeiro positivo eh o elemento [classe][classe] da matriz
	// falso negativo sao todos os elementos que nao estao nem na linha nem na coluna da classe
	public double taxaVerdadeiroPositivo(int[][] matriz, int classe){
		int vp = matriz[classe][classe]; //verdadeiro positivo
		int fn =0; //falso negativo
		for (int i=0;i<matriz.length;i++){
			for (int j=0;j<matriz[i].length;i++){
				if(i==classe || j==classe){//se esta na linha ou na coluna da classe, nao faz nada
					//nao faz nada
				}
				else{ //se nao adiciona o elemento nos falsos negativos
					fn = matriz[i][j] +fn;
				}
			}
		}
		
		double taxa = vp/(vp+fn);
		return taxa;
	}
}
