import java.util.Scanner;

public class Main{
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		Log log = new Log();
		log.criaHead("log/log1");
		//------------- TREINAMENTO ---------------//
		
		//parametros objeto Inicializa - Inicio
		String random = "aleatoria";
		String nulo = "zero";
		String primEntrada = "primeiraEntrada";
		String ArquivoTreinamento= "aquiDNS.csv";
		//parametros objeto Inicializa - Fim
		
		//Objeto responsavel por inicializar os pesos (neuronios de saida) e por alinhar os dados de teste recebidos da
		//entrada.
		//Recebe arquivo de dados de treinamento (ex: "entrada.txt"), e numero de neuronios de saida por cada classe (ex:"1")
		Inicializa inicializa = new Inicializa("../dados/treinozScore.csv","../dados/validacaozScore.csv", 3, random);//coloca arquivo de treino
		
		//MEDIDAS QUE DEVINEM A "CARA" DA LVQ - inicio//
		
		int numeroFixo = 50000; //numero que ira restringir ate que Epoca a LVQ pode chegar (ex:100)
		//System.out.println("digite o numero de epocas:");
		//int numeroFixo = sc.nextInt();
		double taxaDeAprendizado = 0.002; //taxa de Aprendizado (ex: 2.0)
		//System.out.println("digite a taxa de aprendizado:");
		//double taxaDeAprendizado = sc.nextDouble();
		double reducaoAprendizado = 0.000002; //valor que reduz a taxa de Aprendizado (ex: 0.1)
		//System.out.println("digite o valor que reduz a taxa de aprendizado");
		//double reducaoAprendizado = sc.nextDouble();
 		double valorMinimo=0.0001; //valor minimo que a taxa de aprendizado pode chegar (ex:0.1)
		//System.out.println("digite o valor minimo que a taxa de aprendizado pode chegar");
 		int maxPiora = 1;
		//double valorMinimo = sc.nextDouble();
		//MEDIDAS QUE DEVINEM A "CARA" DA LVQ - fim// (ex:)
		
		
		//Objeto que da inicio ao corpo da LVQ. Recebendo como parametro um objeto Inicializa, para inicializar seus pesos
		//e dados de entrada, alem de medidas que iram definir a cara da rede (numero fixo que as iteracoes podem chegar, taxa de Aprendizado, 
		//taxa de reducao do Aprendizado e valor minimo que a taxa de reducao pode chegar)
		LVQ lvq1 = new LVQ(inicializa, numeroFixo, taxaDeAprendizado, reducaoAprendizado, valorMinimo, maxPiora);
		lvq1.Aprendizado("log/log1"); 
		
		String nomeArquivoTeste ="../dados/testezScore.csv";
		double[] respostas = lvq1.Teste(nomeArquivoTeste);
		MatrizConfusao confusao = new MatrizConfusao();
		confusao.adicionaMatriz(nomeArquivoTeste, respostas);
		confusao.gravaMAtrizConfusao("log/log1MatrizConfusao.csv");
	}
}
