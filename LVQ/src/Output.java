import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


public class Output {


	public void escreveArquivo(String nomeArquivo, String[] s) {
		try {
			FileWriter arq = new FileWriter(nomeArquivo);
			PrintWriter writer = new PrintWriter(arq);
			for(int i = 0; i < s.length; i++) {
				writer.println(s[i]);
			}
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
}
