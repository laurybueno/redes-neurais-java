
public class GeraMedias {
	
	public static String [] MediaTaxaErro(String nomeArquivo){
		Input input = new Input();
		double [][] matrizLida;
		double somaErros = 0;
		double somaTodos = 0;
		String [] MediasErros = new String[18];
		
		for(int t = 0; t<18 ; t++){
			
			if(t<10){
				matrizLida = input.arquivoToMatrizDouble(nomeArquivo+"0"+t+".csv");
			}
			else{
				matrizLida = input.arquivoToMatrizDouble(nomeArquivo+t+".csv");
			}
			//soma os valores errados
			for(int i=1; i < matrizLida.length; i++){
				for(int j=1; j<matrizLida[i].length;j++){
					if(i!=j){
						somaErros += matrizLida[i][j];
					}
					somaTodos += matrizLida[i][j];
				}
			}
			
			MediasErros[t] = String.valueOf(somaErros/somaTodos)+",";
			somaErros = 0;
			somaTodos =0;
		}
		return MediasErros;
	}
	
	
	public static String [] MediasEpocas(String nomeArquivo){
		Input input = new Input();
		
		double [][] matrizLida;
		double somaEpocas = 0;
		String [] MediasEpocas = new String[18];
		
		for(int t = 0; t<18 ; t++){
			for(int h = 0; h < 10 ; h++){
				if(t<10){
					matrizLida = input.arquivoComHeadToMatrizDouble(nomeArquivo+h+"0"+t+"Aprendizado.csv");
					somaEpocas += matrizLida[matrizLida.length-1][0]; 
				}
				else{
					matrizLida = input.arquivoComHeadToMatrizDouble(nomeArquivo+h+t+"Aprendizado.csv");
					somaEpocas += matrizLida[matrizLida.length-1][0]; 
				}
			}
			MediasEpocas[t] = String.valueOf(somaEpocas/(double)10)+",";
			somaEpocas = 0;
		}
		
		return MediasEpocas;
	}
	
	public static void main(String[] args) {
		String ArqAleatorio ="log/testeLvq/_bruto_aleatoria_0_";
		String ArqPrimEntra ="log/testeLvq/_bruto_primeiraEntrada_0_";
		String ArqZero = "log/testeLvq/_bruto_zero_0_";
		
		String ArqMatrizAleatorio ="log/testeLvq/mediaMatrizConfusaoaleatoria_0_";
		String ArqMatrizPrimEntra ="log/testeLvq/mediaMatrizConfusaoprimeiraEntrada_0_";
		String ArqMatrizZero = "log/testeLvq/mediaMatrizConfusaozero_0_";
		
		String [] mediaEpocasAleatorio = MediasEpocas(ArqAleatorio);
		String [] mediaEpocasPrimEntra = MediasEpocas(ArqPrimEntra);
		String [] mediaEpocasArqZero = MediasEpocas(ArqZero);
		
		String[] mediaErrosAleatorio = MediaTaxaErro(ArqMatrizAleatorio);
		String[] mediaErrosPrimEntra= MediaTaxaErro(ArqMatrizPrimEntra);
		String[] mediaErrosArqZero = MediaTaxaErro(ArqMatrizZero);
		
		String [] medias = new String[2];
		
		Output output = new Output();
		Log log = new Log();
		
		for(int i =0; i<18; i++){
			medias[0] = mediaEpocasAleatorio[i];
			medias[1] = mediaErrosAleatorio[i];
			if(i<10){
				log.criaheadMediaEpocas("log/testeLvq/MediasEpocaErroaleatoria_0_"+"0"+i+".csv");
				output.escreveArquivo("log/testeLvq/MediasEpocaErroaleatoria_0_"+"0"+i+".csv", medias, true);
			}
			else{
				log.criaheadMediaEpocas("log/testeLvq/MediasEpocaErroaleatoria_0_"+i+".csv");
				output.escreveArquivo("log/testeLvq/MediasEpocaErroaleatoria_0_"+i+".csv", medias, true);
			}
		}
		
		for(int i =0; i<18; i++){
			medias[0] = mediaEpocasPrimEntra[i];
			medias[1] = mediaErrosPrimEntra[i];
			if(i<10){
				log.criaheadMediaEpocas("log/testeLvq/MediasEpocaErroprimeiraEntrada_0_"+"0"+i+".csv");
				output.escreveArquivo("log/testeLvq/MediasEpocaErroprimeiraEntrada_0_"+"0"+i+".csv", medias, true);
			}
			else{
				log.criaheadMediaEpocas("log/testeLvq/MediasEpocaErroprimeiraEntrada_0_"+i+".csv");
				output.escreveArquivo("log/testeLvq/MediasEpocaErroprimeiraEntrada_0_"+i+".csv", medias, true);
			}
		}
		
		for(int i =0; i<18; i++){
			medias[0] = mediaEpocasArqZero[i];
			medias[1] = mediaErrosArqZero[i];
			if(i<10){
				log.criaheadMediaEpocas("log/testeLvq/MediasEpocaErrozero_0_"+"0"+i+".csv");
				output.escreveArquivo("log/testeLvq/MediasEpocaErrozero_0_"+"0"+i+".csv", medias, true);
			}
			else{
				log.criaheadMediaEpocas("log/testeLvq/MediasEpocaErrozero_0_"+i+".csv");
				output.escreveArquivo("log/testeLvq/MediasEpocaErrozero_0_"+i+".csv", medias, true);
			}
		}
	}
}
