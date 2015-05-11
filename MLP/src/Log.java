import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;


public class Log {
	
	List<String> dados = new LinkedList<String>();
	private String nomeArquivo;
	private String diretorio;
	private String extensao = ".csv";
	
	public void addDados(int epocaAtual, double taxaRedAprendizado, double taxaAprendizadoAtual, int epocasReduzidas) {
		// As strings já são formatadas no formato do .csv, utilizando ";" como separador
		String linha = epocaAtual + ";" + "taxaRedAprendizado" + ";" + taxaAprendizadoAtual + ";" + epocasReduzidas;
		dados.add(linha);
	}
	
	public void limparDados() {
		dados.clear();
	}
	
	public void setNomeArquivo(String nome) {
		nomeArquivo = nome;
	}
	
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	
	public void setDiretorio(String d) {
		diretorio = d;
	}
	
	public String getDiretorio() {
		return diretorio;
	}

	public void gravaArquivo() {
		try {
			FileWriter arq = new FileWriter(diretorio + nomeArquivo + extensao);
			PrintWriter gravarArq = new PrintWriter(arq);
			Iterator<String> it = dados.iterator();
			while(it.hasNext()) {
				gravarArq.println(it.next()); // percorre toda a lista de dados, já devidamente formatada, e adiciona linha por linha
			}
			gravarArq.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Após os dados serem gravados, a lista é esvaziada
		limparDados();
	}
}
