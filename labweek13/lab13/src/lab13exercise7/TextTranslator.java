package lab13exercise7;

public class TextTranslator
{
	public static String transliterate (String input, String translateTo)
	{
		// TODO Auto-generated method stub
		String EnglishWord = "";
		String toBeTranslate = "English";
				
		if (translateTo.equals(toBeTranslate))
		{
		   EnglishWord = "How are you?";
		   return EnglishWord;
		} 
		else
		{
			return "The language is not available";
		}
	}
}
