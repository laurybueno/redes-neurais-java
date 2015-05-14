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
		int quantidadeLinhas = tamanho(nomeArquivo);
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
			if(linha.charAt(linha.length()-1)==classe){//se a ultima letra for o numero da classe por causa do ".0"
				classeX.add(linha);
			}
		}
		return classeX;
	}
	
	public void funcaoIntegradora(String nomeArquivo, int pTreino, int pTeste, int pValidacao, String normalizacao){
		
		List[] classes = preencheClasse(nomeArquivo); //faz um arranjo de listas. Cada lista contem todos os dados de uma classe
		System.out.println("passou pra lista");
		for(int i=0;i<classes.length;i++){//embaralha as classes
			classes[i] = shuffle(classes[i]);
		}

		int[] quantidade = calculaQuantidade(pTreino, pTeste, pValidacao, nomeArquivo);//guarda em um arranjo a quantidade de linhas para treino, teste e validacao
		

		String[] dados = dados(tamanho(nomeArquivo), classes);//pega um arranjo de string ordenado um dado de cada classe
		
		
		
		String[] dados1 = cortaString(0, quantidade[0], dados);// pega X primeiros itens do arranjo total
		String[] dados2 = cortaString(quantidade[0],quantidade[1],dados);
		String[] dados3 = cortaString(quantidade[1], quantidade[2], dados);
		
		
		Output grava = new Output();
		grava.escreveArquivo("treino"+normalizacao+".csv",dados1 , false);
		System.out.println("criado treino");
		grava.escreveArquivo("teste"+normalizacao+".csv", dados2, false);
		System.out.println("criado teste");
		grava.escreveArquivo("validacao"+normalizacao+".csv", dados3, false);
		System.out.println("criado validacao");

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
	
	//escreve um arquivo para cada classe contendo todos os registros dessa classe
	public void escreveArquivoClasses(List[] todos){
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
		
		Output grava = new Output();
		grava.escreveArquivo("classe0.csv", dados0, false);
		grava.escreveArquivo("classe1.csv", dados1, false);
		grava.escreveArquivo("classe2.csv", dados2, false);
		grava.escreveArquivo("classe3.csv", dados3, false);
		grava.escreveArquivo("classe4.csv", dados4, false);
		grava.escreveArquivo("classe5.csv", dados5, false);
		grava.escreveArquivo("classe6.csv", dados6, false);
		grava.escreveArquivo("classe7.csv", dados7, false);
		grava.escreveArquivo("classe8.csv", dados8, false);
		grava.escreveArquivo("classe9.csv", dados9, false);	
	}
	
	
	//informo a quantidade e a lista total ele me retorna um arranjo de String pegando um de cada classe.
	public String[] dados (int quantidadeDados, List[] todos){
		escreveArquivoClasses(todos);
		String[] novo = new String[quantidadeDados];
		
		int cont=0;
		for(int i=0;i<quantidadeDados;i++){
			String s = leituralinha("classe"+cont+".csv", i);
			if(s!=null){
				novo[i]=s;
			}
			else{
				i--;
			}
			cont++;
			if(cont==10){
				cont=0;
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