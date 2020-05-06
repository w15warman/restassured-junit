package framework.config;

import static io.restassured.config.HttpClientConfig.httpClientConfig;

import framework.utils.PropertiesReader;
import io.restassured.RestAssured;
import java.util.Properties;

public class RestAssuredSettings {

  private PropertiesReader propertiesReader = new PropertiesReader();
  private Properties properties = propertiesReader.loadPropertiesFile("run.properties");

  public void setEnvProperties() {
    System.out.println("Tests running at: " + properties.getProperty("url"));
    RestAssured.baseURI = properties.getProperty("url");
    RestAssured.config = RestAssured.config()
        .httpClient(httpClientConfig().reuseHttpClientInstance());
//    RestAssured.useRelaxedHTTPSValidation();
//    RestAssured.port = Integer.parseInt(properties.getProperty("port"));
  }
}
