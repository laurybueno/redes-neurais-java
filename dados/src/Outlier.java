import java.util.Arrays;

public class Outlier {
	/*public boolean outliernoarquivo(String nomearquivo){
		Input im = new Input();
		double[][] normal = im.arquivoToMatrizDouble(nomearquivo);
		boolean outliers[] = outlier(normal);
		for(int i=0;i<outliers.length;i++){
		//	System.out.println(i+" "+outliers[i]);
		}
		return false;
	}*/
	
	//funcao para encontrar outliers, se um ponto eh maior que 250% do desvio padrao, entao ele eh outlier
	public double[] outlier (double[][] dados){
		double[] outlier = new double[dados.length];
		double[] variancia = new double[dados[0].length];
		double[] mediana = mediana(dados);
		for(int i=0;i<variancia.length;i++){
			double mediaColuna = mediaColuna(dados, i);
			variancia[i]=varianciaColuna(dados, i, mediaColuna);
		}
		
		for(int i=0;i<dados.length;i++){
			for (int j=0;j<dados[i].length;j++){
				double variouMuito = mediana[j] + (variancia[j]*2.5);
				if(variouMuito>dados[i][j] && (variouMuito>dados[i][j]*-1)){//esse ponto nao eh outlier
					break;
				}
				if(j==dados[i].length-1){
					outlier[i]=dados[i][j];
					System.out.println(outlier[i]);
				}
			}
		}
		return outlier;
	}
	
	public double varianciaColuna(double[][] dados, int coluna, double mediaColuna){
		int quantidadeElementos = dados.length;
		double variancia =0;
		for(int i=0;i<dados.length;i++){
			variancia = variancia + Math.pow((dados[i][coluna]- mediaColuna), 2);
		}
		variancia = variancia/quantidadeElementos;
		return variancia;
	}
	
	public double mediaColuna(double[][] dados, int coluna){
		int quantidadeLinhas = dados.length;
		double somaTotalColuna =0;
		for (int i=0;i<dados.length;i++){
			somaTotalColuna = somaTotalColuna + dados[i][coluna];
		}
		double media = somaTotalColuna/quantidadeLinhas;
		return media;
	}
	
	//funcao pra calcular a mediana de cada coluna
	public double[] mediana (double[][] dados){
		
		double[] mediana = new double[dados[0].length];
		double[][] inversa = new double[dados[0].length][dados.length];
		for (int i=0;i<dados.length;i++){
			for (int j=0;j<dados[i].length;j++){
				inversa[j][i]=dados[i][j];
			}
		}
		for(int i=0;i<inversa.length;i++){
			for (int j=0;j<inversa[i].length;j++){
				//System.out.print(inversa[i][j]+" ");
			}
			//System.out.println();
		}
		//ordena as colunas
		for(int i=0;i<dados[0].length;i++){
			Arrays.sort(inversa[i]);//ta ordena as linhas
		}
		for(int i=0;i<inversa.length;i++){
			for(int j=0;j<inversa[i].length;j++){
		//		System.out.print(inversa[i][j]+" ");
			}
			//System.out.println();
		}
		
		int meio;
		
		for(int i = 0; i < inversa.length; i++) {
			meio = inversa[i].length/2;
			if(meio % 2 == 0) // não há valor central, portanto deve-se somar os dois valores centrais para obter a média
				mediana[i] = (inversa[i][meio] + inversa[i][meio-1])/2;
			else mediana[i] = inversa[i][meio];
		}
		
		return mediana;
	}
	
}
