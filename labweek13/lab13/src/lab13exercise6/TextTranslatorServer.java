package lab13exercise6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TextTranslatorServer
{
	public static void main(String[] args) throws IOException
	{
		// Binding to a port or any other port no you are fancy of
		int portNo = 2345;
		ServerSocket serverSocket = new ServerSocket(portNo);
		System.out.println("Waiting a request from client side to bind with " + portNo + " server port");
		System.out.print("\n");
		
		// Accept client request for connection
		Socket clientSocket = serverSocket.accept();
			

		// Read stream data from the client
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
		String input = bufferReader.readLine();
		String LTT = bufferReader.readLine();
		int translateTo = Integer.parseInt(LTT);
		String toTranslate = "";
			
		if(translateTo == 1)
		{
			toTranslate = "English";
		}
		if(translateTo == 2)
		{
			toTranslate = "Arabic";
		}
		if(translateTo == 3)
		{
			toTranslate = "Korean (Hangul)";
		}
		System.out.println("Input: \n" + input);
			
		// get the value for the total words using method wordsCount()
	    String TranslateWords = TranslatorForText.TranslatorText(translateTo);
		// Create stream to write data on the network
		PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream());

		printWriter.println(TranslateWords);
		printWriter.flush();
		
		if (translateTo == 1 || translateTo == 2 || translateTo == 3)
		{
			System.out.println("Translated word sent to client.");
			System.out.print("\n" + input + " in " + toTranslate + " is " + TranslateWords + "\n\n:)");
		}
		
		else
		{
			System.out.println("\nSorry," + TranslateWords);
		}

		// Close the socket
		clientSocket.close();
			
		printWriter.close();
		bufferReader.close();
	}
}