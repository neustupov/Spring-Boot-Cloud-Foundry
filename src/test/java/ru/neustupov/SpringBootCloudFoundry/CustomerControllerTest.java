package ru.neustupov.SpringBootCloudFoundry;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.neustupov.SpringBootCloudFoundry.beans.Customer;
import ru.neustupov.SpringBootCloudFoundry.data.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class CustomerControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private CustomerRepository customerRepository;

  @Before
  public void before() {
    Stream.of(new Customer(100100L, "Ivan"),
        new Customer(100001L, "Grey"),
        new Customer(100002L, "Petr"))
        .forEach(customer -> customerRepository.save(customer));
  }

  @Test
  public void catReflectedInRead() throws Exception {
    MediaType json = MediaType.parseMediaType("application/json;charset=UTF-8");
    this.mvc
        .perform(get("/customers"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(json))
        .andExpect(
            mvcResult -> {
              String contentAsString = mvcResult.getResponse().getContentAsString();
              String actual = "[{\"id\":1,\"email\":\"Ivan\"},{\"id\":2,\"email\":\"Grey\"},{\"id\":3,\"email\":\"Petr\"}]";
              JSONAssert.assertEquals(contentAsString, actual, JSONCompareMode.LENIENT);
            }
        );
  }

}
