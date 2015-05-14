import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;


public class Log {
	
	List<String> dados = new LinkedList<String>();
	private String nomeArquivo;
	private String diretorio;
	private String extensao = ".csv";
	
	// construtor prepara a pŕimeira linha do CSV a ser gravado
	public Log(){
		String linha = "epocaAtual;desempenho;taxaAprendizadoAtual;idRede";
		dados.add(linha);
		diretorio = "";
	}
	
	public void addDados(int epocaAtual, double desempenho, double taxaAprendizadoAtual, String idRede) {
		// As strings já são formatadas no formato do .csv, utilizando ";" como separador
		String linha = epocaAtual + ";" + desempenho + ";" + taxaAprendizadoAtual + ";" + idRede;
		dados.add(linha);
	}
	
	// sobrecarga de addDados torna campo idRede não-obrigatório para o usuário desta classe
	public void addDados(int epocaAtual, double desempenho, double taxaAprendizadoAtual){
		addDados(epocaAtual, desempenho, taxaAprendizadoAtual,"");
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
