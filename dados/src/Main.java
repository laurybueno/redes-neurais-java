
public class Main {
 public static void main (String [] args){
	Input arquivo = new Input();
	System.out.println("cria arquivos de teste, validcao e treino");
	arquivo.funcaoIntegradora("dados.txt", 60, 30, 10);
	System.out.println("criada");
	Output grava = new Output();
	grava.separaClasses(arquivo.preencheClasse("dados.txt"));
	Normaliza normal = new Normaliza();
	System.out.println("gravando arquivos de cada classe normalizado");
	for(int i=0;i<10;i++){
		normal.zScore("registros"+i+".csv", "normalizado"+i+".csv");
	}
 }
}
