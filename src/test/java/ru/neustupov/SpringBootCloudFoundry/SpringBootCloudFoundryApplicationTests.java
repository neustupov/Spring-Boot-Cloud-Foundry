package ru.neustupov.SpringBootCloudFoundry;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.Assert.assertTrue;

import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.neustupov.SpringBootCloudFoundry.beans.Cat;
import ru.neustupov.SpringBootCloudFoundry.data.CatRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class SpringBootCloudFoundryApplicationTests {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private CatRepository catRepository;

  @Before
  public void before() {
    Stream.of("Felix", "Haskel", "Garfild").forEach(n -> catRepository.save(new Cat(n)));
  }

  @Test
  public void catReflectedInRead() throws Exception {
    MediaType halJson = MediaType.parseMediaType("application/hal+json;charset=UTF-8");
    this.mvc
        .perform(get("/cats"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(halJson))
        .andExpect(
            mvcResult -> {
              String contentAsString = mvcResult.getResponse().getContentAsString();
              assertTrue(contentAsString.split("totalElements")[1].split(":")[1]
                  .trim().split(",")[0].equals("3"));
            }
        );
  }

}
