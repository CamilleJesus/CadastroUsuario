# language: pt

@CadastroComSucesso
Funcionalidade: Testar o cadastro de usuario
  
  Esquema do Cenario: Realizar cadastro com sucesso
  	Dado Abro o navegador na tela de cadastro
  	E Preencho os campos Nome <nome> Email <email> e Senha <senha>
  	Quando Clico no botao Cadastrar
  	Entao Usuario e cadastrado <nome> <email>
  
	  Exemplos:                  		
      | nome 								  | email																  	 | senha	  |
      | Gabriel Fernandes     | gabrielfernardes@dlh.de									 | 12345678 |
      | Emanuel Benicio Costa | emanuelbeniciocosta@rcstechnology.com.br | 87654321 |
      
@CadastroSemSucesso  
  Esquema do Cenario: Tentar realizar cadastro com campo vazio
  	Dado Abro o navegador na tela de cadastro
  	E Preencho os campos Nome <nome> Email <email> e Senha <senha>
  	Quando Clico no botao Cadastrar
  	Entao Usuario nao e cadastrado <nome> <email> <senha>
  	
	  Exemplos:                  		
      | nome 						  | email									  | senha	   |
      | 								  | gabrielfernardes@dlh.de | 12345678 |
      | Gabriel Fernandes | 										    | 12345678 |
      | Gabriel Fernandes | 										    | 12345678 |
      | Gabriel Fernandes | gabrielfernardes@dlh.de | 				 |
      | Gabriel 					| gabrielfernardes@dlh.de | 12345678 |
      | Gabriel Fernandes | gabrielfernardes			  | 12345678 |
      | Gabriel Fernandes | gabrielfernardes@ 		  | 12345678 |
      | Gabriel Fernandes | gabrielfernardes@dhl	  | 12345678 |
      | Gabriel Fernandes | gabrielfernardes@dhl.	  | 12345678 |
      | Gabriel Fernandes | gabrielfernardes@dhl.d  | 12345678 |
      | Gabriel Fernandes | gabrielfernardes@dhl.d  | 1			   |
      | Gabriel Fernandes | gabrielfernardes@dhl.d  | 1234567  |
      
@ExclusaoCadastro  
  Esquema do Cenario: Realizar exclusao de cadastro
  	Dado Abro o navegador na tela de cadastro
  	E Preencho os campos Nome <nome> Email <email> e Senha <senha>
  	Quando Clico no botao Cadastrar
  	Entao Usuario e cadastrado <nome> <email>
  	Mas Usuario e excluido
  	
	  Exemplos:                  		
      | nome 								  | email																  	 | senha	  |
      | Gabriel Fernandes     | gabrielfernardes@dlh.de									 | 12345678 |
      | Emanuel Benicio Costa | emanuelbeniciocosta@rcstechnology.com.br | 87654321 |