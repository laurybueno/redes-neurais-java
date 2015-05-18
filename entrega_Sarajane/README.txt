# EP-IA-redes-neurais
Repositório para o EP sobre Redes Neurais preparado para a professora Sarajane Peres da EACH-USP.
Para ter acesso a mais logs e histórico do sistema, por favor, acesse: 
https://github.com/laurybueno/EP-IA-redes-neurais/


## Sobre os projetos
    ### Pre-Processamento
        Projeto para fazer as normalizações, deletar atributos e fazer o holdout.
    ### LVQ
        Gera o logAprendizado.csv que contem a taxa de erro do treinamento e da validação durante as epocas. Também gera MatrizConfusao.csv que contem a matriz de confusão gerada pelo teste.
        Cada elemento dos arquivos .csv são separados por virgulas.
    ### MLP
        Recebe parâmetros via linha de comando e gera diversos relaórios com taxas de erro e aprendizado de uma rede neural MLP. Arquivos de log com "sum" no início são sumários gerados automaticamente após criar várias MLPs com a mesma estrutura.


## Rede MLP:
    Uso: "java Main arg1 arg2 arg3 arg4 arg5 arg6 arg7 arg8"
    Em que:
    arg1: nome do arquivo do conjunto de dados de treino
    arg2: nome do arquivo do conjunto de dados de validação
    arg3: nome do arquivo do conjunto de dados de teste
    arg4: taxa de aprendizado
    arg5: número de neurônios na camada escondida
    arg6: define se inicialização de pesos é aleatória (true/false)
    arg7: intervalo de épocas em que deverá ocorrer validação
    arg8: máximo de validações com perda de desempenho aceitas
    arg9: quantas redes neurais deverão ser criadas (e resumidas automaticamente) com os dados dados

## Rede LVQ:
    Uso: execute o Relatorio.java e siga as instruções do console.

