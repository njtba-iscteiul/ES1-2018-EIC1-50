Ao iniciar a aplica��o atrav�s do jar ter� que ser efetuado um registo na aplica��o, caso n�o tenha sido efetuado um registo anteriormente. 

No registo ter� que ser inserido o email pelo qual queremos consultar os emails do iscte, um username, uma password, password do email inserido anteriomente e para termos acesso a conta twitter teremos que inserir as keys e tokens da API do twitter.

O registo ao ser bem sucessido caso seja o primeiro registo ser� criado o ficheiro config.xml que ter� os filtros para consulta da informa��o obtida e os dados do registo efetuado.

Ap�s o registo ser� possivel efetuar login atrav�s do username e password inseridos no registo mais o token para aceder a API do facebook que neste caso ser� sempre necessario inserir a cada login uma vez que expira num curto periodo de tempo.

O login ao ser bem sucessido a aplica��o vai efetuar as conex�es necessarias que pudera demorar algum tempo dependendo se consegue efetuar todas as conex�es, algumas ou nenhuma. 

Assim que seja feito o login e as conex�es concluidas iremos para o painel inicial ao qual conseguimos ver os dados do utilizador logado e se conseguiu efetuar ou nao todas as conex�es. Ir� ser criado um ficheiro "username".json com a informa��o obtida pelas conex�es que foram bem sucessidas. Caso nao consigamos no proximo login aceder quer seja ao email, twitter ou facebook ser� a este ficheiro que ser� carregado as informa��es do ultimo login efetuado em que essas conex�es foram conseguidas.

Na aplica��o � possivel enviar email atrav�s da barra de menu como tamb�m efetuar um tweet. Na op��o info � possivel aceder a toda a informa��o e responder a essa mesma informa��o em excep��o ao facebook que apenas � possivel visualizar.

Atrav�s da barra de navega��o � possivel aceder tamb�m ao ficheiro config.xml ao qual e necessario saber a password para aceder, ap�s introdu��o da password � possivel clicar show file e visualizar o ficheiro, � possivel efetuar altera��es ao ficheiro clicando edit e guardar as altera��es efetuadas ao clicar save.

Ap�s efetuar tudo o que � pretendido � possivel efetuar logout e fechar a aplica��o ou efetuar login com outra conta.