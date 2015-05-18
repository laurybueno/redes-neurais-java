import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Output implements Runnable {
	
	private Rede rede;
	
	public Output(Rede r) {
		rede = r;
	}
	
	// FunÁ„o que cria um arquivo a partir de uma string de conte˙do
	public void toFile(String conteudo, String nome, String ext) {
		try {
			PrintWriter arq = new PrintWriter(nome + "." + ext, "UTF-8");
			arq.println(conteudo);
			arq.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		
		DateFormat dataFormatada;
		Date data;
		
		
		// a thread guardi√£ fica constantemente em execu√ß√£o e tenta ler par√¢metros da rede de tempos em tempos
		while(true){
			dataFormatada = new SimpleDateFormat("dd-MM-yyyy HHhmm");
			data = new Date();
			this.toFile(rede.toString(), "Rede at " + dataFormatada.format(data), "csv"); // cria o arquivo .csv, contendo a data e hora da criaÁ„o
			
			try {
				Thread.sleep(30000); // pausa esta thread por 30 segundos
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt(); // aceita a interrup√ß√£o e sai da pilha de execu√ß√£o
			}
			
		}
	}

}
