/** stringDist method returns the edit distance between two strings. 
*	Unfinished, the result is slightly off for certain inputs.
*	Helpful tool to check correctness of results: https://planetcalc.com/1721/
*/

public class StringDist {
	
	//From https://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/Longest_common_substring
	//Helper method to find the longest common substring in two strings
	public static String longestCommonSubstring(String S1, String S2) {

	    int Start = 0;
	    int Max = 0;

	    for (int i = 0; i < S1.length(); i++) {

	        for (int j = 0; j < S2.length(); j++) {

	            int x = 0;

	            while (S1.charAt(i + x) == S2.charAt(j + x)) {

	                x++;

	                if (((i + x) >= S1.length()) || ((j + x) >= S2.length())) {
	                	break;
	                }
	            }
	            if (x > Max) {
	                Max = x;
	                Start = i;
	            }
	         }
	    }

	    return S1.substring(Start, (Start + Max));
	}

	public static int stringDist(String from, String into) {

		int fromLength = from.length();
		int intoLength = into.length();

		//Return value of the method - the minimum number of steps to convert one string into the other
		int numberOfSteps = 0;

		//If any of the strings is empty then it takes as many steps to convert one to the other as the number of letters it contains
		if(fromLength == 0 || from == null) {

			return intoLength;
		}

		if(intoLength == 0 || into == null) {

			return fromLength;
		}

		//We find the longest substring of the string that is contained in the string we are transforming it into
		String longestSubstr = longestCommonSubstring(from, into);

		//If there is no common substring in the two string then we have to proceed letter by letter
		if(longestSubstr.length() <= 1) {

			if(fromLength <= intoLength ) {

				for(int i = 0; i < fromLength; i++) {

					if(!(from.substring(i, i+1).equals(into.substring(i, i+1)))) {

						numberOfSteps++;
					}

				}

				return numberOfSteps + intoLength - fromLength;
			}

			if(fromLength > intoLength) {

				for(int i = 0; i < intoLength; i++) {

					if(!(from.substring(0, i+1).equals(into.substring(0, i+1)))) {

						numberOfSteps++;

					}
				}

				return numberOfSteps + fromLength - intoLength;
			}
		}

		//If there is a common substring, check how many letters have to be modified to its left and right
		int subStrLength = longestSubstr.length();
		int fromStartIndex = from.indexOf(longestSubstr);
		int fromEndIndex = fromStartIndex + subStrLength;
		int intoStartIndex = into.indexOf(longestSubstr);
		int intoEndIndex = intoStartIndex + subStrLength;

		if(fromLength <= intoLength) {

			//Checking the letters to the left from the longest common substring
			for(int i = 0; i < intoStartIndex; i++) {

				//If the word being transformed has no more letters, then we meed as many more steps to transform 
				//it as the number of letters left until the start of the common substring
				if(i + 1 > fromLength) {

					numberOfSteps += intoStartIndex - fromLength;
					break;
				}

				if(!(into.substring(i, i+1).equals(from.substring(i, i+1)))) {

					numberOfSteps++;
				}
			}

			//Checking the letters to the right from the longest common substring
			for(int i = intoEndIndex; i < intoLength; i++) {

				if(i + 1 > fromLength) {

					return numberOfSteps + intoLength - into.substring(0, i).length();
				}

				if(!(into.substring(i, i+1).equals(from.substring(i, i+1)))) {

					numberOfSteps++;
				}
			}			

		}

		//Symmetric to fromLength <= intoLength
		if(fromLength > intoLength) {

			for(int i = 0; i < fromStartIndex; i++) {

				if(i + 1 > intoLength) {

					numberOfSteps += fromStartIndex - intoLength;
					break;
				}

				if(!(from.substring(i, i+1).equals(into.substring(i, i+1)))) {

					numberOfSteps++;
				}
			}

			for(int i = fromEndIndex; i < fromLength; i++) {

				if(i + 1 > intoLength) {

					return numberOfSteps + fromLength - from.substring(0, i).length();
				}

				if(!(from.substring(i, i+1).equals(into.substring(i, i+1)))) {

					numberOfSteps++;
				}
			}
		}

		return numberOfSteps;
	}

	public static void main(String[] args) {

		System.out.println(stringDist(args[0], args[1]));
		
	}
}