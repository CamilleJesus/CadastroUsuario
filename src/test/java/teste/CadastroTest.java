package teste;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;		
import cucumber.api.junit.Cucumber;		

@RunWith(Cucumber.class)				
@CucumberOptions(features = "src/test/resources/funcionalidades/Cadastro.feature", glue = {"passos"}, 
plugin = {"json:target/cucumber.json"})						
public class CadastroTest 				
{		

}