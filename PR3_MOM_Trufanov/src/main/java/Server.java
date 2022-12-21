public class Server {
    public static void main (String[] args) throws Exception {
        final Sender s = new Sender();
        final Receiver r = new Receiver();
        r.startListener();
        s.print("Сообщение MOM!");
    }
}