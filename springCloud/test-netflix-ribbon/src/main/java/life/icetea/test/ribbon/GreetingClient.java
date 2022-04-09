///*
//package life.icetea.test.ribbon;
//
//import com.netflix.client.ClientFactory;
//import com.netflix.client.http.HttpRequest;
//import com.netflix.client.http.HttpResponse;
//import com.netflix.niws.client.http.RestClient;
//import com.netflix.config.ConfigurationManager;
//import com.netflix.niws.client.http.RestClient;
//
//public class GreetingClient {
//
//    public static void main(String[] args) throws Exception {
//        ConfigurationManager.getConfigInstance().setProperty(
//                "greeting-service.ribbon.listOfServers", "localhost:8080,localhost:8088");
//
//        RestClient client = (RestClient) ClientFactory.getNamedClient("greeting-service");
//
//        HttpRequest reuqest = HttpRequest.newBuilder()
//                .uri("/greeting/sayHello/leo")
//                .build();
//
//        for (int i = 0; i < 10; i++) {
//            HttpResponse response = client.executeWithLoadBalancer(request);
//            String result = response.getEntity(String.class);
//            System.out.println(result);
//        }
//    }
//
//}*/
