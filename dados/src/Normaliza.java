import javax.swing.text.AbstractDocument.LeafElement;

import sun.security.util.Length;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class Normaliza {
	//funcao para normalizar usando a tecnica minMax
	//recebe como parametro o nome do arquivo em que se quer normalizar
	//escreve os dados normalizados no arquivo normalizadoMinMax
	public void minMax(String nomeArquivoLeitura){
		Input arquivo = new Input();
		double[][] dados = arquivo.arquivoToMatrizDouble(nomeArquivoLeitura);//le arqquivo e passa para uma matriz de double
		double[] minimo = encontraMin(dados);//arranjo contendo o menor numeor de cada coluna respectivamente
		double[] maximo = encontraMax(dados);//arranjo contendo o maior numeor de cada coluna respectivamente
		
		for(int i =0;i<dados.length;i++){
			for(int j=0;j<dados[i].length;j++){
				dados[i][j]=(dados[i][j]-minimo[i])/(maximo[i]-minimo[i]);
			}
		}
		
		
		
		
		double[][] minMax = geraNovaMatriz(dados);

		String[] normalizado = new String[minMax.length];

		for(int i=0;i<minMax.length;i++){
			normalizado[i] = Arrays.toString(minMax[i]);
			normalizado[i] = normalizado[i].substring(1);//tira colchetes do inicio
			normalizado[i] = normalizado[i].substring (0, normalizado[i].length() - 1); //tira colchetes do fim
		}
		//grava no disco
		Output grava = new Output();
		grava.escreveArquivo("normalizadoMinMax.csv", normalizado, false);
		
	}
	
	//funcao para deletar uma coluna especifica
	public double [][] deletaColuna(double[][] dados,int col){
		double [][] novaMatriz;
	    novaMatriz = new double[dados.length][dados[0].length-1];//cria matriz com uma coluna a menos
	    for(int i =0;i<dados.length;i++){ 
	    	int newColIdx = 0;
	        for(int j =0;j<dados[i].length;j++){
	        	if(j!=col){//se nao for a coluna pra deletar, copia ela
	        		novaMatriz[i][newColIdx] = dados[i][j];
	                newColIdx++;
	            }               
	        }
	     }
	     return novaMatriz;
	 }
	
	//funcao para deletar coluna que so tem numeor igual
	public double [][] geraNovaMatriz(double[][] args1){
		double [][] novaMatriz = clonaMatriz(args1);
		boolean parada = true;
		int coluna = 0;
		while (parada && coluna < args1[0].length){
			if(sotem0(args1, coluna)){
				parada = false;
				novaMatriz = deletaColuna(args1, coluna);
				geraNovaMatriz(novaMatriz);
			}
			else{
				coluna++;
			}
		}
		return novaMatriz;
	}
	
	//funcao para copiar matriz
	public static double [][] clonaMatriz(double [][] original){
		double [][] clone = new double[original.length][];
		for(int i =0; i < original.length; i++ ){
			clone[i] = original[i].clone();
		}
		return clone;
	}
		 
	


	//funcao para checar coluna que só tem 0
	//recebe como parametro a matriz e o numeor da coluna
	boolean sotem0(double[][] dados, int coluna){
		for(int i=0;i<dados.length;i++){
			if(dados[i][coluna]!=0){
				return false;
			}
		}
		return true;
	}
	
	
	//funcao que retorna um arranjo contendo o menor numero encontrado em cada coluna respectivamente
	public double[] encontraMin(double[][] dados){
		double[] minimo = new double[dados.length];
		for(int i=0;i<dados.length;i++){//percorre o arranjo colocando o primeiro elemento de cada coluna
			minimo[i] = dados[i][0];
		}
		for(int i=0;i<dados.length;i++){//percorre o arranjo de dados
			for(int j=1;j<dados[i].length;j++){// o primeiro elemtno ja esta no aranjo
				if(dados[i][j]<minimo[i]){
					minimo[i]=dados[i][j];
				}
			}
		}
		return minimo;
	}
	
	//funcao que retorna um arranjo contendo o maior numero encontrado em cada coluna respectivamente
		public double[] encontraMax(double[][] dados){
			double[] maximo = new double[dados.length];
			for(int i=0;i<dados.length;i++){//percorre o arranjo colocando o primeiro elemento de cada coluna
				maximo[i] = dados[i][0];
			}
			for(int i=0;i<dados.length;i++){//percorre o arranjo de dados
				for(int j=1;j<dados[i].length;j++){// o primeiro elemtno ja esta no aranjo
					if(dados[i][j]>maximo[i]){
						maximo[i]=dados[i][j];
					}
				}
			}
			return maximo;
		}
	
	//funcao para fazer a normalizacao usando a tecnica Z score
	//recebe como parametro o nome do arquivo em que se quer normalizar e o nome do arquivo onde vai salvar isso
	//DANGER: essa funcao nao normaliza a ultima coluna do arquivo
	public void zScore(String nomeArquivoLeitura, String NomeArquivoGravacao){
		Input arquivo = new Input();
		//le arqquivo e passa para uma matriz de double
		double[][] dados = arquivo.arquivoToMatrizDouble(nomeArquivoLeitura);
		//calcula a media da coluna
		double[] medias = new double[dados[0].length];
		for(int i=0;i<medias.length;i++){
			medias[i] = mediaColuna(dados, i);
		}
		
		//calcula o desvio padrao da coluna
		double[] desvioPadrao = new double[dados[0].length];
		for(int i=0;i<desvioPadrao.length;i++){
			desvioPadrao[i] = desvioPadraoColuna(dados, i, medias[i]);
		}
		//se desvio padrao for zero, bota null
		//(atributo-media)/desvio padrao
		for(int i=0;i<dados.length;i++){
			for (int j=0;j<dados[i].length-1;j++){//nao nromaliza a ultima coluna (classe)
				if(desvioPadrao[j]!=0){
					dados[i][j]= (dados[i][j]-medias[j])/desvioPadrao[j];
				}
				else{
					dados[i][j]=0;
				}
			}
		}
		//passa de double[][] para string[] para poder gravar
		String[] normalizado = new String[dados.length];

		for(int i=0;i<dados.length;i++){
			normalizado[i] = Arrays.toString(dados[i]);
			normalizado[i] = normalizado[i].substring(1);//tira colchetes do inicio
			normalizado[i] = normalizado[i].substring (0, normalizado[i].length() - 1); //tira colchetes do fim
		}
		
		//grava no disco
		Output grava = new Output();
		grava.escreveArquivo(NomeArquivoGravacao, normalizado, false);
	}
	
	//recebe como parametro uma matriz de double contendo os dados e a coluna a qual quer calcular a media
	//retorna a media daquela coluna
	public double mediaColuna(double[][] dados, int coluna){
		int quantidadeLinhas = dados.length;
		double somaTotalColuna =0;
		for (int i=0;i<dados.length;i++){
			somaTotalColuna = somaTotalColuna + dados[i][coluna];
		}
		double media = somaTotalColuna/quantidadeLinhas;
		return media;
	}
	
	//calcula o desvio padrao da coluna
	public double desvioPadraoColuna(double[][] dados, int coluna, double mediaColuna){
		//calcula a variancia da coluna
		double variancia = varianciaColuna(dados, coluna, mediaColuna);
		//retorna raiz quadrada positiva da variancia
		double desvioPadrao = Math.sqrt(variancia);
		return desvioPadrao;
	}
	
	//recebe como parametro uma matriz de double contendo os dados, a coluna  e a media da coluna qual se quer descobrir a variancia 
	//calcula a variancia populacional da coluna
	public double varianciaColuna(double[][] dados, int coluna, double mediaColuna){
		int quantidadeElementos = dados.length;
		double variancia =0;
		for(int i=0;i<dados.length;i++){
			variancia = variancia + Math.pow((dados[i][coluna]- mediaColuna), 2);
		}
		variancia = variancia/quantidadeElementos;
		return variancia;
	}
}
