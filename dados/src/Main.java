
public class Main {
 public static void main (String [] args){
		Input arquivo = new Input();
		Output grava = new Output();
		grava.separaClasses(arquivo.preencheClasse("dados.txt"));
		Normaliza normal = new Normaliza();
		for(int i=0;i<10;i++){
			normal.zScore("registros"+i+".csv", "normalizado"+i+".csv");
		}
 }
}
