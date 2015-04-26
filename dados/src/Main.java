import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class Main {
 public static void main (String [] args){
	//Input arquivo = new Input();
	//arquivo.arquivoToMatrizDouble("dados.txt");
	Normaliza normal = new Normaliza();
	normal.minMax("dados.csv");
	//System.out.println("criando treino, teste, validacao");
	//arquivo.funcaoIntegradora("normalizados.csv", 60, 30, 10);
 }
}
