import org.eclipse.jetty.websocket.api.WriteCallback;

public class MyCallback implements WriteCallback {
    @Override
    public void writeFailed(Throwable throwable) {
        System.out.println("Error: " + throwable.getMessage());
    }

    @Override
    public void writeSuccess() {
        System.out.println("Write success");
    }
}
