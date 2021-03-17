import com.lagou.edu.AutodeliverApplication8093;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest(classes = {AutodeliverApplication8093.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class AutodelliverApplicationTest {
    @Resource
    private DiscoveryClient discoveryClient;
    @Test
    public void metadatatest(){
        List<ServiceInstance> instances = discoveryClient.getInstances("lagou-server-resume");
        //从服务列表中选一个服务(负载均衡)
        ServiceInstance serviceInstance = instances.get(0);
        System.out.println("获取自定义的元数据========================>"+serviceInstance.getMetadata().get("cluster"));
    }
}
