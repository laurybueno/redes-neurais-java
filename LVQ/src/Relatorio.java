import java.util.Scanner;

//classe criada para gerar todos os arquivos necessarios ( logs ) para o relatorio
public class Relatorio {
	public static void main (String[] arg){
		
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
		for(int i=0;i<18;i++){//cria media dos 17 testes
			double[][] media = criaMediaMatrizConfusao("log/dns/_bruto_", i,"_bruto", "aleatoria_0_");
			criaDesVioPadraoMatrizConfusao("log/dns/_bruto_", media, i, "_bruto","aleatoria_0_" );
			
			media = criaMediaMatrizConfusao("log/dns/_bruto_", i,"_bruto", "zero_0_");
			criaDesVioPadraoMatrizConfusao("log/dns/_bruto_", media, i, "_bruto", "zero_0_");
			
			media = criaMediaMatrizConfusao("log/dns/_bruto_", i,"_bruto", "primeiraEntrada_0_");
			criaDesVioPadraoMatrizConfusao("log/dns/_bruto_", media, i, "_bruto", "primeiraEntrada_0_");
		}
		System.out.println("cabo");
		
		String normalizacao = "_bruto";//muda pra qual normalizacao vc quer
		/*for(int i=0; i<10; i++){//chama a funcao para gerar os logs de aprendizado
			executaLVQ(normalizacao, nulo, 1, 0.01, 0.00001, 1, 1, String.valueOf(i)+"00", String.valueOf(i));
			executaLVQ(normalizacao, nulo, 1, 0.0001, 0.0000001, 1, 1, String.valueOf(i)+"01", String.valueOf(i));
			executaLVQ(normalizacao, nulo, 1, 0.000001, 0.000000001, 1, 1, String.valueOf(i)+"02", String.valueOf(i));

			executaLVQ(normalizacao, nulo, 1, 0.01, 0.00001, 3, 1, String.valueOf(i)+"03", String.valueOf(i));
			executaLVQ(normalizacao, nulo, 1, 0.0001, 0.0000001, 3, 1, String.valueOf(i)+"04", String.valueOf(i));
			executaLVQ(normalizacao, nulo, 1, 0.000001, 0.000000001, 3, 1, String.valueOf(i)+"05", String.valueOf(i));

			executaLVQ(normalizacao, nulo, 5, 0.01, 0.00001, 1, 1, String.valueOf(i)+"06", String.valueOf(i));
			executaLVQ(normalizacao, nulo, 5, 0.0001, 0.0000001, 1, 1, String.valueOf(i)+"07", String.valueOf(i));
			executaLVQ(normalizacao, nulo, 5, 0.000001, 0.000000001, 1, 1, String.valueOf(i)+"08", String.valueOf(i));

			executaLVQ(normalizacao, nulo, 5, 0.01, 0.00001, 3, 1, String.valueOf(i)+"09", String.valueOf(i));
			executaLVQ(normalizacao, nulo, 5, 0.0001, 0.0000001, 3, 1, String.valueOf(i)+"10", String.valueOf(i));
			executaLVQ(normalizacao, nulo, 5, 0.000001, 0.000000001, 3, 1, String.valueOf(i)+"11", String.valueOf(i));

			executaLVQ(normalizacao, nulo, 10, 0.01, 0.00001, 1, 1, String.valueOf(i)+"12", String.valueOf(i));
			executaLVQ(normalizacao, nulo, 10, 0.0001, 0.0000001, 1, 1, String.valueOf(i)+"13", String.valueOf(i));
			executaLVQ(normalizacao, nulo, 10, 0.000001, 0.000000001, 1, 1, String.valueOf(i)+"14", String.valueOf(i));

			executaLVQ(normalizacao, nulo, 10, 0.01, 0.00001, 3, 1, String.valueOf(i)+"15", String.valueOf(i));
			executaLVQ(normalizacao, nulo, 10, 0.0001, 0.0000001, 3, 1, String.valueOf(i)+"16", String.valueOf(i));
			executaLVQ(normalizacao, nulo, 10, 0.000001, 0.000000001, 3, 1, String.valueOf(i)+"17", String.valueOf(i));

			executaLVQ(normalizacao, primEntrada, 1, 0.01, 0.00001, 1, 1, String.valueOf(i)+"00", String.valueOf(i));
			executaLVQ(normalizacao, primEntrada, 1, 0.0001, 0.0000001, 1, 1, String.valueOf(i)+"01", String.valueOf(i));
			executaLVQ(normalizacao, primEntrada, 1, 0.000001, 0.000000001, 1, 1, String.valueOf(i)+"02", String.valueOf(i));

			executaLVQ(normalizacao, primEntrada, 1, 0.01, 0.00001, 3, 1, String.valueOf(i)+"03", String.valueOf(i));
			executaLVQ(normalizacao, primEntrada, 1, 0.0001, 0.0000001, 3, 1, String.valueOf(i)+"04", String.valueOf(i));
			executaLVQ(normalizacao, primEntrada, 1, 0.000001, 0.000000001, 3, 1, String.valueOf(i)+"05", String.valueOf(i));

			executaLVQ(normalizacao, primEntrada, 5, 0.01, 0.00001, 1, 1, String.valueOf(i)+"06", String.valueOf(i));
			executaLVQ(normalizacao, primEntrada, 5, 0.0001, 0.0000001, 1, 1, String.valueOf(i)+"07", String.valueOf(i));
			executaLVQ(normalizacao, primEntrada, 5, 0.000001, 0.000000001, 1, 1, String.valueOf(i)+"08", String.valueOf(i));

			executaLVQ(normalizacao, primEntrada, 5, 0.01, 0.00001, 3, 1, String.valueOf(i)+"09", String.valueOf(i));
			executaLVQ(normalizacao, primEntrada, 5, 0.0001, 0.0000001, 3, 1, String.valueOf(i)+"10", String.valueOf(i));
			executaLVQ(normalizacao, primEntrada, 5, 0.000001, 0.000000001, 3, 1, String.valueOf(i)+"11", String.valueOf(i));

			executaLVQ(normalizacao, primEntrada, 10, 0.01, 0.00001, 1, 1, String.valueOf(i)+"12", String.valueOf(i));
			executaLVQ(normalizacao, primEntrada, 10, 0.0001, 0.0000001, 1, 1, String.valueOf(i)+"13", String.valueOf(i));
			executaLVQ(normalizacao, primEntrada, 10, 0.000001, 0.000000001, 1, 1, String.valueOf(i)+"14", String.valueOf(i));

			executaLVQ(normalizacao, primEntrada, 10, 0.01, 0.00001, 3, 1, String.valueOf(i)+"15", String.valueOf(i));
			executaLVQ(normalizacao, primEntrada, 10, 0.0001, 0.0000001, 3, 1, String.valueOf(i)+"16", String.valueOf(i));
			executaLVQ(normalizacao, primEntrada, 10, 0.000001, 0.000000001, 3, 1, String.valueOf(i)+"17", String.valueOf(i));

			executaLVQ(normalizacao, random, 1, 0.01, 0.00001, 1, 1, String.valueOf(i)+"00", String.valueOf(i));
			executaLVQ(normalizacao, random, 1, 0.0001, 0.0000001, 1, 1, String.valueOf(i)+"01", String.valueOf(i));
			executaLVQ(normalizacao, random, 1, 0.000001, 0.000000001, 1, 1, String.valueOf(i)+"02", String.valueOf(i));

			executaLVQ(normalizacao, random, 1, 0.01, 0.00001, 3, 1, String.valueOf(i)+"03", String.valueOf(i));
			executaLVQ(normalizacao, random, 1, 0.0001, 0.0000001, 3, 1, String.valueOf(i)+"04", String.valueOf(i));
			executaLVQ(normalizacao, random, 1, 0.000001, 0.000000001, 3, 1, String.valueOf(i)+"05", String.valueOf(i));

			executaLVQ(normalizacao, random, 5, 0.01, 0.00001, 1, 1, String.valueOf(i)+"06", String.valueOf(i));
			executaLVQ(normalizacao, random, 5, 0.0001, 0.0000001, 1, 1, String.valueOf(i)+"07", String.valueOf(i));
			executaLVQ(normalizacao, random, 5, 0.000001, 0.000000001, 1, 1, String.valueOf(i)+"08", String.valueOf(i));

			executaLVQ(normalizacao, random, 5, 0.01, 0.00001, 3, 1, String.valueOf(i)+"09", String.valueOf(i));
			executaLVQ(normalizacao, random, 5, 0.0001, 0.0000001, 3, 1, String.valueOf(i)+"10", String.valueOf(i));
			executaLVQ(normalizacao, random, 5, 0.000001, 0.000000001, 3, 1, String.valueOf(i)+"11", String.valueOf(i));

			executaLVQ(normalizacao, random, 10, 0.01, 0.00001, 1, 1, String.valueOf(i)+"12", String.valueOf(i));
			executaLVQ(normalizacao, random, 10, 0.0001, 0.0000001, 1, 1, String.valueOf(i)+"13", String.valueOf(i));
			executaLVQ(normalizacao, random, 10, 0.000001, 0.000000001, 1, 1, String.valueOf(i)+"14", String.valueOf(i));

			executaLVQ(normalizacao, random, 10, 0.01, 0.00001, 3, 1, String.valueOf(i)+"15", String.valueOf(i));
			executaLVQ(normalizacao, random, 10, 0.0001, 0.0000001, 3, 1, String.valueOf(i)+"16", String.valueOf(i));
			executaLVQ(normalizacao, random, 10, 0.000001, 0.000000001, 3, 1, String.valueOf(i)+"17", String.valueOf(i));
		}
		System.out.println("vai gerar a media agora");
		for(int i=0;i<10;i++){
			//faz a media do aprendizado anterior
			//0 porque quantidade de testes (se for random, fica 10 aqui)
			criaArquivoMediaAprendizado("log/dns/_bruto_zero_0_", 0, "log/dns/_bruto_aleatoria_00_MEDIAaprendizado.csv", "_bruto", String.valueOf(i));
			criaArquivoMediaAprendizado("log/dns/_bruto_aleatoria_0_", 0, "log/dns/_bruto_primeiraEntraa_0_MEDIAaprendizado.csv", "_bruto", String.valueOf(i));
			criaArquivoMediaAprendizado("log/dns/_bruto_primeiraEntrada_0_", 0, "log/dns/_bruto_zero_0_MEDIAaprendizado.csv", "_bruto", String.valueOf(i));
			
		}*/
		System.out.println("gerou a media");
		
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
		double[][] dados = le.arquivoComHeadToMatrizDouble(arquivoLeitura+"000"+"Aprendizado.csv");
		
		for(int i=1;i<quantidadeArquivos;i++){//ja leu o primeiro
			double[][] media = le.arquivoComHeadToMatrizDouble(arquivoLeitura+i+"00"+"Aprendizado.csv");
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
	
	static void criaDesVioPadraoMatrizConfusao(String nomeArquivo, double[][] media, int numeroexecucao, String normalizacao, String inicio){
		String execucao;
		if(numeroexecucao<10){
			execucao = "0"+String.valueOf(numeroexecucao);
		}
		else{
			execucao = String.valueOf(numeroexecucao);
		}
		
		Input arquivo = new Input();
		double[][] dados0 = arquivo.arquivoToMatrizDouble(nomeArquivo+inicio+"0"+execucao+"_MatrizConfusao.csv");
		double[][] dados1 = arquivo.arquivoToMatrizDouble(nomeArquivo+inicio+"1"+execucao+"_MatrizConfusao.csv");
		double[][] dados2 = arquivo.arquivoToMatrizDouble(nomeArquivo+inicio+"2"+execucao+"_MatrizConfusao.csv");
		double[][] dados3 = arquivo.arquivoToMatrizDouble(nomeArquivo+inicio+"3"+execucao+"_MatrizConfusao.csv");
		double[][] dados4 = arquivo.arquivoToMatrizDouble(nomeArquivo+inicio+"4"+execucao+"_MatrizConfusao.csv");
		double[][] dados5 = arquivo.arquivoToMatrizDouble(nomeArquivo+inicio+"5"+execucao+"_MatrizConfusao.csv");
		double[][] dados6 = arquivo.arquivoToMatrizDouble(nomeArquivo+inicio+"6"+execucao+"_MatrizConfusao.csv");
		double[][] dados7 = arquivo.arquivoToMatrizDouble(nomeArquivo+inicio+"7"+execucao+"_MatrizConfusao.csv");
		double[][] dados8 = arquivo.arquivoToMatrizDouble(nomeArquivo+inicio+"8"+execucao+"_MatrizConfusao.csv");
		double[][] dados9 = arquivo.arquivoToMatrizDouble(nomeArquivo+inicio+"9"+execucao+"_MatrizConfusao.csv");
		
		for(int i=1;i<dados0.length;i++){// priemeira linha e primeira coluna sao iguais
			for(int j=1;j<dados0[i].length;j++){
				double variancia = Math.pow(dados0[i][j]-media[i][j], 2) + Math.pow(dados1[i][j]-media[i][j], 2) + 
						Math.pow(dados2[i][j]-media[i][j], 2) + Math.pow(dados3[i][j]-media[i][j], 2) +  Math.pow(dados4[i][j]-media[i][j], 2)
						+ Math.pow(dados5[i][j]-media[i][j], 2) + Math.pow(dados6[i][j]-media[i][j], 2) + Math.pow(dados7[i][j]-media[i][j], 2)
						+ Math.pow(dados8[i][j]-media[i][j], 2) + Math.pow(dados8[i][j]-media[i][j], 2);
				variancia = variancia/9;//10 -1
				double desvioPadrao = Math.sqrt(variancia);
				dados0[i][j] = desvioPadrao;
				System.out.print(dados0[i][j]+" ");
			}
			System.out.println();
		}
		
		Output grava = new Output();
		String[] linha = new String[1+dados0[0].length];
		linha[0]="-1";
		for(int i=0;i<dados0.length;i++){
			linha[i+1]=","+String.valueOf(i);
		}
		grava.escreveArquivo("log/dns/desviopadrao"+inicio+normalizacao+execucao+".csv", linha, false);
		
		for (int i=0;i<dados0.length;i++){
			linha[0]=String.valueOf(i)+",";
			for (int j=0;j<dados0[i].length;j++){
				linha[j+1]=String.valueOf((int)dados0[i][j])+",";//pega o valor do elemento da matriz
			}
			grava.escreveArquivo("log/dns/desviopadrao"+inicio+normalizacao+execucao+".csv", linha, true);
		}
		
	}
	static double[][] criaMediaMatrizConfusao(String nomeArquivo, int numeroexecucao, String normalizacao, String inicio){
		String execucao;
		if(numeroexecucao<10){
			execucao = "0"+String.valueOf(numeroexecucao);
		}
		else{
			execucao = String.valueOf(numeroexecucao);
		}
		Input arquivo = new Input();
		double[][] dados0 = arquivo.arquivoToMatrizDouble(nomeArquivo+inicio+"0"+execucao+"_MatrizConfusao.csv");
		double[][] dados1 = arquivo.arquivoToMatrizDouble(nomeArquivo+inicio+"1"+execucao+"_MatrizConfusao.csv");
		double[][] dados2 = arquivo.arquivoToMatrizDouble(nomeArquivo+inicio+"2"+execucao+"_MatrizConfusao.csv");
		double[][] dados3 = arquivo.arquivoToMatrizDouble(nomeArquivo+inicio+"3"+execucao+"_MatrizConfusao.csv");
		double[][] dados4 = arquivo.arquivoToMatrizDouble(nomeArquivo+inicio+"4"+execucao+"_MatrizConfusao.csv");
		double[][] dados5 = arquivo.arquivoToMatrizDouble(nomeArquivo+inicio+"5"+execucao+"_MatrizConfusao.csv");
		double[][] dados6 = arquivo.arquivoToMatrizDouble(nomeArquivo+inicio+"6"+execucao+"_MatrizConfusao.csv");
		double[][] dados7 = arquivo.arquivoToMatrizDouble(nomeArquivo+inicio+"7"+execucao+"_MatrizConfusao.csv");
		double[][] dados8 = arquivo.arquivoToMatrizDouble(nomeArquivo+inicio+"8"+execucao+"_MatrizConfusao.csv");
		double[][] dados9 = arquivo.arquivoToMatrizDouble(nomeArquivo+inicio+"9"+execucao+"_MatrizConfusao.csv");

		for(int i=1;i<dados0.length;i++){//priemira linha eh sempre igual (header)
			for( int j=1;j<dados0[i].length;j++){//priemira coluna eh sempre igual (header)
				dados0[i][j] = dados0[i][j]+dados1[i][j]+dados2[i][j]+dados3[i][j]+dados4[i][j]+dados5[i][j]
						+dados6[i][j]+dados7[i][j]+dados8[i][j]+dados9[i][j];
				dados0[i][j]= dados0[i][j]/10;
			}
		}
		
		
		
		Output grava = new Output();
		String[] linha = new String[dados0[0].length];
		grava.escreveArquivo("log/dns/mediaMatrizConfusao"+inicio+execucao+".csv", linha, false);
		
		for (int i=0;i<dados0.length;i++){
			for (int j=0;j<dados0[i].length;j++){
				
				linha[j]=String.valueOf((double)dados0[i][j])+",";//pega o valor do elemento da matriz
			}
			if (i==0){
				grava.escreveArquivo("log/dns/mediaMatrizConfusao"+inicio+execucao+".csv", linha, false);
			}
			grava.escreveArquivo("log/dns/mediaMatrizConfusao"+inicio+execucao+".csv", linha, true);
		}	
		//tem que fazer pra ele deletar a priemira linha e a primeira coluna
		return dados0;
	}
}
