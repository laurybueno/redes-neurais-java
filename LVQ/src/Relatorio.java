import java.util.Scanner;

//classe criada para gerar todos os arquivos necessarios ( logs ) para o relatorio
public class Relatorio {
	public static void main (String[] argq){
		long tempoInicio = System.currentTimeMillis(); 
		//------------- TREINAMENTO ---------------//
		
		//parametros objeto Inicializa - Inicio
		String random = "aleatoria";
		String nulo = "zero";
		String primEntrada = "primeiraEntrada";
		//parametros objeto Inicializa - Fim
		
		int quantidadeTestes =10;
// int neuronioSaida, double taxaDeAprendizado, double reducaoAprendizado, int quantidadeVezes
		String normalização = "MinMax";//muda pra qual normalizacao vc quer
		executaLVQ(normalização, nulo,  1, 0.5, 0, 1, "01");
		executaLVQ(normalização, nulo,  1, 0.005, 0.00001, 1, "02");
		executaLVQ(normalização, nulo,  1, 0.00005, 0.000000001, 1, "03");
		executaLVQ(normalização, nulo,  5, 0.5, 0, 1, "04");
		executaLVQ(normalização, nulo,  5, 0.005, 0.00001, 1, "05");
		executaLVQ(normalização, nulo,  5, 0.00005, 0.000000001, 1, "06");
		executaLVQ(normalização, nulo,  10, 0.5, 0, 1, "07");
		executaLVQ(normalização, nulo,  10, 0.005, 0.00001, 1, "08");
		executaLVQ(normalização, nulo,  10, 0.00005, 0.000000001, 1, "09");
		executaLVQ(normalização, primEntrada,  1, 0.5, 0, 1, "10");
		executaLVQ(normalização, primEntrada,  1, 0.005, 0.00001, 1, "11");
		executaLVQ(normalização, primEntrada,  1, 0.00005, 0.000000001, 1, "12");
		executaLVQ(normalização, primEntrada,  5, 0.5, 0, 1, "13");
		executaLVQ(normalização, primEntrada,  5, 0.005, 0.00001, 1, "14");
		executaLVQ(normalização, primEntrada,  5, 0.00005, 0.000000001, 1, "15");
		executaLVQ(normalização, primEntrada,  10, 0.5, 0, 1, "16");
		executaLVQ(normalização, primEntrada,  10, 0.005, 0.00001, 1, "17");
		executaLVQ(normalização, primEntrada,  10, 0.00005, 0.000000001, 1, "18");
		executaLVQ(normalização, random,  1, 0.5, 0, quantidadeTestes, "1");
		executaLVQ(normalização, random,  1, 0.005, 0.00001, quantidadeTestes, "2");
		executaLVQ(normalização, random,  1, 0.00005, 0.000000001, quantidadeTestes, "3");
		executaLVQ(normalização, random,  5, 0.5, 0, quantidadeTestes, "4");
		executaLVQ(normalização, random,  5, 0.005, 0.00001, quantidadeTestes, "6");
		executaLVQ(normalização, random,  5, 0.00005, 0.000000001, quantidadeTestes, "6");
		executaLVQ(normalização, random,  10, 0.5, 0, quantidadeTestes, "7");
		executaLVQ(normalização, random,  10, 0.005, 0.00001, quantidadeTestes, "8");
		executaLVQ(normalização, random,  10, 0.00005, 0.000000001, quantidadeTestes, "9");

		System.out.println("Tempo Total de execucao: "+(System.currentTimeMillis()-tempoInicio));
		
		//criaArquivoMediaAprendizado("log/logzScorealeatoria", quantidadeTestes , "log/logzMediaScorealeatoria.csv");
		//criaArquivoMediaAprendizado("log/logMinMaxaleatoria", quantidadeTestes , "log/logMediaMinMaxaleatoria.csv");
		
	}
	
	//funcao para executar a LVQ com os parametros dados uma determinada quantidade de vezes criando os logs
	static void executaLVQ(String normalizacao, String parametroInicio, int neuronioSaida, double taxaDeAprendizado, double reducaoAprendizado, int quantidadeVezes, String adicional){
		for(int cont=0;cont<quantidadeVezes;cont++){//contador de vezes que testou
			Log log = new Log();
			log.criaHead("log/dns/"+normalizacao+"_"+parametroInicio+"_"+cont+"_"+adicional);
			
			
			//Objeto responsavel por inicializar os pesos (neuronios de saida) e por alinhar os dados de teste recebidos da
			//entrada.
			//Recebe arquivo de dados de treinamento (ex: "entrada.txt"), e numero de neuronios de saida por cada classe (ex:"1")
			Inicializa inicializa = new Inicializa("../dados/treinozScore.csv","../dados/validacaozScore.csv", neuronioSaida, parametroInicio);//coloca arquivo de treino
			
			//MEDIDAS QUE DEVINEM A "CARA" DA LVQ - inicio//
			
			int numeroFixo = 10000; //numero que ira restringir ate que Epoca a LVQ pode chegar (ex:100)
			//System.out.println("digite o numero de epocas:");
			//int numeroFixo = sc.nextInt();
			//System.out.println("digite a taxa de aprendizado:");
			//double taxaDeAprendizado = sc.nextDouble();
			//System.out.println("digite o valor que reduz a taxa de aprendizado");
			//double reducaoAprendizado = sc.nextDouble();
	 		double valorMinimo=0; //valor minimo que a taxa de aprendizado pode chegar (ex:0.1)
			//System.out.println("digite o valor minimo que a taxa de aprendizado pode chegar");
	 		int maxPioras = 10;
			//double valorMinimo = sc.nextDouble();
			//MEDIDAS QUE DEVINEM A "CARA" DA LVQ - fim// (ex:)
			
			
			//Objeto que da inicio ao corpo da LVQ. Recebendo como parametro um objeto Inicializa, para inicializar seus pesos
			//e dados de entrada, alem de medidas que iram definir a cara da rede (numero fixo que as iteracoes podem chegar, taxa de Aprendizado, 
			//taxa de reducao do Aprendizado e valor minimo que a taxa de reducao pode chegar)
			LVQ lvq1 = new LVQ(inicializa, numeroFixo, taxaDeAprendizado, reducaoAprendizado, valorMinimo, maxPioras);
			lvq1.Aprendizado("log/dns/"+normalizacao+"_"+parametroInicio+"_" +cont+"_"+adicional);
			String nomeArquivoTeste ="../dados/teste"+normalizacao+".csv";
			double[] respostas = lvq1.Teste(nomeArquivoTeste);
			MatrizConfusao confusao = new MatrizConfusao();
			confusao.adicionaMatriz(nomeArquivoTeste, respostas);
			confusao.gravaMAtrizConfusao("log/dns/"+normalizacao+"_"+parametroInicio+"_" +cont+"_"+adicional+"_MatrizConfusao.csv");
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
