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
	public static Word fromString(String input){
		String[] inputs = input.split(":");
		Word returnWord = new Word(inputs[0], "garbage_" + inputs[1]);
		return returnWord;
	}
}
