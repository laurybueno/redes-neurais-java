import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class Input {
	//abre arquivo (nome no primeiro parametro) e retorna a linha especificada (segunto parametro) desse arquivo
	public String leituralinha(String nome, int quantidade) {
		String linha = "";
		try{
			File arquivo = new File(nome);
			FileReader fr = new FileReader(arquivo);
			BufferedReader br = new BufferedReader(fr);
			int cont = 0;//auxliar para ver a linha atual
			
			while ((cont <= quantidade) && (br.ready())) {//enquanto a linha atual for menor ou igual a a linha que eh para ser lida e eh possivel ler o arquivo
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
	public List<String> todasClassesX(String nomeArquivo, int classe){
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
		
		System.out.println("deu o shuffle");
		int comeco =0;
		int[] quantidade = calculaQuantidade(p1, p2, p3, nomeArquivo);
		
		
		String[] dados1 = dados(quantidade[0], 0, classes);
		String[] dados2 = dados(quantidade[1], quantidade[1], classes);
		String[] dados3 = dados(quantidade[2], quantidade[1]+quantidade[2], classes);
		
		for(int i=0;i<dados1.length;i++){
			System.out.println(dados1[i]);
		}
		System.out.println("vai comecar a escrever");
		escreveArquivo("treino",dados1 );
		System.out.println("escreveu o rpiemiro");
		escreveArquivo("teste", dados2);
		System.out.println("escreveu o segund0");
		escreveArquivo("validacao", dados3);
		System.out.println("parabens");
	}
	
	public void escreveArquivo(String nomeArquivo, String[] s) {
		try {
			PrintWriter writer = new PrintWriter(nomeArquivo, "UTF-8");
			for(int i = 0; i < s.length; i++) {
				writer.println(s[i]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	//informo a quantidade e a lista taotal e onde comecar. ele me retorna um arranjo de String pegando um de cada classe.
	public String[] dados (int quantidadeDados, int comeco, List[] todos){
		String[] dados = new String[quantidadeDados];
		
		Iterator<String>[] iterators= new Iterator[10];
		for(int i=0;i<iterators.length;i++){
			iterators[i] = todos[i].iterator();
		}
		
		int aux=0;
		while (aux<comeco){
			for(int i=0;i<iterators.length;i++){
				if(iterators[i].hasNext()){
					iterators[i].next();
					aux++;					
				}
			}
			if (aux<comeco){
				break;
			}
		}
		
		int contador=0;
		while(contador < quantidadeDados) {
			for(int i = 0; i < iterators.length; i++) {
				if(iterators[i].hasNext()){
					dados[contador]=iterators[i].next();
					contador++;
				}
			}
			if (contador < quantidadeDados){
				break;
			}
		}
		return dados;
	}
	
	public List[] preencheClasse(String nomeArquivo) {
		
		List[] classe = new List[10];
		for(int i = 0; i < 10; i++) {
			System.out.println("preenche Classe "+i);
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