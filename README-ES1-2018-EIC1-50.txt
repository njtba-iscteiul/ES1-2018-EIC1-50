Ao iniciar a aplicação através do jar terá que ser efetuado um registo na aplicação, caso não tenha sido efetuado um registo anteriormente. 

No registo terá que ser inserido o email pelo qual queremos consultar os emails do iscte, um username, uma password, password do email inserido anteriomente e para termos acesso a conta twitter teremos que inserir as keys e tokens da API do twitter.

O registo ao ser bem sucessido caso seja o primeiro registo será criado o ficheiro config.xml que terá os filtros para consulta da informação obtida e os dados do registo efetuado.

Após o registo será possivel efetuar login através do username e password inseridos no registo mais o token para aceder a API do facebook que neste caso será sempre necessario inserir a cada login uma vez que expira num curto periodo de tempo.

O login ao ser bem sucessido a aplicação vai efetuar as conexões necessarias que pudera demorar algum tempo dependendo se consegue efetuar todas as conexões, algumas ou nenhuma. 

Assim que seja feito o login e as conexões concluidas iremos para o painel inicial ao qual conseguimos ver os dados do utilizador logado e se conseguiu efetuar ou nao todas as conexões. Irá ser criado um ficheiro "username".json com a informação obtida pelas conexões que foram bem sucessidas. Caso nao consigamos no proximo login aceder quer seja ao email, twitter ou facebook será a este ficheiro que será carregado as informações do ultimo login efetuado em que essas conexões foram conseguidas.

Na aplicação é possivel enviar email através da barra de menu como também efetuar um tweet. Na opção info é possivel aceder a toda a informação e responder a essa mesma informação em excepção ao facebook que apenas é possivel visualizar.

Através da barra de navegação é possivel aceder também ao ficheiro config.xml ao qual e necessario saber a password para aceder, após introdução da password é possivel clicar show file e visualizar o ficheiro, é possivel efetuar alterações ao ficheiro clicando edit e guardar as alterações efetuadas ao clicar save.

Após efetuar tudo o que é pretendido é possivel efetuar logout e fechar a aplicação ou efetuar login com outra conta.