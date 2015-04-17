import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class Input {
	//abre arquivo (nome no primeiro parametro) e retorna a linha especificada (segunto parametro) desse arquivo
	public String leitura(String nome, int quantidade) {
		String linha = "";
		try{
			File arquivo = new File(nome);
			FileReader fr = new FileReader(arquivo);
			BufferedReader br = new BufferedReader(fr);
			int cont = 0;//auxliar para ver a linha atual
			
			while ((cont <= quantidade) && (br.ready())) {//enquanto a linha atual for menor ou igual a a linha que eh para ser lida e eh possivel ler o arquivo
				linha = br.readLine();//le o arquivo e vai para a proxima linha
				cont++;//soma um no auxiliar para ver a linha atual
			}
			br.close();
			fr.close();
		}
		catch (Exception e){
			System.err.println(e);
		}
		return linha;
	}
	
	//tira as virgulas da string(parametro) e coloca cada atributo em um arranjo de double
	public double[] StringToDouble (String linha){
		String[] semVirgula = linha.split(",");//tira as virgulas e transforma em um arranjo de string
		double[] atributos = new double[semVirgula.length];
		for(int i=0;i<semVirgula.length;i++){//percorre todo o array de String
			atributos[i]= Double.parseDouble(semVirgula[i]);//transforma cada string do aray de string em um double e coloca no array de double
		}
		return atributos;
		
	}
}
