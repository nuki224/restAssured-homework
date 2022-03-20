import io.restassured.internal.path.xml.NodeChildrenImpl;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class RestAssuredd {
    @Test
    void validateCountAndListOfAllSnameValue() {
        NodeChildrenImpl sname = given().when()
                .get("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso/ListOfContinentsByName")
                .then().extract().path("ArrayOftContinent.tContinent.sName");
        System.out.println("count: " + sname.size());
        System.out.println("list: " + sname.list());
    }
    @Test
    void validatesWithValueOfSCodeEqualsTpAN() {
        String sName = given().when()
                .get("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso/ListOfContinentsByName")
                .then().extract().path("ArrayOftContinents.'**'.find{it.sCode=='AN'}.sName");
        System.out.println(sName);
    }
    @Test
    void validateLastValue () {
        String sName = given().when()
                .get("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso/ListOfContinentsByName")
                .then().extract().path("ArrayOftContinent.tContinent[-1].sName");
        System.out.println(sName);
    }
}