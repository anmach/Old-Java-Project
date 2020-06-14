package pl.polsl.lab.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import pl.polsl.lab.exceptions.ErrorWithServerException;

/**
 * Class communicating client demand to server
 *
 * @author Anna Mach
 * @version 1.1
 */
public class DemandService implements Closeable {
        /**
     * Communication socket
     */
    private Socket socket;
    /**
     * buffered input character stream
     */
    private BufferedReader input;
    /**
     * Formatted output character stream
     */
    private PrintWriter output;

    /**
     * Constructor of Service
     *
     * @param socket representing connection to the client
     * @throws pl.polsl.lab.exceptions.ErrorWithServerException - exception thrown when problems with server occure
     */
    public DemandService(Socket socket) throws ErrorWithServerException {
        try{
        this.socket = socket;
        output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch(IOException ex){
            throw new ErrorWithServerException("IOException in DemandService constructor.");
        }
    }

    /**
    * Method for reading welcome message
     * @throws pl.polsl.lab.exceptions.ErrorWithServerException - exception thrown when problems with server occure
    */
    public void reciveWelcomeMessageFromServer() throws ErrorWithServerException{        
        try {
            System.out.print(input.readLine() + "\n");
        } catch (IOException e) {
            throw new ErrorWithServerException("IOException in reciveWelcomeMessageFromServer method from class DemandService.");
        }
    }
    
    /**
     * Method which realizes the service
     *
     * @param demand - String with instruction for server
     * @throws pl.polsl.lab.exceptions.ErrorWithServerException - exception thrown when problems with server occure
     */
    public void realize(String demand) throws ErrorWithServerException{
        try {
            output.println(demand);
            String str = input.readLine();
            System.out.print(str + "\n");
        } catch (IOException e) {
            throw new ErrorWithServerException("IOException in realize() method from class DemandService.");
        }
    }

    /**
     * Method which asks server for information
     *
     * @param demand String with information what information program want to
     * get
     * @return String with requested information
     * @throws pl.polsl.lab.exceptions.ErrorWithServerException - exception thrown when problems with server occure
     */
    public String getInformationFromServer(String demand) throws ErrorWithServerException {
        try {
            output.println(demand);
            String str = input.readLine();
            System.out.print(str + "\n");
            String[] splitted = str.split(" :: ");
            boolean isError = false;
            int i =0;
            for(String string: splitted){
                switch(i){
                    case 0:
                        if(!string.equals("FROM SERVER"))
                            throw new ErrorWithServerException("Wrong protocool.");
                        i++;
                        break;
                    case 1:
                        if(string.equals("ERROR"))
                            isError = true;
                        else
                            return string;
                        i++;
                        break;
                    case 2:
                        throw new ErrorWithServerException(string);
                }
            }
            return str;
        } catch (IOException e) {
            throw new ErrorWithServerException("IOException in getInformationFromServer() method from class DemandService.");
        }
    }

    /**
     * Overrided method close(), closing socket.
     * @throws IOException - exception may occure when there will be problems with Socket
     */
    @Override
    public void close() throws IOException {
        if(socket!=null){
            socket.close();
        }
    }
}
