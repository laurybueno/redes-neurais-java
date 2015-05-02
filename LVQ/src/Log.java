
public class Log {
	public void criaHead(String nomeArquivoLog){
		criaHeadRedesNeurais(nomeArquivoLog+"Redes.csv");
		criaHeadValidacao(nomeArquivoLog+"Validacao.csv");
	}
	
	public void criaHeadRedesNeurais(String nomeArquivo){
		String[] head = new String[4];
		head[0] = "Epoca atual:,";
		head[1] = "Taxa de reducao do aprendizado:,";
		head[2] = "Taxa de aprendizado atual,";
		head[3] = "Quantidade epocas reduzir aprendizado:,";
		
		Output grava = new Output();
		grava.escreveArquivo(nomeArquivo, head, false);
	}
	
	public void criaHeadValidacao(String nomeArquivo){
		String[] head = new String[2];
		head[0] = "Epoca atual:,";
		head[1] = "Taxa de erro:,";
		
		Output grava = new Output();
		grava.escreveArquivo(nomeArquivo, head, false);
	}
	
	public void escreveLogRedes(int epocaAtual, double taxaReducaoAprendizado, double taxaAprendizadoAtual, double quantidadeReduzir, double[][] pesos, String nomeArquivo){
		String[] linha = new String[4+pesos.length*pesos[0].length];
		linha[0] = String.valueOf(epocaAtual)+",";
		linha[1] = String.valueOf(taxaReducaoAprendizado)+",";
		linha[2] = String.valueOf(taxaAprendizadoAtual)+",";
		linha[3] = String.valueOf(quantidadeReduzir)+",";
		
		int aux= 4;
		for (int i=0;i<pesos.length;i++){
			for (int j=0;j<pesos[i].length;j++){
				linha[aux]=String.valueOf(pesos[i][j])+",";
				aux++;
			}
		}
		
		Output grava = new Output();
		grava.escreveArquivo(nomeArquivo+"Redes.csv", linha, true);
	}
	
	public void escreveMatrizConfusao(int[][] matrizConfusao, String nomeArquivo){
		Output grava = new Output();
		String[] linha = new String[1+matrizConfusao[0].length];
		for(int i=0;i<matrizConfusao.length;i++){
			linha[i+1]=String.valueOf(i);
		}
		
		grava.escreveArquivo(nomeArquivo, linha, true);
		
		for (int i=0;i<matrizConfusao.length;i++){
			linha[0]=String.valueOf(i)+",";
			for (int j=0;j<matrizConfusao[i].length;j++){
				linha[j+1]=String.valueOf(matrizConfusao[j])+",";//pega o valor do elemento da matriz
			}
			grava.escreveArquivo(nomeArquivo, linha, true);
		}
		
	}
}
