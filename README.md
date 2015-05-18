# EP-IA-redes-neurais
Repositório para o EP sobre Redes Neurais preparado para a professora Sarajane Peres da EACH-USP.


## Rede MLP:
    Uso: "java Main arg1 arg2 arg3 arg4 arg5 arg6"
    Em que:
    arg1: nome do arquivo do conjunto de dados de treino
    arg2: nome do arquivo do conjunto de dados de validação
    arg3: nome do arquivo do conjunto de dados de teste
    arg4: taxa de aprendizado inicial
    arg5: número de neurônios na camada escondida
    arg6: define se inicialização de pesos é aleatória (true/false)

## Rede LVQ:
    Uso: execute o Relatorio.java e siga as instruções do console.
    
    
## Sobre os projetos
    ### Pre-Processamento
        Projeto para fazer as normalizações, deletar atributos e fazer o holdout.
    ### LVQ
        Gera o logAprendizado.csv que contem a taxa de erro do treinamento e da validação durante as epocas. Também gera MatrizConfusao.csv que contem a matriz de confusão gerada pelo teste.
        Cada elemento dos arquivos .csv são separados por virgulas.
    ### MLP
