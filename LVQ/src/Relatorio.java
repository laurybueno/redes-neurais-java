import java.util.Scanner;

//classe criada para gerar todos os arquivos necessarios ( logs ) para o relatorio
public class Relatorio {
	public static void main (String[] arg){
		//MEDIDAS QUE DEFINEM A "CARA" DA LVQ - inicio//
		Scanner sc = new Scanner(System.in);
		System.out.println("digite o numero de epocas:");
		int numeroEpoca = sc.nextInt();
		System.out.println("digite a taxa de aprendizado inicial:");
		double taxaDeAprendizado = sc.nextDouble();
		System.out.println("digite o valor que reduz a taxa de aprendizado");
		double reducaoAprendizado = sc.nextDouble();
		System.out.println("digite o valor minimo que a taxa de aprendizado pode chegar");
		double valorMinimo = sc.nextDouble();
		System.out.println("digite o numero maximo de pioras");
		int maxPiora = sc.nextInt();
		System.out.println("digite o nome do arquivo de treino");
		String nomeTreino = sc.next();
		System.out.println("digite o nome do arquivo de validacao");
		String nomeValidacao = sc.next();
		System.out.println("digite o nome do arquivo de teste");
		String nomeTeste = sc.next();
		System.out.println("digite o numero de neuronios por classe");
		int neuronios = sc.nextInt();
		System.out.println("digite a inicialização dos pesos");
		System.out.println("0 para zero, 1 para aleatoria e qualquer outra coisa para primeira entrada");
		int inicial = sc.nextInt();
		String inicio =  "primeiraEntrada";
		if(inicial ==0){
			inicio = "zero";
		}
		else if(inicial ==1){
			inicio = "aleatoria";
		}
		//MEDIDAS QUE DEFINEM A "CARA" DA LVQ - fim// (ex:)

		
		executaLVQ(numeroEpoca, taxaDeAprendizado, reducaoAprendizado, maxPiora, valorMinimo, nomeTreino, nomeValidacao, nomeTeste, neuronios, inicio);
	}
	

	static void executaLVQ(int numeroEpoca, double taxaDeAprendizado, double reducaoAprendizado, int maxPiora, double valorMinimo, String nomeTreino, String nomeValidacao, String nomeTeste, int neuronios, String inicio){

		Log log = new Log();
		log.criaHead("log");


		//Objeto responsavel por inicializar os pesos (neuronios de saida) e por alinhar os dados de teste recebidos da
		//entrada.
		//Recebe arquivo de dados de treinamento (ex: "entrada.txt"), e numero de neuronios de saida por cada classe (ex:"1")
		Inicializa inicializa = new Inicializa(nomeTreino,nomeValidacao, neuronios, inicio);//coloca arquivo de treino
		//Inicializa inicializa = new Inicializa("../dados/houldout/treino.csv","../dados/testes_brutos/validacao.csv", neuronioSaida, parametroInicio);
		//MEDIDAS QUE DEVINEM A "CARA" DA LVQ - inicio//


		//Objeto que da inicio ao corpo da LVQ. Recebendo como parametro um objeto Inicializa, para inicializar seus pesos
		//e dados de entrada, alem de medidas que iram definir a cara da rede (numero fixo que as iteracoes podem chegar, taxa de Aprendizado, 
		//taxa de reducao do Aprendizado e valor minimo que a taxa de reducao pode chegar)
		LVQ lvq1 = new LVQ(inicializa, numeroEpoca, taxaDeAprendizado, reducaoAprendizado, valorMinimo, maxPiora);
		lvq1.Aprendizado("log");
		double[] respostas = lvq1.Teste(nomeTeste);
		MatrizConfusao confusao = new MatrizConfusao();
		confusao.adicionaMatriz(nomeTeste, respostas);
		confusao.gravaMAtrizConfusao("MatrizConfusao.csv");
	}
	
