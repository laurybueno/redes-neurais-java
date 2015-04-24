public class Main{
	
	public static void main(String[] args){
		
		
		//------------- TREINAMENTO ---------------//
		
		//Objeto responsavel por inicializar os pesos (neuronios de saida) e por alinhar os dados de teste recebidos da
		//entrada.
		//Recebe arquivo de dados de treinamento (ex: "entrada.txt"), e numero de neuronios de saida por cada classe (ex:"1")
		Inicializa inicializa = new Inicializa("aquiDNS.csv", 2);
		
		//MEDIDAS QUE DEVINEM A "CARA" DA LVQ - inicio//
		int numeroFixo = 100; //numero que ira restringir ate que Epoca a LVQ pode chegar (ex:100)
		double taxaDeAprendizado = 2.0; //taxa de Aprendizado (ex: 2.0)
		double reducaoAprendizado = 0.1; //valor que reduz a taxa de Aprendizado (ex: 0.1)
 		double valorMinimo=0.1; //valor minimo que a taxa de aprendizado pode chegar (ex:0.1)
		//MEDIDAS QUE DEVINEM A "CARA" DA LVQ - fim// (ex:)
		
		
		//Objeto que da inicio ao corpo da LVQ. Recebendo como parametro um objeto Inicializa, para inicializar seus pesos
		//e dados de entrada, alem de medidas que iram definir a cara da rede (numero fixo que as iteracoes podem chegar, taxa de Aprendizado, 
		//taxa de reducao do Aprendizado e valor minimo que a taxa de reducao pode chegar)
		LVQ lvq1 = new LVQ(inicializa, numeroFixo, taxaDeAprendizado, reducaoAprendizado, valorMinimo);
		lvq1.Aprendizado();
		double [][] vetoresDePesos = lvq1.vetoresDePesos; //neuronios de saida jah treinados
		
		double [][] teste  = inicializa.dadosEntrada;
	}
}
