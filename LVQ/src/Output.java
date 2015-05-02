import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;


public class Output {


	public void escreveArquivo(String nomeArquivo, String[] s, boolean append) {
		try {
			FileWriter arq = new FileWriter(nomeArquivo,append);
			PrintWriter writer = new PrintWriter(arq);
			for(int i = 0; i < s.length; i++) {
				writer.print(s[i]);
			}
			writer.println();
			arq.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			System.err.println(e);
		}
		
	}
	
	//funcao para criar um arquivo contendo os registros de cada classe
	public void separaClasses(List[] classes){
		System.out.println("entrou");
		for(int i=0;i<classes.length;i++){
			String [] registros = (String[]) classes[i].toArray(new String[classes[i].size()]);// transforma a lista em um arranjo de String
			escreveArquivo("registros"+i+".csv", registros, true);
		}
	}
}
