import java.io.*;

public class printJLabels
{
	public static void main(String[]args)
	{
		try{
		PrintStream out = new PrintStream(new FileOutputStream("JLabelOutput.txt"));
		System.setOut(out);
	}
	catch(Exception e)
	{
		System.out.println("Didn't work");
	}

		for(int letter=0; letter<26; letter++)
		{
			char c = (char)(97 + letter);
			String let = "" + c;
			for(int num=1; num<21; num++)
			{
				System.out.println("JLabel " + let + num + " = new JLabel();");
			}
			System.out.println();
		}
	}
}

