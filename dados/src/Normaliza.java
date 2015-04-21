import javax.swing.text.AbstractDocument.LeafElement;
import java.util.Arrays;


public class Normaliza {
	
	//funcao para fazer a normalizacao usando a tecnica Z score
	//recebe como parametro o nome do arquivo em que se quer normalizar e o nome do arquivo onde vai salvar isso
	//DANGER: essa funcao nao normaliza a ultima coluna do arquivo
	public void zScore(String nomeArquivoLeitura, String NomeArquivoGravacao){
		Input arquivo = new Input();
		//le arqquivo e passa para uma matriz de double
		double[][] dados = arquivo.arquivoToMatrizDouble(nomeArquivoLeitura);
		//calcula a media da coluna
		double[] medias = new double[dados[0].length];
		for(int i=0;i<medias.length;i++){
			medias[i] = mediaColuna(dados, i);
		}
		
		//calcula o desvio padrao da coluna
		double[] desvioPadrao = new double[dados[0].length];
		for(int i=0;i<desvioPadrao.length;i++){
			desvioPadrao[i] = desvioPadraoColuna(dados, i, medias[i]);
		}
		//se desvio padrao for zero, bota null
		//(atributo-media)/desvio padrao
		for(int i=0;i<dados.length;i++){
			for (int j=0;j<dados[i].length-1;j++){//nao nromaliza a ultima coluna (classe)
				if(desvioPadrao[j]!=0){
					dados[i][j]= (dados[i][j]-medias[j])/desvioPadrao[j];
				}
				else{
					dados[i][j]=0;
				}
			}
		}
		//passa de double[][] para string[] para poder gravar
		String[] normalizado = new String[dados.length];

		for(int i=0;i<dados.length;i++){
			normalizado[i] = Arrays.toString(dados[i]);
			normalizado[i] = normalizado[i].substring(1);//tira colchetes do inicio
			normalizado[i] = normalizado[i].substring (0, normalizado[i].length() - 1); //tira colchetes do fim
		}
		
		//grava no disco
		Output grava = new Output();
		grava.escreveArquivo(NomeArquivoGravacao, normalizado);
	}
	
	//recebe como parametro uma matriz de double contendo os dados e a coluna a qual quer calcular a media
	//retorna a media daquela coluna
	public double mediaColuna(double[][] dados, int coluna){
		int quantidadeLinhas = dados.length;
		double somaTotalColuna =0;
		for (int i=0;i<dados.length;i++){
			somaTotalColuna = somaTotalColuna + dados[i][coluna];
		}
		double media = somaTotalColuna/quantidadeLinhas;
		return media;
	}
	
	//calcula o desvio padrao da coluna
	public double desvioPadraoColuna(double[][] dados, int coluna, double mediaColuna){
		//calcula a variancia da coluna
		double variancia = varianciaColuna(dados, coluna, mediaColuna);
		//retorna raiz quadrada positiva da variancia
		double desvioPadrao = Math.sqrt(variancia);
		return desvioPadrao;
	}
	
	//recebe como parametro uma matriz de double contendo os dados, a coluna  e a media da coluna qual se quer descobrir a variancia 
	//calcula a variancia populacional da coluna
	public double varianciaColuna(double[][] dados, int coluna, double mediaColuna){
		int quantidadeElementos = dados.length;
		double variancia =0;
		for(int i=0;i<dados.length;i++){
			variancia = variancia + Math.pow((dados[i][coluna]- mediaColuna), 2);
		}
		variancia = variancia/quantidadeElementos;
		return variancia;
	}
}
