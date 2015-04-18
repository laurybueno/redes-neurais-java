
public class LVQ {
	public static void main (String []args){
		String nomeArquivo = "pre processamento/optdigitsNormalizado.txt";
		//R = numero de atributos da base de dados que serao analisados (64)
		int M =10; //quantidade de clusters (como a base de dados analisa os 10 digitos)
		int[] vetorPesos = new int[M];
		int taxaDeAprendizado=0;
		Input arquivo = new Input();
		
		double[][] dados = arquivo.matrizDados(nomeArquivo);
		
		
		double[] centroide = centroMassa(dados);
		
		
	}
	
	//metodo para calcular a distancia euclidiana entre dois pontos
	//recebe como paramentro dois vetores e retorna a distancia euclidiana
	double distanciaEuclidiana(int[] vetor, int [] array){
		double aux=0;//auxiliar somar as distancias locais
		for (int i=0;i<vetor.length;i++){// percorre o arranjo para pegar todos os atributos
			double local = vetor[i]- array[i]; //pega a distancia local
			local = Math.pow(local, 2);// eleva a distancia local ao quadrado
			aux=aux+local;
		}
		aux =Math.sqrt(aux);//tira a raiz quadrada da soma das distancias locais
		return aux;
	}
	
	
	
	// não sei se sera usado. pensei que isso fosse ser usado para calcular centroide. Nao sei o que eh centroide.
	//metodo para descobrir a media de uma coluna
	// recebe como parametro uma matriz e a coluna desejada
	//retorna a media aritmetica
	static double mediaColuna(double[][] matriz, int coluna){
		double media =0;
		for(int i=0;i<matriz.length;i++){// percorre todas as linhas da matriz
			media = media + matriz[i][coluna];// soma todos os elementos de uma coluna
		}
		media = media/matriz.length; //divide pela quantidade de linhas
		return media;
	}
	
	//funcao para calcular o centor de massa (talvez seja a mesma coisa que o centroide)
	//recebe como parametro a matriz com os pontos e retorna um vetor com o ponto que eh o centro de massa
	static double[] centroMassa(double[][] matriz){
		double[] centroMassa = new double[matriz[0].length];
		for(int i=0;i<centroMassa.length;i++){// percorre o vetor com o centro de massa
			centroMassa[i]=mediaColuna(matriz, i);// a variavel do ponto eh igual ah media de todos os pontos daquela variavel
		}
		return centroMassa;
	}
}
