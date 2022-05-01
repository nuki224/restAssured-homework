import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestAssured1 {
    @DataProvider (name = "index&Country")
    public Object[][] dataProvider() {
        return new Object[][]
                {{1, "USA"},
                {5, "Hungary"}};
    }
    @Test (dataProvider = "index&Country")
    public void extractCircuitsAndValidateCountry(int index, String country) {
        String circuitID = given().when()
                .get("http://ergast.com/api/f1/2017/circuits.json")
                .then().extract().path("MRData.CircuitTable.Circuits.circuitId[" +index+ "]");
        given().pathParam("circuitId", circuitID).when()
                .get("http://ergast.com/api/f1/circuits/{circuitId}.json")
                .then().assertThat()
                .body("MRData.CircuitTable.Circuits.Location[0].country", equalTo(country));
    }
}
