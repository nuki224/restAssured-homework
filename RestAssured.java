import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;
import java.text.SimpleDateFormat;
import java.util.Date;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RestAssured {
    @Test
     void validateLastValue () {
        given().when()
                .get("https://chercher.tech/sample/api/product/read")
                .then()
                .body("records.name[-1]", equalTo("cherchertech"));
    }
    @Test
    void validateThatStatedTimeIsLessThanCurrentTime () {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date currentTime = new Date();
        given().when()
                .get("https://chercher.tech/sample/api/product/read")
                .then()
                .body("records.created", everyItem(lessThan(sdf.format(currentTime.getTime()+1))));
    }
    @Test
    public void bookingUpdate () {
        JSONObject request = new JSONObject();
        request.put("firstname", "James");
        request.put("lastname", "Brown");
        System.out.println((request.toString()));
        baseURI = ("https://reqres.in/api");
        given()
                .header ("Content-Type", "application/json")
                .contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(request.toString()).when()
                .put("/").then()
                .statusCode(201).log().all();
    }
}
