package passos;

import pages.CadastroPage;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.TakesScreenshot;

import org.apache.commons.io.FileUtils;

import cucumber.api.java.After;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Mas;
import cucumber.api.java.pt.Quando;

public class CadastroPassos {
  
  private WebDriver driver;
  private CadastroPage page;
  private File dir;

  @Dado("^Abro o navegador na tela de cadastro$")       
    public void abro_o_navegador_na_tela_de_cadastro() throws Throwable             
    {
    System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("start-maximized");
    options.addArguments("start-fullscreen");
    driver = new ChromeDriver(options);
    driver.get("http://prova.stefanini-jgr.com.br/teste/qa/");
    page = new CadastroPage(driver);
    }   

    @E("^Preencho os campos Nome (.*) Email (.*) e Senha (.*)$")          
    public void preencho_os_campos_Nome_Email_e_Senha(String nome, String email, String senha) throws Throwable               
    {   
      page.adicionarNome(nome);
    page.adicionarEmail(email);
    page.adicionarSenha(senha);
    capturarTela();
    } 
    
    @Quando("^Clico no botao Cadastrar$")         
    public void clico_no_botao_cadastrar() throws Throwable               
    {   
      page.cadastrar();
    }

    @Entao("^Usuario e cadastrado (.*) (.*)$")          
    public void usuario_e_cadastrado(String nome, String email) throws Throwable              
    {
      capturarTela();
      Assert.assertEquals("Usuários cadastrados", page.acessarUsuarios());
      Assert.assertEquals("1", page.acessarId());
      Assert.assertEquals(nome, page.acessarNome());
      Assert.assertEquals(email, page.acessarEmail());
    } 
    
    @Entao("^Usuario nao e cadastrado (.*) (.*) (.*)$")         
    public void usuario_nao_e_cadastrado(String nome, String email, String senha) throws Throwable              
    {     
      capturarTela();
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
    
    public void capturarTela() {
      dir = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      try {
      FileUtils.copyFile(dir, new File("target/screenshots/" + dataHoraAtual() + ".png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    }
    
    private static String dataHoraAtual() {
    String str = null;
    try {
      DateFormat formato = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
      Date data = new Date();
      str = formato.format(data);
      str = str.replace(" ", "").replaceAll("/", "").replaceAll(":", "");
    } catch (Exception e) {
    }
    return str;
  }
}