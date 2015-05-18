import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Arquivo {
	
	// O arquivo passado por parâmetro é lido linha por linha, sendo que cada uma é inserida
	// numa lista. Após todas as linhas terem sido inseridas, elas são divididas uma por uma
	// pegando-se os valores entre as vírgulas. Esses valores são, por fim, inseridos na matriz que será retornada.
	public static double[][] csvToDouble(String caminho) {
		
		BufferedReader br = null;
		String linhaAtual;
		double[][] dados = null; // matriz que será retornada
		int numLinhas = 0; // necessário para a criação da matriz
		int numColunas = 0; // necessário para a criação da matriz
		List<String> linhas = new ArrayList<String>(); // lista de linhas do arquivo
		
		/* Linhas são inseridas uma por uma na lista, além de contar quantas foram inseridas no total.
		 * Esse contador será necessário para especificar o tamanho da matriz de dados */
		try {
			br = new BufferedReader(new FileReader(caminho));
			while((linhaAtual = br.readLine()) != null) {
				linhas.add(linhaAtual);
				numLinhas++;
			}		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(br != null) br.close(); // fecha o arquivo
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		// Obtém o número de colunas através da primeira linha do arquivo
		String[] colunas = linhas.get(0).split(",");
		numColunas = colunas.length;
		
		// O número de linhas e colunas do arquivo agora são conhecidos, permitindo instanciar a matriz de dados
		dados = new double[numLinhas][numColunas];
		
		/* Agora todas as linhas do arquivo estão na lista linhas.
		 * Elas são dividas uma por uma e seus valores postos na matriz. */
		int indiceDados = 0;
		Iterator<String> it = linhas.iterator();
		while(it.hasNext()) {
			String[] atributos = it.next().split(",");
			for(int i = 0; i < atributos.length; i++) {
				dados[indiceDados][i] = Double.parseDouble(atributos[i]);
			}
			indiceDados++;
		}
		
		return dados;
	}

	public void gravaMatrizConfusao(int[][] matriz, String nomeArquivo) {
		FileWriter arq;
		try {
			arq = new FileWriter(nomeArquivo + ".csv");
			PrintWriter gravarArq = new PrintWriter(arq);
			// Preenche a primeira linha do arquivo com os n�meros 0...9
			gravarArq.println("null;0;1;2;3;4;5;6;7;8;9");
			for(int i = 0; i < 10; i++) {
				gravarArq.print(i); // a primeira coluna � fixa, com os n�meros 0...9
				for(int j = 0; j < 10; j++) {
					gravarArq.print(";" + matriz[i][j]);
				}
				gravarArq.println(); // pula para a pr�xima linha
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
