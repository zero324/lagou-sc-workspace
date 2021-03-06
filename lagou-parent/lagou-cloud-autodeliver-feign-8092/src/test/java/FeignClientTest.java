import com.lagou.edu.AutodeliverApplication8092;
import com.lagou.edu.service.ResumeFeignClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = AutodeliverApplication8092.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class FeignClientTest {
    @Autowired
    private ResumeFeignClient resumeFeignClient;

    @Test
    public void testFeignClient(){
        Integer defaultResumeByUserId = resumeFeignClient.getDefaultResumeByUserId(100000l);
        System.out.println(defaultResumeByUserId);
    }
}
