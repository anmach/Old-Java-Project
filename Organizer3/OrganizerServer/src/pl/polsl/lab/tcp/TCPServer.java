package pl.polsl.lab.tcp;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import pl.polsl.lab.model.Organizer;

/**
 *
 * @author Anna Mach
 * @version 1.2
 */
public class TCPServer implements Closeable {

    /**
     * Socket for connection with client
     */
    private ServerSocket serverSocket;

    /**
     * Constructor, reading port value from file .properties and creating
     * serverSocket
     *
     * @throws IOException when prot is already bind
     */
    TCPServer() throws IOException {

        try {
            InputStream input = new FileInputStream("tcpConfig.properties");
            Properties prop = new Properties();
            prop.load(input);

            serverSocket = new ServerSocket(Integer.parseInt(prop.getProperty("tcp.port")));

        } catch (IOException | NumberFormatException ex) {
            System.err.println("Error in TCPServer constructor");
        }
    }

    /**
     * The main application method
     *
     * @param args - categories to be avalible from the begining
     */
    public static void main(String args[]) {
        TCPServer tcpServer;
        Organizer organizer = new Organizer(args);

        try {
            tcpServer = new TCPServer();
            System.out.println("Server started");
            while (true) {
                Socket socket = tcpServer.serverSocket.accept();
                try (Service service = new Service(socket)) {
                    service.realize(organizer);
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Overridef method close()
     * @throws IOException - exception may occure when there will be problems with Socket
     */
    @Override
    public void close() throws IOException {
        if (serverSocket != null) {
            serverSocket.close();
        }
    }
}
