package icetea.test.nettysocketio.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import icetea.test.nettysocketio.config.PushMessage;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class ClientUser1 {
    public static void main(String args[]) {
        String use1 = "user1";
        Socket socket = null;
        try {
            socket = IO.socket("http://localhost:9098?no=" + use1);

            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    System.out.println("connect");

                }

            }).on("OnMSG", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    JSONObject obj = (JSONObject) args[0];
                    System.out.println(obj.toString());
                    System.out.println("OnMSG");
                }

            }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    System.out.println("EVENT_DISCONNECT");

                }

            });
            socket.connect();

            PushMessage msgBean = new PushMessage();
            msgBean.setContent(use1);
            msgBean.setContent("nnnnn");
            JSONObject obj = new JSONObject(msgBean);

            socket.emit("broadcast event", obj);

            ObjectMapper objectMapper = new ObjectMapper();
            String mess = null;
            try {
                mess = objectMapper.writeValueAsString(msgBean);
                System.out.println(mess);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            socket.emit("OnMSG", obj);

            try {
                Thread.sleep(20 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            boolean flag = socket.connected();
            System.out.println(flag);

            socket.disconnect();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }
}
