package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DSL {

private WebDriver driver;
	
	public DSL(WebDriver driver) {
		this.driver = driver;
	}
	
	public String obterTextoCampo(By by) {
		return driver.findElement(by).getText();
	}
	
	public String obterTextoCampo(String id) {
		return driver.findElement(By.id(id)).getText();
	}
	
	public boolean campoExiste(By by) {
		return !driver.findElements(by).isEmpty();
	}
	
	public void escrever(By by, String texto) {
		driver.findElement(by).clear();
		driver.findElement(by).click();
		driver.findElement(by).sendKeys(texto);
	}

	public void escrever(String id, String texto) {
		escrever(By.id(id), texto);
	}
	
	public void clicar(String id) {
		driver.findElement(By.id(id)).click();
	}
}