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
		DateFormat dataFormatada = new SimpleDateFormat("dd-MM-yyyy HHhmm");
		Date data =new Date();
		this.toFile(rede.toString(), "Rede at " + dataFormatada.format(data), "csv");
	}

}
