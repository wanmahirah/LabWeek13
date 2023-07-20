package lab13exercise6;

public class TranslatorForText
{
	public static String TranslatorText(int translateTo)
	{
		// TODO Auto-generated method stub
		if (translateTo == 1)
		{
		   String EnglishWord = "How are you?";
		   return EnglishWord;
		} 
		if (translateTo == 2)
		{
		   String Arabic = "كيف حالك؟";
		   return Arabic;
		}
		if (translateTo == 3)
		{
		   String Hangul = "어떻게 지내세요?";
		   return Hangul;
		}
		else
		{
			return " the language is not available.";
		}
	}
}