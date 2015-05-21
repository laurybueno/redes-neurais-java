A primeira linha do arquivo de pesos dá a identificação única da rede no sistema de treinamento.

Na segunda linha, temos:
[número de neurônios na camada escondida];[número de neurônios na camada de saída];[quantidade de pesos em cada neurônio escondido];[quantidade de pesos em cada neurônio de saída];

A partir da terceira linha, temos os pesos de cada neurônio.
Vale a pena observar que, em nossa implementação, a camada de entrada não chega a ser instanciada e todos os pesos da rede são registrados apenas nos neurônios escondidos e nos de saída.
O primeiro número em cada uma dessas linhas mostra a qual camada pertence o neurônio representado pela linha (1=camada escondida / 2=camada de saída).

Por fim, a primeira coluna de peso em cada uma dessas linhas se refere ao peso do viés dessa camada com o dado neurônio.
