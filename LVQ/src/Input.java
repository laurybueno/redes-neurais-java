import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class Input {
	//abre arquivo (nome no primeiro parametro) e retorna a linha especificada (segunto parametro) desse arquivo
	public String leituralinha(String nome, int posicao) {
		String linha = "";
		try{
			File arquivo = new File(nome);
			FileReader fr = new FileReader(arquivo);
			BufferedReader br = new BufferedReader(fr);
			int cont = 0;//auxliar para ver a linha atual
			
			while ((cont <= posicao) && (br.ready())) {//enquanto a linha atual for menor ou igual a a linha que eh para ser lida e eh possivel ler o arquivo
				linha = br.readLine();//le o arquivo e vai para a proxima linha
				cont++;//soma um no auxiliar para ver a linha atual
			}
			br.close();
			fr.close();
		}
		catch (Exception e){
			System.err.println(e);
		}
		return linha;
	}
	
	//tira as virgulas da string(parametro) e coloca cada atributo em um arranjo de double
	public double[] StringToDouble (String linha){
		String[] semVirgula = linha.split(",");//tira as virgulas e transforma em um arranjo de string
		double[] atributos = new double[semVirgula.length];
		for(int i=0;i<semVirgula.length;i++){//percorre todo o array de String
			atributos[i]= Double.parseDouble(semVirgula[i]);//transforma cada string do aray de string em um double e coloca no array de double
		}
		return atributos;
		
	}
	
	//recebe como parametro o nome do arquivo e retorna uma amtriz de double contendo os dados do arquivo
	public double[][] arquivoToMatrizDouble(String nomeArquivo){
		String s = leituralinha(nomeArquivo, 0);
		int quantidadeLinhas = tamanho(nomeArquivo)-1;
		double[][] dados = new double[quantidadeLinhas][s.length()];
		dados[0] = StringToDouble(s);
		for(int i=1;i<dados.length;i++){
			s = leituralinha(nomeArquivo, i);
			dados[i] = StringToDouble(s);
		}
		return dados;
	}
	
	//recebe como parametor o nome do arquivo e retorna a quantidade de linahs do memso
	int tamanho (String nome){
		int tamanho =0;
		try{
			File arquivo = new File(nome);
			LineNumberReader lnr = new LineNumberReader(new FileReader(arquivo));
			lnr.skip(Long.MAX_VALUE);
			tamanho = lnr.getLineNumber();
			tamanho++;//pois ele comeca a contar do 0
			
		}
		catch(Exception e){
			System.err.println(e);
			return 0;
		}
		return tamanho;
	}
	
	//metodo para pegar os dados do arquivo e passar para uma matriz de double
	// recebe como parametro o nome do arquivo e retorna a matriz de double com os dados do arquivo
	double[][] matrizDados(String nomeArquivo){
		int tamanho = tamanho(nomeArquivo); //descobre a quantidade de linhas do arquivo
		String linha = leituralinha(nomeArquivo, 0);// pega a primeira linha do arquivo
		double[] ponto = StringToDouble(linha);// passa a primeira linha do arquivo para um array de double
		double[][] matrizDados = new double[tamanho][ponto.length];//cria a matriz
		for(int i=0;i<matrizDados.length;i++){//percorre as linhas da matriz de retorno
			if(i!=0){ // se for diferente de 0 (pq na primeira linahe le ja fez isso antes de entrar no laco)
				linha = leituralinha(nomeArquivo, i); //pega a string contendo a linha
				ponto = StringToDouble(linha); //tranforma a string em um array de double
			}
			for(int j=0;j<matrizDados[i].length;j++){//percorre as colunas da matriz de retorno
				matrizDados[i][j] = ponto[j];//preenche a matriz de retorno
			}
			
		}
		return matrizDados;
	}
	
	//funcao parar criar um arranjo de lista
	
	//funcao que recebe como parametro o nome do arquivo e retorna todos os registros da classe recebida como parametro
	//retorna uma lista de strings
	public List<String> todasClassesX(String nomeArquivo, int numero){
		char classe =Character.forDigit(numero, 10);
		List<String> classeX = new ArrayList<String>();
		int tamanho = tamanho(nomeArquivo);
		for(int i=0;i<tamanho;i++){
			String linha = leituralinha(nomeArquivo, i);
			if(linha.charAt(linha.length()-1)==classe){//se a ultima letra for o numero da classe
				classeX.add(linha);
			}
		}
		return classeX;
	}
	
	public void funcaoIntegradora(String nomeArquivo, int p1, int p2, int p3){
		
		List[] classes = preencheClasse(nomeArquivo);
		
		for(int i=0;i<classes.length;i++){
			classes[i] = shuffle(classes[i]);
		}

		int[] quantidade = calculaQuantidade(p1, p2, p3, nomeArquivo);
		
		
		String[] dados = dados(tamanho(nomeArquivo), classes);
		
		
		
		String[] dados1 = cortaString(0, quantidade[0], dados);
		String[] dados2 = cortaString(quantidade[0],quantidade[1],dados);
		String[] dados3 = cortaString(quantidade[1], quantidade[2], dados);
		
		
		Output grava = new Output();
		grava.escreveArquivo("treino.txt",dados1 );
		grava.escreveArquivo("teste.txt", dados2);
		grava.escreveArquivo("validacao.txt", dados3);

	}
	
	String[] cortaString(int inicial, int tamanho, String[] total){
		String[] nova=new String[tamanho];

		for(int i=0;i<nova.length;i++){
			nova[i]= total[inicial];
			inicial++;
		}
		return nova;
	}
	
	
	
	public String[] listToArray(List dados){
		String [] retorno = (String[]) dados.toArray(new String[dados.size()]);

		return retorno;
	}
	
	public String[] concat(String[] a, String[] b) {
		   int aLen = a.length;
		   int bLen = b.length;
		   String[] c= new String[aLen+bLen];
		   System.arraycopy(a, 0, c, 0, aLen);
		   System.arraycopy(b, 0, c, aLen, bLen);
		   return c;
		}
	
	//informo a quantidade e a lista total e onde comecar. ele me retorna um arranjo de String pegando um de cada classe.
	public String[] listArrayToArray(List[] todos, int quantidadedados){
		String[] dados = new String[quantidadedados];
		for(int i=0;i<todos.length;i++){
			String[] classe = listToArray(todos[i]);
			dados = concat(dados, classe); 
		}
		return null;
		
	}
	
	//informo a quantidade e a lista total . ele me retorna um arranjo de String pegando um de cada classe.
	public String[] dados (int quantidadeDados, List[] todos){
		String[] novo = new String[quantidadeDados];
		
		String[] dados0 = listToArray(todos[0]);
		String[] dados1 = listToArray(todos[1]);
		String[] dados2 = listToArray(todos[2]);
		String[] dados3 = listToArray(todos[3]);
		String[] dados4 = listToArray(todos[4]);
		String[] dados5 = listToArray(todos[5]);
		String[] dados6 = listToArray(todos[6]);
		String[] dados7 = listToArray(todos[7]);
		String[] dados8 = listToArray(todos[8]);
		String[] dados9 = listToArray(todos[9]);
		
		int aux=0;
		for(int i=0;i<novo.length;aux++){	
			if(aux<dados0.length){
				novo[i]=dados0[aux];
				i++;
			}
			
			if(aux<dados1.length){
				novo[i]=dados1[aux];
				i++;
			}
			
			if(aux<dados2.length){
				novo[i]=dados2[aux];
				i++;
			}
			if(aux<dados3.length){
				novo[i]=dados3[aux];
				i++;
			}
			if(aux<dados4.length){
				novo[i]=dados4[aux];
				i++;
			}
			if(aux<dados5.length){
				novo[i]=dados5[aux];
				i++;
			}
			if(aux<dados6.length){
				novo[i]=dados6[aux];
				i++;
			}
			if(aux<dados7.length){
				novo[i]=dados7[aux];
				i++;
			}
			if(aux<dados8.length){
				novo[i]=dados8[aux];
				i++;
			}
			if(aux<dados9.length){
				novo[i]=dados9[aux];
				i++;
			}
		}
		
		return novo;
	}
	
	
	//retorna um arranjo de lista. Cada elemento do aranjo contem uma lsita contendo todos os registros de uma determinada classe
	//Esta ordenado por classes
	public List[] preencheClasse(String nomeArquivo) {
		
		List[] classe = new List[10];
		for(int i = 0; i < 10; i++) {
			classe[i] = todasClassesX(nomeArquivo, i);
		}
		return classe;
	}
	
	public List<String> shuffle(List classes) {
		Collections.shuffle(classes);
		return classes;
	}


	//funcao para reetornar um arranjo de String contendo N quantidade (recebe como parametro) de uma clase
	//recebe como parametro uma lista de String com as linhas ja aleatorias
	public String[] registrosClasseX(List<String> classe, int quantidade){
		String[] registros = new String[quantidade];
		Iterator<String> it = classe.iterator();
		int cont=0;
		while(it.hasNext() && cont<quantidade){
			registros[cont]=it.next();
			cont++;
		}
		return registros;
	}
	
	

	
	//recebe como parametro a porcentagem (de 0 a 100) e retorna a quantidade de registros com essa porcentagem dentro de um arranjo de int
	public int[] calculaQuantidade(int p1, int p2,int p3, String nomeArquivo){
		int[] quantidade = new int[3];
		int tamanho = tamanho(nomeArquivo);
		if ((tamanho*p1)%100==0 && (tamanho*p2)%100==0 && (tamanho*p3)%100==0){//se nao precisa arredondar
			quantidade[0] = (tamanho*p1)/100;
			quantidade[1] = (tamanho*p2)/100;
			quantidade[2] = (tamanho*p3)/100;
		}
		else{
			quantidade[0]=(tamanho*p1)/100;
			quantidade[1]=(tamanho*p2)/100;
			quantidade[2] = (tamanho*p3)/100+1;
			
		}
		return quantidade;
	}
	
	
}