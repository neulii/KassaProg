package utily;

public final class Xor {
	
	public static String xorCrypt(String text, String key){
		
		int textLength = text.length();
		int keyLength = key.length();
		
		int posFromKey = 0;
		
		StringBuilder cryptString = new StringBuilder();
		
		char c1;
		char c2;
		char temp;
		
		for (int i=0; i<textLength; i++){
			posFromKey = i % keyLength;
			
			c1 = text.charAt(i);
			c2 = key.charAt(posFromKey);
		
			temp = (char)(c1^c2);
	
			cryptString.append( temp );
		}

		return cryptString.toString();
	}
}
