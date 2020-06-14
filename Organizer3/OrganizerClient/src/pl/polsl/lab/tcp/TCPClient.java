package pl.polsl.lab.tcp;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Properties;
import pl.polsl.lab.exceptions.ErrorWithServerException;
import pl.polsl.lab.view.CategoriesFrame;
import pl.polsl.lab.view.ErrorFrame;
import pl.polsl.lab.view.TasksFrame;

/**
 * Class connecting with server
 *
 * @author Anna Mach
 * @version 1.2
 */
public class TCPClient implements Closeable {

    /**
     * Communication socket
     */
    private Socket clientSocket;

    /**
     * Constructor of the TCP client, getting information from .properties file and creates socket
     * @throws ErrorWithServerException 
     */
    TCPClient() throws ErrorWithServerException {
        try {
            InputStream input = new FileInputStream("tcpConfig.properties");
            Properties prop = new Properties();
            prop.load(input);

            clientSocket = new Socket(prop.getProperty("tcp.address"), Integer.parseInt(prop.getProperty("tcp.port")));

        } catch (IOException | NumberFormatException ex) {
            throw new ErrorWithServerException("Exception in TCPClient constructor. Check properties file. Check if the server is running properly");
        }
    }

    /**
     * Main method of TCPClient
     * @param argv - categories which user want to be created
     */
    public static void main(String argv[]) {
        CategoriesFrame categoriesFrame;
        TasksFrame tasksFrame = null;
        String demandForServer = "";

        try {
            TCPClient tcpClient = new TCPClient();
            DemandService service;
            service = new DemandService(tcpClient.clientSocket);
            service.reciveWelcomeMessageFromServer();

            for (String s : argv) {
                service.realize("ADD CATEGORY ;; " + s);
            }

            categoriesFrame = new CategoriesFrame(service.getInformationFromServer("GET CATEGORIES"));

            boolean doWeShowTasks = false;

            while (true) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                }

                demandForServer = categoriesFrame.getDemandForServer();
                if (demandForServer.equals("")) {
                    if (tasksFrame != null) {
                        demandForServer = tasksFrame.getDemandForServer();
                        if (!demandForServer.equals("")) {
                            tasksFrame.resetDemandForServer();
                            service.realize(demandForServer);
                        }
                    }
                } else {
                    service.realize(demandForServer);
                    categoriesFrame.resetDemandForServer();
                }

                if (categoriesFrame.doWeWantToRefreshCategories()) {
                    categoriesFrame.showFrame(service.getInformationFromServer("GET CATEGORIES"));
                    categoriesFrame.resetDoWeRefresh();
                } else if (doWeShowTasks) {
                    categoriesFrame.resetDoWeShowTasks();
                    categoriesFrame.hide();
                    tasksFrame = new TasksFrame(categoriesFrame.getChosenCategory(), service.getInformationFromServer("GET TASKS ;; " + categoriesFrame.getChosenCategory()));
                } else if (tasksFrame != null) {
                    if (tasksFrame.doWeWantToShowCategories()) {
                        tasksFrame.resetDoWeShowCategories();
                        tasksFrame.hide();
                        categoriesFrame.showFrame(service.getInformationFromServer("GET CATEGORIES"));
                    } else if (tasksFrame.doWeWantToRefreshTasks()) {
                        tasksFrame.showFrame(service.getInformationFromServer("GET TASKS ;; " + categoriesFrame.getChosenCategory()));
                        tasksFrame.resetDoWeRefresh();
                    }
                }

                doWeShowTasks = categoriesFrame.doWeWantToShowTasks();

            }
        } catch (ErrorWithServerException | NullPointerException ex) {
            ErrorFrame errFrame = new ErrorFrame(ex.getMessage());
        }
    }

    /**
     * Overrided method close(), closing socket.
     *
     * @throws IOException - exception may occure when there will be problems with Socket
     */
    @Override
    public void close() throws IOException {
        if (clientSocket != null) {
            clientSocket.close();
        }
    }
}
