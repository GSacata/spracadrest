package example.spracadrest;

// import org.junit.jupiter.api.Test;
// import static org.assertj.core.api.Assertions.assertThat;

// public class CashCardJsonTest {
//     // @Test
//     // void myFirstTest() {
//     //    assertThat(1).isEqualTo(42);
//     // }

    
// }

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.core.io.Resource;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class CashCardJsonTest {

    @Autowired
    private JacksonTester<CashCard> json;

    @Value("file:../expected.json")
    Resource resourceFile;

    @Test
    void cashCardSerializationTest() throws IOException {
        CashCard cashCard = new CashCard(99L, 123.45);
        assertThat(json.write(cashCard)).isStrictlyEqualToJson("testresources/expected.json");
        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.id")
                .isEqualTo(99);
        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.amount");
        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.amount")
             .isEqualTo(123.45);
    }
}
