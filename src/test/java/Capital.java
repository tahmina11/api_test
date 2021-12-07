
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class Capital {

        String endPoint = "https://restcountries.com/v3.1/alpha/";

        @Test
        public void happyPath_code(){
            Api_interview api = new Api_interview();
            RestAssured.
                    when().get(endPoint+"col").
                    then().statusCode(200);

            String capital = api.getCapital("col");
            Assert.assertEquals("[\"Bogotá\"]", capital);
        }

        @Test
        public void negative_test(){

            Response response = given().
                    when().
                    get(endPoint+"Random");
            System.out.println(response.statusCode());
            System.out.println(response.asString());
            response.then().statusCode(400);
            assertThat(response.asString(), containsString("Bad Request"));
        }

        @Test
        public void negative_test_specific(){
            Api_interview api = new Api_interview();
            RestAssured.
                    when().get(endPoint+"rus").
                    then().statusCode(200);
            String capital = api.getCapital("rus");
            Assert.assertFalse("[\"Lima\"]".equalsIgnoreCase(capital));
        }
    }
