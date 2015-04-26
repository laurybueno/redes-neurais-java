
public class Outlier {
	
	//funcao para encontrar outliers, se um
	public boolean[] outlier (double[][] dados){
		boolean[] outlier = new boolean[dados.length];
		for(int i =0;i<dados.length;i++){ //percorre matriz de dados
			for (int j=0;j<dados[i].length;j++){
				if(dados[i][j]<2.0 && dados[i][j]>-2){//se tiver um elemnto menor que 2 e maior do que -2, o registro nao eh outlier
					break;
				}
				if(j==dados[i].length-1){
					outlier[i]= true;
				}
			}
		}
		return outlier;
	}
}
