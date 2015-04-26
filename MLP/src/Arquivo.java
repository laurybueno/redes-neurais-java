import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

	/*
	public double[][] csvToDouble(String arq) {
		
		BufferedReader br = null;
		
		String linhaAtual;
		
		double[][] dados = null;
		
		
		try {
			br = new BufferedReader(new FileReader(arq));
			
			// A primeira linha do arquivo est� no formato "w;x;y;z", onde w = tamanho da camada escondida, x = tamanho da camada de sa�da, y = tamanho peso camada escondida, z = tamanho peso camada de sa�da
			linhaAtual = br.readLine();
			String[] tamanhos = linhaAtual.split(";");
			
			int tamanhoCamadaEscondida = Integer.parseInt(tamanhos[0]);
			int tamanhoCamadaSaida = Integer.parseInt(tamanhos[1]);
			int tamanhoPesoEscondida = Integer.parseInt(tamanhos[2]);
			int tamanhoPesoSaida = Integer.parseInt(tamanhos[3]);
			
			// Cria a vari�vel dados[x][y], onde x � o tamanho da camada escondida + tamanho da camada sa�da + 1 da linha dos vieses
			// y � o o valor m�ximo entre o n�mero de pesos da camada escondida e o da camada de sa�da
			dados = new double[tamanhoCamadaEscondida + tamanhoCamadaSaida + 1][Math.max(tamanhoPesoEscondida, tamanhoPesoSaida)];
			
			// A primeira linha da vari�vel dados cont�m os tamanhos e pesos
			dados[0][0] = tamanhoCamadaEscondida;
			dados[0][1] = tamanhoCamadaSaida;
			dados[0][2] = tamanhoPesoEscondida;
			dados[0][3] = tamanhoPesoSaida;
			
			// A segunda linha do arquivo � referente aos vieses
			linhaAtual = br.readLine();
			String[] vieses = linhaAtual.split(";");
			
			// A segunda linha da vari�vel dados cont�m os vieses
			dados[1][0] = Double.parseDouble(vieses[0]);
			dados[1][1] = Double.parseDouble(vieses[1]);
			
			// As pr�ximas linhas do arquivo s�o referentes aos neur�nios e seus pesos
			int indiceDados = 2;
			while((linhaAtual = br.readLine()) != null) {
				String[] pesos = linhaAtual.split(";");
				for(int i = 0; i < pesos.length; i++) {
					dados[indiceDados][i] = Double.parseDouble(pesos[i]);
					indiceDados++;
				}
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
		
		return dados;
	} */
}
