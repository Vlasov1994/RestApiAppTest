import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.StatusCode;
import org.eclipse.jetty.websocket.api.WriteCallback;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import java.net.*;

public class WebSoketTest {
    public static void main(String[] args) throws Exception {

        WebSoketTest.testWebsocketConnection();
    }

    public static void testWebsocketConnection() throws Exception {
        //URI uri = URI.create("ws://echo.websocket.org");
        //URI uri = URI.create("wss://stream.binance.com:9443/ws/btcusdt@aggTrade"); // Обновление движения цены
        URI uri = URI.create("wss://stream.binance.com:9443/ws/btcusdt@depth"); // Обновление стакана

        WebSocketClient client = new WebSocketClient();
        EventSocket socket = new EventSocket();

        try {
            client.start();
            ClientUpgradeRequest request = new ClientUpgradeRequest();
            Session wsSession = client.connect(socket, uri, request).get();
            //WriteCallback writeCallback = new MyCallback();
            //wsSession.getRemote().sendString("Hello", writeCallback);
            //wsSession.getRemote().flush();
            //wsSession.getRemote().flush();
            for (int i = 1; i <= 7; i++) {
                System.out.println("Thread sleeping on 30 second; Iteration number " + i);
                Thread.sleep(30 * 1000);
            }
            wsSession.close(StatusCode.NORMAL, "I'm done");
        } finally {
            client.stop();
        }
    }
}
//174.129.224.73:80