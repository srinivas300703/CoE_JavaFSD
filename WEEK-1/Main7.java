
import java.util.*;
class Main7
{
    public List<Integer>find_Anagrams(String s, String p)
    {
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length()) 
        	return res;
        int[] pCount = new int[26];
        int[] sCount = new int[26];        
        for (char c : p.toCharArray())
        {
            pCount[c - 'a']++;
        }       
        for (int i = 0; i < s.length(); i++)
        {
            sCount[s.charAt(i) - 'a']++;            
            if (i >= p.length())
                sCount[s.charAt(i - p.length()) - 'a']--;                       
            if (Arrays.equals(pCount, sCount))
                res.add(i - p.length() + 1);
        }
    	return res;
	}
    public static void main(String[] args)
    {
        Main7 finder = new Main7();
        String s = "heart";
        String p = "earth";
        System.out.println("Anagram start indices: " + finder.find_Anagrams(s, p));
    }
}
