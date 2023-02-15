package studySelenium;

public class getPassword {

	public static String getText(String text) {
		
		/*String[] passwordArray = text.split("'") ;
		String password = passwordArray[1].split("'")[0];*/	
		String password = text.split("'")[1].split("'")[0];
		return password;	
	}
}
