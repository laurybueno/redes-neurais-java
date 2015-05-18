import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;


public class PosProcessamento {
	
	LinkedList<int[][]> matrizes;
	
	// 0 é a acurácia e 1 o erro quadrado
	LinkedList<double[]> erros;
	
	int nE;
	double tA;
	Date data;
	
	static private PosProcessamento instancia;
	static boolean instanciado = false;
	
	private PosProcessamento(int nE, double tA, Date data){
		matrizes = new LinkedList<int[][]>();
		erros = new LinkedList<double[]>();
		this.nE = nE;
		this.tA = tA;
		this.data = data;
	}
	
	public static PosProcessamento getInstance(int nE, double tA, Date data){
		if(!instanciado){
			instancia = new PosProcessamento(nE, tA, data);
			instanciado = true;
		}
		return instancia;
	}
	
	public static PosProcessamento getInstance(){
		return instancia;
	}
	
	public static void clear(){
		instanciado = false;
		instancia = null;
	}
	
	public void addMatriz(int[][] nova){
		matrizes.add(nova);
	}
	
	public void addErro(double[] novo){
		erros.add(novo);
	}
	
	public int getSize(){
		return matrizes.size();
	}
	
	
	// encontra o desvio padrão da matriz
	public double[][] matrizDesvio(){
		
		Iterator<int[][]> it = matrizes.iterator();
		
		double[][] desvios = new double[10][10];
		double[][] medias = matrizMedia();
		
		// encontra a variância de todos os termos
		while (it.hasNext()) {
			int[][] atual = it.next();

			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					desvios[i][j] += (double)(medias[i][j]-atual[i][j])*(medias[i][j]-atual[i][j])/matrizes.size();
				}
			}
		}
		
		// tira a raiz quadrada de todos os termos
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				desvios[i][j] = Math.sqrt(desvios[i][j]);
			}
		}
		return desvios;

	}
	
	
	// encontra a mádia de cada elemento da matriz
	private double[][] matrizMedia(){
		
		double[][] medias = new double[10][10];
		
		Iterator<int[][]> it = matrizes.iterator();
		
		while(it.hasNext()){
			int[][] atual = it.next();
			
			// inclui a matriz atual no cálculo da média
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					medias[i][j] += (double)(atual[i][j]/matrizes.size());
				}
			}
		}
		return medias;
	}
	
	// retorna no índice 0 a acurácia média e no índice 1 o erro quadrado médio
	public double[] mediaErro(){
		double[] medias = new double[2];
		
		Iterator<double[]> it = erros.iterator();
		
		while (it.hasNext()) {
			double[] atual = it.next();
			medias[0] += (double)(atual[0]/erros.size());
			medias[1] += (double)(atual[1]/erros.size());
		}
		return medias;
	}
	
	public void gravaArq(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		
		// ********** salva a matriz de confusão ************ //
		PrintStream pr;

		try {
			pr = new PrintStream(new File("sumMatrizConfusaoDesv_"+"_nE"+nE+"_tA"+tA+"__"+dateFormat.format(data)+".csv"));
			
			// printa um cabeçalho
			pr.print("Linhas representam a realidade, enquanto colunas mostram as respostas da rede.");
			pr.println();
			
			// printa os header de colunas
			pr.print("	");
			for (int i = 0; i < 10; i++) {
				pr.print("	"+i);
			}
			pr.println();
			
			double[][] matriz = matrizDesvio();

			// printa matriz
			for (int i = 0; i < matriz.length; i++) {
				pr.print("	"+i);
				for (int j = 0; j < matriz[i].length; j++) {
					pr.print("	"+matriz[i][j]);
				}
				pr.println();
			}
			pr.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		// grava matriz de confusão com médias
		PrintStream mt;
		try {
			mt = new PrintStream(new File("sumMatrizConfusaoMed_"+"_nE"+nE+"_tA"+tA+"__"+dateFormat.format(data)+".csv"));
			
			// printa um cabeçalho
			mt.print("Linhas representam a realidade, enquanto colunas mostram as respostas da rede.");
			mt.println();
			
			// printa os header de colunas
			mt.print("	");
			for (int i = 0; i < 10; i++) {
				mt.print("	"+i);
			}
			mt.println();
			
			double[][] matriz = matrizMedia();

			// printa matriz
			for (int i = 0; i < matriz.length; i++) {
				mt.print("	"+i);
				for (int j = 0; j < matriz[i].length; j++) {
					mt.print("	"+matriz[i][j]);
				}
				mt.println();
			}
			mt.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		// ************** salva o log *************** //
		Log ult = new Log();
		double[] erros = mediaErro();
		ult.setNomeArquivo("sumRedesTeste_"+"_nE"+nE+"_tA"+tA+"__"+dateFormat.format(this.data));
		ult.addDados(0,erros[0],erros[1],tA);
		ult.gravaArquivo();
		
	}

}
