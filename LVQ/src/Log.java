
public class Log {
	//chama as funcoes que criam head dos logs
	public void criaHead(String nomeArquivoLog){
		criaHeadRedesNeurais(nomeArquivoLog+"Redes.csv");
		criaHeadAprendizado(nomeArquivoLog+"Aprendizado.csv");
	}
	
	//cria head do log das redes neurais
	public void criaHeadRedesNeurais(String nomeArquivo){
		String[] head = new String[4];
		head[0] = "Epoca atual:,";
		head[1] = "Taxa de reducao do aprendizado:,";
		head[2] = "Taxa de aprendizado atual,";
		head[3] = "Quantidade epocas reduzir aprendizado:,";
		
		Output grava = new Output();
		grava.escreveArquivo(nomeArquivo, head, false);
	}
	
	//cria head do log do aprendizado contendo taxa de erro do treino e da validacao
	public void criaHeadAprendizado(String nomeArquivo){
		String[] head = new String[3];
		head[0] = "Epoca atual:,";
		head[1] = "Taxa de erro sobre Treinamento:,";
		head[2] = "Taxa de erro sobre Validacao:,";
		
		Output grava = new Output();
		grava.escreveArquivo(nomeArquivo, head, false);
	}
	
	
	public void criaheadMediaEpocas(String nomeArquivo){
		String [] head = new String[2];
		head[0] = "Media de Epocas:";
		head[1] = "Media da Taxa de Erro sobre o teste";
		Output grava = new Output();
		grava.escreveArquivo(nomeArquivo, head, false);
	}
	
	//escreve log contendo o vetor de pesos da rede neural
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
	
	//escreve log contendo a taxa de erro do trienamento e da validacao de acordo com o passar das epocas
	public void escreveAprendizado(String nomeArquivo, int epocaAtual, double taxaErroTreinamento, double erroSobreValidacao){
		String[] linha = new String[3];
		linha[0] = String.valueOf(epocaAtual)+",";
		linha[1] = String.valueOf(taxaErroTreinamento)+",";
		linha[2] = String.valueOf(erroSobreValidacao)+",";
		
		Output grava = new Output();
		grava.escreveArquivo(nomeArquivo+"Aprendizado.csv", linha, true);
	}
	
}
