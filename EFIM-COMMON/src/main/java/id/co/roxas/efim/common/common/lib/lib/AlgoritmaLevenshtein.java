package id.co.roxas.efim.common.common.lib.lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class AlgoritmaLevenshtein {
	
	public static void main(String[] args) {
		System.err.println(getLowestLevenstheinDistanceInFreeWordsQuery("Roxas","Roxas-kun"));
	}
	
	public static int getLowestLevenstheinDistanceInFreeWordsQuery (String search, String words) {
		List<Integer> getAllInteger = new ArrayList<>();
		for (String str : getAllProbablyContainLetters(words, search.length())) {
			getAllInteger.add(new Integer(getLowestLevenstheinDistanceInFreeSentenceQuery(search, str)));
		}
		return  getLowestValue(getAllInteger);
	}
	
	public static List<String> getAllProbablyContainLetters(String wordText, int sizeContainer){
		int i = 0;
		List<String> words = new ArrayList<>();
		while (i <= wordText.length()) {
			for (int j = i; j <= wordText.length(); j++) {
				    if(wordText.substring(i, j).length()==sizeContainer) {
					words.add(wordText.substring(i, j).toUpperCase());
				    }
			}
			i++;
		}
		return words;
	}
	
	public static int getLowestLevenstheinDistanceInFreeTextQuery(String pembanding, String dokumenText) {
		List<String> stringGarage1 = getAllProbablyContainWords(pembanding);
		List<String> stringGarage2 = getAllProbablyContainWords(dokumenText);
		List<Integer> getAllInteger = new ArrayList<>();
		for (String sg1 : stringGarage1) {
			for (String sg2 : stringGarage2) {
//				System.err.println("kata " + sg1 + " dibandingkan " + sg2 + " adalah " 
//			    + getFinalDistanceLevenshtein(sg1, sg2));
				getAllInteger.add(new Integer(getLowestLevenstheinDistanceInFreeSentenceQuery(sg1, sg2)));
			}
		}
	//	System.err.println("distance paling mendekati adalah " + getLowestValue(getAllInteger));
		return  getLowestValue(getAllInteger);
	}
	
	public static int getLowestValue(List<Integer> getAllInteger) {
		Integer minVal = new Integer(0);
		int temp = Integer.MAX_VALUE;
		for (Integer vale : getAllInteger) {
			if(temp>=vale) {
				temp = vale;
			}
		}
		minVal = new Integer(temp);
		return minVal;
	} 
	
	public static List<String> getAllProbablyContainWords(String wholeText) {
		String[] splitOpr = wholeText.split(" ");
		int op = splitOpr.length;
		int i = 0;
		List<String> textProb = new ArrayList<>();
		while (i <= op - 1) {
			for (int j = i; j <= op - 1; j++) {
					textProb.add(strProb(i, j, splitOpr));
			}
			i++;
		}

		return textProb;
	}
	
	public static String strProb(int a, int b, String[] text) {
		String prob = "";
		
		int qs = 0;
		for (int i = a; i <= b; i++) {
			prob += text[i] + " ";
			qs++;
		}

		return prob;
	}

	
	
	public static int getLowestLevenstheinDistanceInFreeSentenceQuery(String pembanding, String dokumenText) {
		char[] sChar1 = pembanding.toUpperCase().toCharArray();
		char[] sChar2 = dokumenText.toUpperCase().toCharArray();

		int size1 = pembanding.length();
		int size2 = dokumenText.length();
		int[][] markup = new int[size1 + 1][size2 + 1];
		int costTrue = 0;
		int costFalse = 1;
		for (int i = 0; i <= size1; i++) {
			for (int j = 0; j <= size2; j++) {
				if (i == 0) {	
					markup[i][j] = j;
				} else if (j == 0) {
					markup[i][j] = i;
				} else {
					int var1 = markup[i-1][j]+1;
					int var2 = markup[i][j-1]+1;
					int var3 = 0;
					if (sChar1[i-1] == sChar2[j-1]) {
						var3 = markup[i-1][j-1]+costTrue;
					} else {
						var3 = markup[i-1][j-1]+costFalse;
					}
					markup[i][j] = getMinCostLevenshtein(var1, var2, var3);
				}
			}
		}

//		for (int i = 0; i <= size1; i++) {
//			System.err.println(" ");
//			for (int j = 0; j <= size2; j++) {
//				System.err.print(markup[i][j] + " ");
//			}
//		}

		return markup[size1][size2];
		
	}
	
	public static List<String> getAllClosestWordWithLevenshtein(List<String> words, String search){
		Map<String,Integer> queueWords = new HashMap<>();
		for (String word : words) {
			int points = getLowestLevenstheinDistanceInFreeWordsQuery
					(search, word);
			queueWords.put(word,points);
			System.err.println("dengan search : "+search+" untuk kalimat " + word + " level point : " + points);
		}
		return queueListByRule(queueWords, null);
	}
	
	public static List<String> queueListByRule(Map<String,Integer> queueWords, String[] rule){
		List<String> queues = new ArrayList<>();
		int iMax = Integer.MAX_VALUE;
		String tempMax = "";
		int i = 1;
		while(!queueWords.isEmpty()) {
			for (Entry<String, Integer> queue : queueWords.entrySet()) {
				if(iMax>= queue.getValue().intValue()) {
					tempMax = queue.getKey();
					iMax = queue.getValue();
				}
			}
			
			System.err.println("yang masuk urutan " + i + " adalah " + tempMax);
			queues.add(tempMax);
			queueWords.remove(tempMax);
			iMax = Integer.MAX_VALUE;
		}
		return queues;
	}
	
//	public static List<String> createAllRuleForLevenstheinDistance(List<String> words, String search){
//		List<String> closestWords = new ArrayList<>();
//		
//		
//	}
	
	public static int getMinCostLevenshtein(int i, int j, int k) {
		int temp = i;
		if(temp<=j) {
			if(temp>=k) {
				temp = k;
			}
		}else {
			temp = j;
			if(temp>=k) {
				temp = k;
			}
		}
		return temp;
	}

}
