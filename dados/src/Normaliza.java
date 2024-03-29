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
	public void minMax(String nomeArquivoLeitura,double newMin,double newMax, String normalizacao){
		Input arquivo = new Input();
		double[][] dados = arquivo.arquivoToMatrizDouble(nomeArquivoLeitura);//le arqquivo e passa para uma matriz de double
		
		double[][] normal = geraNovaMatriz(dados, 0);
		double[][] minMax = geraNovaMatriz(normal, 0);
		
		double[] minimo = encontraMin(minMax);//arranjo contendo o menor numeor de cada coluna respectivamente
		double[] maximo = encontraMax(minMax);//arranjo contendo o maior numeor de cada coluna respectivamente
		
		for(int i =0;i<minMax.length;i++){
			for(int j=0;j<minMax[i].length-1;j++){
				double divisor = maximo[j]-minimo[j];
				if(divisor != 0){
					minMax[i][j]=(minMax[i][j]-minimo[j])/(maximo[j]-minimo[j]);//fez de 0 a 1
					minMax[i][j] = (minMax[i][j]*(newMax - newMin))+newMin;
				}
				else{
					minMax[i][j]=0;
				}
			}
		}

		

		String[] normalizado = new String[minMax.length];

		for(int i=0;i<minMax.length;i++){
			normalizado[i] = Arrays.toString(minMax[i]);
			normalizado[i] = normalizado[i].substring(1);//tira colchetes do inicio
			normalizado[i] = normalizado[i].substring (0, normalizado[i].length() - 1); //tira colchetes do fim
		}
		//grava no disco
		Output grava = new Output();
		grava.escreveArquivo("normalizado"+normalizacao+".csv", normalizado, false);
		
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
	
	//funcao para deletar coluna que so tem numeoro igual
	public double [][] geraNovaMatriz(double[][] args1, double newMin){
		double [][] novaMatriz = clonaMatriz(args1);
		boolean parada = true;
		int coluna = 0;
		while (parada && coluna < args1[0].length){
			if(sotem0(args1, coluna, newMin)){
				parada = false;
				novaMatriz = deletaColuna(args1, coluna);
				geraNovaMatriz(novaMatriz, newMin);
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
		 
	


	//funcao para checar coluna que s� tem 0
	//recebe como parametro a matriz e o numeoro da coluna
	boolean sotem0(double[][] dados, int coluna, double newMin){
		for(int i=0;i<dados.length;i++){
			if(dados[i][coluna]!=newMin){
				return false;
			}
		}
		return true;
	}
	
	
	//funcao que retorna um arranjo contendo o menor numero encontrado em cada coluna respectivamente
	public double[] encontraMin(double[][] dados){
		double[] minimo = new double[dados[0].length];
		for(int i=0;i<minimo.length;i++){
			minimo[i]=17;
		}
		for(int i=0;i<dados.length;i++){//percorre o arranjo colocando o primeiro elemento de cada coluna
			for (int j=0;j<dados[i].length;j++){
				if(dados[i][j]<minimo[j]){
					minimo[j]=dados[i][j];
				}
			}
		}
		return minimo;
	}
	
	//funcao que retorna um arranjo contendo o maior numero encontrado em cada coluna respectivamente
	public double[] encontraMax(double[][] dados){
		double[] maximo = new double[dados[0].length];
		for(int i=0;i<dados.length;i++){
			for (int j=0;j<dados[i].length;j++){
				if(dados[i][j]>maximo[j]){
					maximo[j]=dados[i][j];
				}
			}
		}
		return maximo;
	}
	
	
		
	
	//funcao para fazer a normalizacao usando a tecnica Z score
	//recebe como parametro o nome do arquivo em que se quer normalizar e o nome do arquivo onde vai salvar isso
	public void zScore(String nomeArquivoLeitura, String NomeArquivoGravacao){
		Input arquivo = new Input();
		//le arqquivo e passa para uma matriz de double
		double[][] dados = arquivo.arquivoToMatrizDouble(nomeArquivoLeitura);
		double[][] teste = geraNovaMatriz(dados, 0);
		double[][] normal = geraNovaMatriz(teste, 0);
		//calcula a media da coluna
		double[] medias = new double[normal[0].length];
		for(int i=0;i<medias.length;i++){
			medias[i] = mediaColuna(normal, i);
		}
		
		//calcula o desvio padrao da coluna
		double[] desvioPadrao = new double[normal[0].length];
		for(int i=0;i<desvioPadrao.length;i++){
			desvioPadrao[i] = desvioPadraoColuna(normal, i, medias[i]);
		}
		//se desvio padrao for zero, bota null
		//(atributo-media)/desvio padrao
		for(int i=0;i<normal.length;i++){
			for (int j=0;j<normal[i].length-1;j++){//nao nromaliza a ultima coluna (classe)
				if(desvioPadrao[j]!=0){
					normal[i][j]= (normal[i][j]-medias[j])/desvioPadrao[j];
				}
				else{
					normal[i][j]=0;
				}
			}
		}
		
		
		

		
		//passa de double[][] para string[] para poder gravar
		String[] normalizado = new String[normal.length];

		for(int i=0;i<normal.length;i++){
			normalizado[i] = Arrays.toString(normal[i]);
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
		if(mediaColuna!=0){
			for(int i=0;i<dados.length;i++){
				variancia = variancia + Math.pow((dados[i][coluna]- mediaColuna), 2);
			}
			variancia = variancia/(quantidadeElementos-1);
		}
		return variancia;
	}
}
