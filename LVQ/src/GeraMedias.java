
public class GeraMedias {
	
	public static String [] GeraMedias(String nomeArquivo){
		Input input = new Input();
		
		double [][] matrizLida;
		double somaEpocas = 0;
		String [] MediasEpocas = new String[18];
		
		for(int t = 0; t<18 ; t++){
			for(int h = 0; h < 10 ; h++){
				if(t<10){
					matrizLida = input.arquivoComHeadToMatrizDouble(nomeArquivo+h+"0"+t+"Aprendizado");
					somaEpocas += matrizLida[0][matrizLida[0].length-1]; 
				}
				else{
					matrizLida = input.arquivoComHeadToMatrizDouble(nomeArquivo+h+t+"Aprendizado");
					somaEpocas += matrizLida[0][matrizLida[0].length-1]; 
				}
			}
			MediasEpocas[t] = String.valueOf(somaEpocas/10);
			somaEpocas = 0;
		}
		
		return MediasEpocas;
	}
	
	public static void main(String[] args) {
		String ArqAleatorio ="_bruto_aleatoria_0_";
		String ArqPrimEntra ="_bruto_primeiraEntrada_0_";
		String ArqZero = "_bruto_zero_0_";
		
		String [] mediaEpocasAleatorio = GeraMedias(ArqAleatorio);
		String [] mediaEpocasPrimEntra = GeraMedias(ArqPrimEntra);
		String [] mediaEpocasArqZero = GeraMedias(ArqZero);

	}
}
