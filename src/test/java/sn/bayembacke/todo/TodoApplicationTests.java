package sn.bayembacke.todo;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


import org.springframework.context.ApplicationContext;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
@SpringBootTest
@ActiveProfiles("test")

class TodoApplicationTests {

	@Test
	void contextLoads(ApplicationContext context) {
		assertThat(context).isNotNull();
	}



}
