import java.util.HashMap;


public class Language {
	
	private HashMap<String, String> tab;
	private String name;
	
	//http://onlinehelp.microsoft.com/en-us/bing/ff808526.aspx
	private String languageCode;
	
	private String folder;
	
	/**
	 * Default Constructor
	 * Default language english.
	 */
	public Language(String folder) {
		this.folder = folder;
		name = "English";
		languageCode = "en";
	}


	public Language(String name, String languageCode, String folder) {
		this.languageCode = languageCode;
		this.name = name;
		this.folder = folder;
	}

}
