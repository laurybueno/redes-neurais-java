import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;


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
}
