package pages;

import util.DSL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CadastroPage {
	
	private DSL dsl;
	
	public CadastroPage(WebDriver driver) {
		dsl = new DSL(driver);
	}

	public void adicionarNome(String nome) {
		dsl.escrever("name", nome);
	}
	
	public void adicionarEmail(String email) {
		dsl.escrever("email", email);
	}
	
	public void adicionarSenha(String senha) {
		dsl.escrever("password", senha);
	}
	
	public String acessarId() {
		return dsl.obterTextoCampo("tdUserId1");
	}
	
	public String acessarNome() {
		return dsl.obterTextoCampo("tdUserName1");
	}
	
	public String acessarEmail() {
		return dsl.obterTextoCampo("tdUserEmail1");
	}
	
	public String acessarUsuarios() {
		return dsl.obterTextoCampo(By.xpath("//h2[contains(@class, 'table-title')]"));
	}
	
	public String acessarErro() {
		return dsl.obterTextoCampo(By.xpath("//p[contains(@class, 'error')]"));
	}
	
	public void cadastrar() {
		dsl.clicar("register");
	}
	
	public void excluir() {
		dsl.clicar("removeUser1");
	}
	
	public boolean verificarUsuarios() {
		return dsl.campoExiste(By.xpath("//h2[contains(@class, 'table-title')]"));
	}
}