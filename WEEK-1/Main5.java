class StringProcessor {

    
    public String reverseString(String str) {
        StringBuilder reversed = new StringBuilder(str);
        return reversed.reverse().toString();
    }

    
    public int countOccurrences(String text, String sub) {
        if (text == null || sub == null || sub.isEmpty()) {
            return 0;
        }
        int count = 0;
        int index = 0;

       
        while ((index = text.indexOf(sub, index)) != -1) {
            count++;
            index += sub.length(); 
        }
        return count;
    }

    
    public String splitAndCapitalize(String str) {
        if (str == null || str.trim().isEmpty()) {
            return ""; 
        }
        String[] words = str.split("\\s+");
        StringBuilder result = new StringBuilder();

        
        for (String word : words) {
            result.append(word.substring(0, 1).toUpperCase())  
                  .append(word.substring(1).toLowerCase())    
                  .append(" ");
        }

        return result.toString().trim(); 
    }
}

public class Main5 {
    public static void main(String[] args) {
        StringProcessor processor = new StringProcessor();

        
        String text1 = "Hello World";
        String reversedText = processor.reverseString(text1);
        System.out.println("Reversed String: " + reversedText);

        
        String text2 = "A boy fall in love with a girl . the girl also loves a boy.";
        String substring = "girl";
        int occurrences = processor.countOccurrences(text2, substring);
        System.out.println("Occurrences of '" + substring + "': " + occurrences);

       
        String text3 = "hello world! how are you?";
        String capitalizedText = processor.splitAndCapitalize(text3);
        System.out.println("Capitalized String: " + capitalizedText);
    }
}
