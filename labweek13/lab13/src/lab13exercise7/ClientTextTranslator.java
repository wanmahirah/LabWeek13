package lab13exercise7;

import java.net.Socket;
import java.net.InetAddress;
import java.io.PrintWriter;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class ClientTextTranslator
{
	public static void main(String[] args)
	{
		FrameClient clientFrame = new FrameClient();
		String text = "TERIMA KASIH";
		String translateTo = "English";
		
		try
		{
			// Launch client-side frame
			clientFrame.setVisible(true);
			
			// Connect to the server @ localhost, port 2345
			Socket socket = new Socket(InetAddress.getLocalHost(), 2345);

			// Update the status of the connection
			clientFrame.updateConnectionStatus(socket.isConnected());
			System.out.println(socket.isConnected());

			// Write text for server
			PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
		
			// send text
			printWriter.println(text);
			printWriter.println(translateTo);
			System.out.println("Text send to server.");
		
			// Read from network
			BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(socket.getInputStream()));

			System.out.println("Test");
			// display words count
			String translateLanguage = bufferedReader.readLine();
			clientFrame.updateWordsCount(translateLanguage);
	

			// Close everything
			bufferedReader.close();
			socket.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}