import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class Main {
	public static void main (String [] args){
		System.out.println("foi");
		//Outlier out = new Outlier();
		//out.outliernoarquivo("classe0.csv");
		
		System.out.println("saiu");
		Input arquivo = new Input();
		//arquivo.arquivoToMatrizDouble("dados.txt");
		//Normaliza normal = new Normaliza();
		//normal.zScore("dados.csv", "normalizadozScore.csv");
		//normal.minMax("dados.csv");
		
		System.out.println("criando treino, teste, validacao");
		//arquivo.funcaoIntegradora("normalizadoMinMax.csv", 60, 20, 20, "minMax");
		//arquivo.funcaoIntegradora("dados.csv", 60, 20, 20, "_bruto");
		arquivo.funcaoIntegradora("dados.csv", 60, 20, 20, "MinMax");
		System.out.println("fez min max");
		arquivo.funcaoIntegradora("dados.csv", 60, 20, 20, "zScore");
		//arquivo.funcaoIntegradora("dados.csv", 60, 20, 20, "_bruto");
		
		System.out.println("acabou");
	}
}
