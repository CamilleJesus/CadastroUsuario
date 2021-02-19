package passos;

import pages.CadastroPage;

import java.util.regex.Pattern;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Mas;
import cucumber.api.java.pt.Quando;

public class CadastroPassos {
	
	private WebDriver driver;
	private CadastroPage page;

	@Dado("^Abro o navegador na tela de cadastro$")				
    public void abro_o_navegador_na_tela_de_cadastro() throws Throwable							
    {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://prova.stefanini-jgr.com.br/teste/qa/");
		page = new CadastroPage(driver);
    }		

    @E("^Preencho os campos Nome (.*) Email (.*) e Senha (.*)$")					
    public void preencho_os_campos_Nome_Email_e_Senha(String nome, String email, String senha) throws Throwable 							
    {		
    	page.adicionarNome(nome);
		page.adicionarEmail(email);
		page.adicionarSenha(senha);
    }	
    
    @Quando("^Clico no botao Cadastrar$")					
    public void clico_no_botao_cadastrar() throws Throwable 							
    {		
    	page.cadastrar();
    }

    @Entao("^Usuario e cadastrado (.*) (.*)$")					
    public void usuario_e_cadastrado(String nome, String email) throws Throwable 							
    {
    	Assert.assertEquals("Usuários cadastrados", page.acessarUsuarios());
    	Assert.assertEquals("1", page.acessarId());
    	Assert.assertEquals(nome, page.acessarNome());
    	Assert.assertEquals(email, page.acessarEmail());
    }	
    
    @Entao("^Usuario nao e cadastrado (.*) (.*) (.*)$")					
    public void usuario_nao_e_cadastrado(String nome, String email, String senha) throws Throwable 							
    {    	
    	if(nome.isEmpty()) 
    		Assert.assertEquals("O campo Nome é obrigatório.", page.acessarErro());
    	else if(nome.trim().split(" ").length == 1)
    		Assert.assertEquals("Por favor, insira um nome completo.", page.acessarErro());
    	else if(email.isEmpty()) 
    		Assert.assertEquals("O campo E-mail é obrigatório.", page.acessarErro());
    	else if(!Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE).matcher(email).find()) 
    		Assert.assertEquals("Por favor, insira um e-mail válido.", page.acessarErro());
    	else if(senha.isEmpty())
    		Assert.assertEquals("O campo Senha é obrigatório.", page.acessarErro());
    	else
    		Assert.assertEquals("A senha deve conter ao menos 8 caracteres.", page.acessarErro());
    }
    
    @Mas("^Usuario e excluido$")					
    public void usuario_e_excluido() throws Throwable 							
    {
    	page.excluir();
    	Assert.assertFalse(page.verificarUsuarios());
    }
    
    @After					
    public void finalizar() throws Throwable 							
    {
    	driver.quit();
    }
}