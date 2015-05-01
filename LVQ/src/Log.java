
public class Log {
	public void criaHead(String nomeArquivoLog){
		
	}
	
	public void criaHeadRedesNeurais(String nomeArquivo){
		String[] head = new String[4];
		head[0] = "Epoca atual:,";
		head[1] = "Taxa de aprendizado inicial:,";
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
	
}
