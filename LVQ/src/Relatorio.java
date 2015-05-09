import java.util.Scanner;

//classe criada para gerar todos os arquivos necessarios ( logs ) para o relatorio
public class Relatorio {
	public static void main (String[] argq){
		
		//------------- TREINAMENTO ---------------//
		
		//parametros objeto Inicializa - Inicio
		String random = "aleatoria";
		String nulo = "zero";
		String primEntrada = "primeiraEntrada";
		String ArquivoTreinamento= "aquiDNS.csv";
		//parametros objeto Inicializa - Fim
		
		

		//executaLVQ("zScore", random, 10);
		executaLVQ("MinMax", random, 10);
		criaArquivoMediaAprendizado("log/logzScorealeatoria", 10, "log/logzMediaScorealeatoria.csv");
		criaArquivoMediaAprendizado("log/logMinMaxaleatoria", 10, "log/logMediaMinMaxaleatoria.csv");
		
	}
	
	//funcao para executar a LVQ com os parametros dados uma determinada quantidade de vezes criando os logs
	static void executaLVQ(String normalizacao, String parametroInicio, int quantidadeVezes){
		for(int cont=0;cont<quantidadeVezes;cont++){//contador de vezes que testou
			Log log = new Log();
			log.criaHead("log/log"+normalizacao+parametroInicio +cont);
			
			
			//Objeto responsavel por inicializar os pesos (neuronios de saida) e por alinhar os dados de teste recebidos da
			//entrada.
			//Recebe arquivo de dados de treinamento (ex: "entrada.txt"), e numero de neuronios de saida por cada classe (ex:"1")
			Inicializa inicializa = new Inicializa("../dados/treino"+normalizacao+".csv","../dados/validacao"+normalizacao+".csv", 3, parametroInicio);//coloca arquivo de treino
			
			//MEDIDAS QUE DEVINEM A "CARA" DA LVQ - inicio//
			
			int numeroFixo = 10000; //numero que ira restringir ate que Epoca a LVQ pode chegar (ex:100)
			//System.out.println("digite o numero de epocas:");
			//int numeroFixo = sc.nextInt();
			double taxaDeAprendizado = 0.002; //taxa de Aprendizado (ex: 2.0)
			//System.out.println("digite a taxa de aprendizado:");
			//double taxaDeAprendizado = sc.nextDouble();
			double reducaoAprendizado = 0.0; //valor que reduz a taxa de Aprendizado (ex: 0.1)
			//System.out.println("digite o valor que reduz a taxa de aprendizado");
			//double reducaoAprendizado = sc.nextDouble();
	 		double valorMinimo=0.1; //valor minimo que a taxa de aprendizado pode chegar (ex:0.1)
			//System.out.println("digite o valor minimo que a taxa de aprendizado pode chegar");
	 		int maxPioras = 10;
			//double valorMinimo = sc.nextDouble();
			//MEDIDAS QUE DEVINEM A "CARA" DA LVQ - fim// (ex:)
			
			
			//Objeto que da inicio ao corpo da LVQ. Recebendo como parametro um objeto Inicializa, para inicializar seus pesos
			//e dados de entrada, alem de medidas que iram definir a cara da rede (numero fixo que as iteracoes podem chegar, taxa de Aprendizado, 
			//taxa de reducao do Aprendizado e valor minimo que a taxa de reducao pode chegar)
			LVQ lvq1 = new LVQ(inicializa, numeroFixo, taxaDeAprendizado, reducaoAprendizado, valorMinimo, maxPioras);
			lvq1.Aprendizado("log/log"+normalizacao+parametroInicio +cont);
			String nomeArquivoTeste ="../dados/teste"+normalizacao+".csv";
			double[] respostas = lvq1.Teste(nomeArquivoTeste);
			MatrizConfusao confusao = new MatrizConfusao();
			confusao.adicionaMatriz(nomeArquivoTeste, respostas);
			confusao.gravaMAtrizConfusao("log/log"+normalizacao+parametroInicio +cont+"MatrizConfusao.csv");
		}
	}
	
	
	//funcao para calcualr a meida de duas matrizes, usado apara tirar a meida de todos os randomicos
	static double[][] mediaMatriz (double[][] matriz, double[][] media){
		for(int i=0;i<media.length;i++){
			for(int j=0;j<media[i].length;j++){
				media[i][j]=media[i][j]+matriz[i][j];
				media[i][j] = media[i][j]/2;
			}
		}
		return media;
	}
	
	static void criaArquivoMediaAprendizado(String arquivoLeitura, int quantidadeArquivos, String arquivoGravacao){
		Input le = new Input();
		double[][] dados = le.arquivoComHeadToMatrizDouble(arquivoLeitura+0+"Aprendizado.csv");
		
		for(int i=1;i<quantidadeArquivos;i++){//ja leu o primeiro
			double[][] media = le.arquivoComHeadToMatrizDouble(arquivoLeitura+i+"Aprendizado.csv");
			dados= mediaMatriz(media, dados);
		}
		
		Output grava = new Output();
		String[] s = new String[3];
		s[0]="Epoca:,";
		s[1]="Taxa erro do treinamento:,";
		s[2]="Taxa erro da validacao:,";
		
		grava.escreveArquivo(arquivoGravacao, s, false);//cria head do arquivo
		
		//passa da matriz de double pra arranjo de string e grava
		for(int i=0;i<dados.length;i++){
			for(int j=0;j<dados[i].length;j++){
				s[j]= String.valueOf(dados[i][j]+",");
			}
			grava.escreveArquivo(arquivoGravacao, s, true);
		}
	}
}
