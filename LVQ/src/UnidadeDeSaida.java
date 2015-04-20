public class UnidadeDeSaida{
	int N; //numero de neuronios por cada classe
	//vetor de pesos dos neuronios. Como existem 10 classes, temos 10 * N.
	double [] VetorPesos = new double [10*N]; 
	
	//construtor que recebe a quantidade de neuronios por cada classe.
	public UnidadeDeSaida(int N){
		this.N = N;
	}
		
}