package lab13exercise3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class TextProcessingClient
{
    int serverSidePortNo = 4321;
    
    public void startClient() throws IOException
    {
    	Socket socket = null;
        try
        {
        	// Connect to the remote machine
            socket = new Socket(InetAddress.getLocalHost(),serverSidePortNo);
            
        	// Send request to the server
        	OutputStream outputStream = socket.getOutputStream();
        	String text = "This is Lab 13 Exercise 3.";
        	byte[] textBytes = text.getBytes();
        	outputStream.write(textBytes);
        	outputStream.flush();
        	
        	// Accept response from the server
        	InputStream inputStream = socket.getInputStream();
        	// Buffer size for reading data from the server
        	byte[] buffer = new byte[1024];
            int bytesRead;
            StringBuilder responseBuilder = new StringBuilder();
            while ((bytesRead = inputStream.read(buffer)) != -1) 
            {
            	String responseChunk = new String(buffer, 0, bytesRead);
            	responseBuilder.append(responseChunk);
            }
            String response = responseBuilder.toString();
        	
              // Response the process
        	System.out.println("Response from server: " + response);
        }
        catch (IOException e)
        {
        	e.printStackTrace();
        }
        finally
        {
        	if (socket != null)
        	{
        		try
        		{
        			socket.close();
        		}
        		catch (IOException e)
        		{
                         e.printStackTrace();
                }
        	}
        }
    }
    

    public static void main (String[] args)
    {
    	TextProcessingClient client = new TextProcessingClient();
        try
        {
            client.startClient();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}