	//funcao para executar a LVQ com os parametros dados uma determinada quantidade de vezes criando os logs
	static void executaLVQ(String normalizacao, String parametroInicio, int neuronioSaida, double taxaDeAprendizado, double reducaoAprendizado, int maxPioras,int quantidadeVezes, String adicional, String numeroHouldout){
		for(int cont=0;cont<quantidadeVezes;cont++){//contador de vezes que testou
			Log log = new Log();
			String pasta = "log/lucas/";
			log.criaHead(pasta+normalizacao+"_"+parametroInicio+"_"+cont+"_"+adicional);
			
			
			//Objeto responsavel por inicializar os pesos (neuronios de saida) e por alinhar os dados de teste recebidos da
			//entrada.
			//Recebe arquivo de dados de treinamento (ex: "entrada.txt"), e numero de neuronios de saida por cada classe (ex:"1")
			Inicializa inicializa = new Inicializa("../dados/holdout/treino"+normalizacao+numeroHouldout+".csv","../dados/holdout/validacao"+normalizacao+numeroHouldout+".csv", neuronioSaida, parametroInicio);//coloca arquivo de treino
			//Inicializa inicializa = new Inicializa("../dados/houldout/treino.csv","../dados/testes_brutos/validacao.csv", neuronioSaida, parametroInicio);
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
			//String nomeArquivoTeste ="../dados/testes_brutos/teste.csv";
			double[] respostas = lvq1.Teste(nomeArquivoTeste);
			MatrizConfusao confusao = new MatrizConfusao();
			confusao.adicionaMatriz(nomeArquivoTeste, respostas);
			confusao.gravaMAtrizConfusao(pasta+normalizacao+"_"+parametroInicio+"_" +cont+"_"+adicional+"_MatrizConfusao.csv");
		}
	}
	
	
	//funcao para calcualr a meida de duas matrizes, usado apara tirar a meida de todos os randomicos
	static double[][] mediaMatriz (double[][] matriz, double[][] media){
		try{for(int i=0;i<media.length;i++){
			for(int j=0;j<media[i].length;j++){
				media[i][j]=media[i][j]+matriz[i][j];
				media[i][j] = media[i][j]/2;
			}
		}
		}
		catch(Exception e){
			for(int i=0;i<matriz.length;i++){
				for(int j=0;j<matriz[i].length;j++){
					matriz[i][j]=matriz[i][j]+media[i][j];
					matriz[i][j] = matriz[i][j]/2;
				}
			}
			return matriz;
		}
		return media;
	}
	
	static void criaArquivoMediaAprendizado(String arquivoLeitura, int quantidadeArquivos, String arquivoGravacao, String normalizacao,String numeroHouldout){
		Input le = new Input();
		double[][] dados = le.arquivoComHeadToMatrizDouble(arquivoLeitura+"00"+numeroHouldout+"Aprendizado.csv");
		
		for(int i=1;i<quantidadeArquivos;i++){//ja leu o primeiro
			double[][] media = le.arquivoComHeadToMatrizDouble(arquivoLeitura+i+"0"+numeroHouldout+"Aprendizado.csv");
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
				
			}
		}
		
		Output grava = new Output();
		String[][] linha = new String[dados0.length][dados0[0].length];
		
		for (int i=0;i<dados0.length;i++){
			for (int j=0;j<dados0[i].length;j++){
				
				linha[i][j]=String.valueOf((double)dados0[i][j])+",";//pega o valor do elemento da matriz
			}
		}	
		
		grava.escreveArquivo("log/lucas/desviopadrao"+inicio+normalizacao+execucao+".csv", linha[0], false);
		for(int i=1;i<linha.length;i++){
			grava.escreveArquivo("log/lucas/desviopadrao"+inicio+normalizacao+execucao+".csv", linha[i], true);
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
		String[][] linha = new String[dados0.length][dados0[0].length];
		
		for (int i=0;i<dados0.length;i++){
			for (int j=0;j<dados0[i].length;j++){
				
				linha[i][j]=String.valueOf((double)dados0[i][j])+",";//pega o valor do elemento da matriz
			}
		}	
		
		grava.escreveArquivo(nomeArquivo+"Media"+inicio+execucao+".csv", linha[0], false);
		for(int i=1;i<linha.length;i++){
			grava.escreveArquivo(nomeArquivo+"Media"+inicio+execucao+".csv", linha[i], true);
		}
		
		//tem que fazer pra ele deletar a priemira linha e a primeira coluna
		return dados0;
	}
}
