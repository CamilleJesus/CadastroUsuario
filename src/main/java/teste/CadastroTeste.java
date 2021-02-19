package teste;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;		
import cucumber.api.junit.Cucumber;		

@RunWith(Cucumber.class)				
@CucumberOptions(features = "classpath:funcionalidades", glue = {"passos"})						
public class CadastroTeste 				
{		

}