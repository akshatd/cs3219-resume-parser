package common;

public class Word {
	private String content;
	private String POS;
	
	public Word(String newContent, String newPOS){
		content = newContent;
		String[] temp = newPOS.split("_");
		POS = temp[1];
	}
	public String getContent(){
		return content;
	}
	public String getPOS(){
		return POS;
	}
	public String toString() 
	{
	    return content + ":" + POS;
	}
}
