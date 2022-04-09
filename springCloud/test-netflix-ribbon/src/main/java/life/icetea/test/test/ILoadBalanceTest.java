package life.icetea.test.test;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icetea
 */
public class ILoadBalanceTest {

    public static void main(String[] args) {
        ILoadBalancer balancer = new BaseLoadBalancer();

        List<Server> servers = new ArrayList<Server>();
        servers.add(new Server("localhost", 8080));
        servers.add(new Server("localhost", 8088));
        balancer.addServers(servers);

        for (int i = 0; i < 10; i++) {
            Server server = balancer.chooseServer(null);
            System.out.println(server);
        }
    }

}
