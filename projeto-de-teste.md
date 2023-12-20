# Projeto de testes

Para esse trabalho será aplicado o conceito de TDD  (Test Data Driven). Dessa forma, primeiramente serão levantados os cenários a serem testados diante dos critérios e a implementação deve se seguir posteriormente. Além disso, será utilizado o conceito de Test Data Builder para facilitar a geração de objetos de entrada para os devidos cenários.

## Criando estrutura inicial a partir do teste

- Definição dos campos que configuram uma ordem (ou entrada) **quantidade**, **ticket**, **valor** e **tipoOrdem**

- Definição básica dos cenários de testes tomando como base o entendimento das regras de negócio a serem implementadas 

- Criada classe de teste **CarteiraTest** com método **enviarOrdemTest** 

- Criada classe **Ordem** que recebe os campos de entrada decididos
	
- Criada classe **Carteira** com método **enviarOrdem** que recebe uma Ordem e a armazena em uma lista do tipo **Ordem**

- Asserção que garante a adição do objeto **Ordem** recém enviado na lista da **Carteira**

```
@Test
	void enviarOrdemTest() {
		Carteira carteira = new Carteira();
		
		try {
			carteira.enviarOrdem(new Ordem("ITUB4", new BigDecimal(50.00), 100, TipoOrdem.COMPRA));
			System.out.println(carteira.getOrdens().get(0).getTicket());
			Assertions.assertTrue(!carteira.getOrdens().isEmpty());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
```

## Implementando regras de negócio

- Criada interface **IExecutador** que encapsula regras de negócio para ordens de venda e compra dentro de uma carteira
  
- Criadas as classes **ExecutadorVenda** e **ExecutadorCompra** que implementam **IExecutador**

- Criada a classe **Ativo** que guarda o estado de um ativo, preço médio e quantidade em carteira, caso a carteira de fato tenha aquele ativo

- Criado o campo **ativos** na classe **Carteira** que recebe uma lista de objetos do tipo **Ativo**

### Compra

- Criada a lógica de processamento e armazenamento de objeto **Ativo** na lista de ativos da carteira em **ExecutadorCompra**

	- Criada a lógica de atualização do preço médio de um ativo em caso de nova compra

	- Asserção que garante atualização do preço médio para um ativo existente na carteira

### Venda

- Criada a lógica de venda de um ativo, bem como sua atualização na lista de ativos da carteira em **ExecutadorVenda**

- Asserção que garante atualização das informações daquele ativo vendido na carteira

- Criação dos campos **imposto** e **impostoRetidoNaFonte**

- Implementação da lógica que calcula e guarda os valores dos impostos em seus devidos campos em **ExecutadorVenda**

- Asserção que garante o cálculo do campo **imposto**

## Provisionando testes

- Criação da classe **OrdemBuilder** que segue o padrão *Test Data Builder* para gerar objetos do tipo **Ordem**

- Provisionando método **enviarOrdemCompraTest** com suítes de teste oriundas do método **proverArgumentos**

```
@ParameterizedTest
@MethodSource("proverArgumentos")
void enviarOrdemTest(List<Ordem> ordens, BigDecimal impostoEsperado) {
	Carteira carteira = new Carteira();
	
	try {
		for (Ordem ordem : ordens) {
			carteira.enviarOrdem(ordem);
		}
		System.out.println(carteira.toString());
		Assertions.assertEquals(impostoEsperado, carteira.consolidarImpostos());
	} catch (Exception e) {
		e.printStackTrace();
	}
}
```
