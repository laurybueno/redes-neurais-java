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
		Normaliza normal = new Normaliza();
		normal.zScore("dados.csv", "normalizadozScore.csv");
		normal.minMax("dados.csv",0.1,0.9 , "minMax");
		
		System.out.println("criando treino, teste, validacao");
		for(int i=0;i<10;i++){//faz os 10 houldouts para os testes
			arquivo.houldout("dados.csv", 60, 20, 20, "_bruto"+String.valueOf(i));
			System.out.println("feito o bruto");
			arquivo.houldout("normalizadominMax.csv", 60, 20, 20, "minMax"+String.valueOf(i));
			System.out.println("fez min max");
			arquivo.houldout("normalizadozScore.csv", 60, 20, 20, "zScore"+String.valueOf(i));
		}
		System.out.println("acabou");
	}
}
