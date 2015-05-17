import java.util.Scanner;

//classe criada para gerar todos os arquivos necessarios ( logs ) para o relatorio
public class Relatorio {
	public static void main (String[] argq){
		System.out.println("executando o relatorio");
		long tempoInicio = System.currentTimeMillis(); 
		//------------- TREINAMENTO ---------------//
		
		//parametros objeto Inicializa - Inicio
		String random = "aleatoria";
		String nulo = "zero";
		String primEntrada = "primeiraEntrada";
		//parametros objeto Inicializa - Fim
		
		//int quantidadeTestes =10;
// int neuronioSaida, double taxaDeAprendizado, double reducaoAprendizado,max pioras,  int quantidadeVezes .. numeorHouldout
		
		String normalizacao = "_bruto";//muda pra qual normalizacao vc quer
		for(int i=0; i<10; i++){
			//chama a funcao para gerar os logs de aprendizado
			executaLVQ(normalizacao, nulo, 3, 0.01, 0.00001, 10, 1, String.valueOf(i)+"00", String.valueOf(i));
		}
		for(int i=0;i<10;i++){
			//faz a media do aprendizado anterior
			criaArquivoMediaAprendizado("log/dns/_bruto_zero_", 0/*quantidade de testes (se for random, fica 10 aqui) */ , "log/dns/_bruto_zero_0_MEDIA.csv", normalizacao, String.valueOf(i));
		}
		//testes para mostrar o resultado das normalizacoes
		//executaLVQ("_bruto", random, 2, 0.00005, 0.000000001, 1, "olha esse aqui");
		//executaLVQ("zScore", random, 2, 0.00005, 0.000000001, 1, "olha esse aqui");
		//executaLVQ("minMax", random, 2, 0.00005, 0.000000001, 1, "olha esse aqui");
		System.out.println("fez os testes das normalizacoes");
		//executaLVQ("zScore", random, 4, 0.00005, 0.000000001, 10, "LucaszScore");
		//System.out.println("Tempo Total de execucao: "+(System.currentTimeMillis()-tempoInicio));
		
		//3
		//criaArquivoMediaAprendizado("log/lucas/MinMax_aleatoria_", quantidadeTestes , "log/lucas/logzMediaMinMaxaleatoria.csv", "MinMax");
		System.out.println("feito o primeiro");
		//criaArquivoMediaAprendizado("log/lucas/zScore_aleatoria_", quantidadeTestes , "log/lucas/logMediazScorealeatoria.csv", "zScore");
		
		//executaLVQ("MinMax", primEntrada, 4, 0.00005, 0.000000001, 1, "LucasNulo");
		//executaLVQ("zScore", nulo, 4, 0.00005, 0.000000001, 1, "LucasNulo");
		
	}
	
	//funcao para executar a LVQ com os parametros dados uma determinada quantidade de vezes criando os logs
	static void executaLVQ(String normalizacao, String parametroInicio, int neuronioSaida, double taxaDeAprendizado, double reducaoAprendizado, int maxPioras,int quantidadeVezes, String adicional, String numeroHouldout){
		for(int cont=0;cont<quantidadeVezes;cont++){//contador de vezes que testou
			Log log = new Log();
			String pasta = "log/dns/";
			log.criaHead(pasta+normalizacao+"_"+parametroInicio+"_"+cont+"_"+adicional);
			
			
			//Objeto responsavel por inicializar os pesos (neuronios de saida) e por alinhar os dados de teste recebidos da
			//entrada.
			//Recebe arquivo de dados de treinamento (ex: "entrada.txt"), e numero de neuronios de saida por cada classe (ex:"1")
			Inicializa inicializa = new Inicializa("../dados/holdout/treino"+normalizacao+numeroHouldout+".csv","../dados/holdout/validacao"+normalizacao+numeroHouldout+".csv", neuronioSaida, parametroInicio);//coloca arquivo de treino
			
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
	 		
			//double valorMinimo = sc.nextDouble();
			//MEDIDAS QUE DEVINEM A "CARA" DA LVQ - fim// (ex:)
			
			
			//Objeto que da inicio ao corpo da LVQ. Recebendo como parametro um objeto Inicializa, para inicializar seus pesos
			//e dados de entrada, alem de medidas que iram definir a cara da rede (numero fixo que as iteracoes podem chegar, taxa de Aprendizado, 
			//taxa de reducao do Aprendizado e valor minimo que a taxa de reducao pode chegar)
			LVQ lvq1 = new LVQ(inicializa, numeroFixo, taxaDeAprendizado, reducaoAprendizado, valorMinimo, maxPioras);
			lvq1.Aprendizado(pasta+normalizacao+"_"+parametroInicio+"_" +cont+"_"+adicional);
			String nomeArquivoTeste ="../dados/holdout/teste"+normalizacao+numeroHouldout+".csv";
			double[] respostas = lvq1.Teste(nomeArquivoTeste);
			MatrizConfusao confusao = new MatrizConfusao();
			confusao.adicionaMatriz(nomeArquivoTeste, respostas);
			confusao.gravaMAtrizConfusao(pasta+normalizacao+"_"+parametroInicio+"_" +cont+"_"+adicional+"_MatrizConfusao.csv");
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
	
	static void criaArquivoMediaAprendizado(String arquivoLeitura, int quantidadeArquivos, String arquivoGravacao, String normalizacao,String numeroHouldout){
		Input le = new Input();
		double[][] dados = le.arquivoComHeadToMatrizDouble(arquivoLeitura+0+"_"+numeroHouldout+"00Aprendizado.csv");
		
		for(int i=1;i<quantidadeArquivos;i++){//ja leu o primeiro
			double[][] media = le.arquivoComHeadToMatrizDouble(arquivoLeitura+i+"_Lucas"+normalizacao+"Aprendizado.csv");
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
