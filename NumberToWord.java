import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NumberToWord {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Please enter a number using digits: ");
            int i = scanner.nextInt();

            NumberToWord numberToWord = new NumberToWord();
            String numberString = numberToWord.getNumberInEnglish(i);

            System.out.println("Your number in words is: " + numberString);
        } catch (InputMismatchException e) {
            System.out.println("An error occurred. Please make sure you enter a whole number less than " + Integer.MAX_VALUE);
        } finally {
            scanner.close();
        }
        
    }

    private String getNumberInEnglish(int number) {
        if (number == 0) {
            return "Zero";
        }
        HashMap<Integer, String> numberDictionary = populateNumberDictionary();
        HashMap<Integer, String> tensDictionary = populateTensMap();
        HashMap<Integer, String> quantifierDictionary = populateQuantifierDictionary();

        String numToString = Integer.toString(number);

        int numLength = numToString.length();
        int commaCount = (numLength - 1) / 3;

        StringBuilder sb = new StringBuilder();

        int substringStart = 0;
        int substringMax = 0;
        if (numLength % 3 > 0) {
            substringMax = numLength % 3;
        } else {
            substringMax = 3;
        }

        for (int i = commaCount; i >= 0; i--) {
            
            String subSection = numToString.substring(substringStart, substringMax);

            int subSectionLength = subSection.length();

            for (int j = subSectionLength - 1; j >= 0; j--) {
                if (j == 2) {
                    if (Integer.parseInt(subSection.substring(subSectionLength - 3, subSectionLength)) == 0) {
                        break;
                    }
                    Integer subSectionToInt = Integer.parseInt(subSection.substring(0, 1));
                    if (subSectionToInt == 0) {
                        continue;
                    }
                    sb.append(numberDictionary.get(subSectionToInt));
                    sb.append(" Hundred ");
                } else if (j == 1) {
                    if (Integer.parseInt(subSection.substring(subSectionLength - 2, subSectionLength)) == 0) {
                        sb.append(quantifierDictionary.get(i)).append(" ");
                        break;
                    } else if (Integer.parseInt(subSection.substring(subSectionLength - 2, subSectionLength)) < 20) {
                        sb.append(numberDictionary.get(Integer.parseInt(subSection.substring(subSectionLength - 2, subSectionLength))));
                        sb.append(" ").append(quantifierDictionary.get(i)).append(" ");
                        break;
                    } else {
                        sb.append(tensDictionary.get(Integer.parseInt(subSection.substring(subSectionLength - 2, subSectionLength - 1)) * 10));
                        sb.append(" ");
                    }
                } else {
                    if (Integer.parseInt(subSection.substring(subSectionLength - 1, subSectionLength)) == 0) {
                        sb.append(quantifierDictionary.get(i)).append(" ");
                        break;
                    }
                    sb.append(numberDictionary.get(Integer.parseInt(subSection.substring(subSectionLength - 1, subSectionLength))));
                    sb.append(" ").append(quantifierDictionary.get(i)).append(" ");
                }
            }

            substringStart = substringMax;
            substringMax += 3;

        }

        return sb.toString().trim();
    }

    private HashMap<Integer, String> populateNumberDictionary() {
        HashMap<Integer, String> numberDict = new HashMap<>();
        numberDict.put(1, "One");
        numberDict.put(2, "Two");
        numberDict.put(3, "Three");
        numberDict.put(4, "Four");
        numberDict.put(5, "Five");
        numberDict.put(6, "Six");
        numberDict.put(7, "Seven");
        numberDict.put(8, "Eight");
        numberDict.put(9, "Nine");
        numberDict.put(10, "Ten");
        numberDict.put(11, "Eleven");
        numberDict.put(12, "Twelve");
        numberDict.put(13, "Thirteen");
        numberDict.put(14, "Fourteen");
        numberDict.put(15, "Fifteen");
        numberDict.put(16, "Sixteen");
        numberDict.put(17, "Seventeen");
        numberDict.put(18, "Eighteen");
        numberDict.put(19, "Nineteen");
        return numberDict;
    }

    private HashMap<Integer, String> populateTensMap() {
        HashMap<Integer, String> tensDict = new HashMap<>();
        tensDict.put(20, "Twenty");
        tensDict.put(30, "Thirty");
        tensDict.put(40, "Forty");
        tensDict.put(50, "Fifty");
        tensDict.put(60, "Sixty");
        tensDict.put(70, "Seventy");
        tensDict.put(80, "Eighty");
        tensDict.put(90, "Ninety");
        return tensDict;
    }
    
    private HashMap<Integer, String> populateQuantifierDictionary() {
        HashMap<Integer, String> quantifierDict = new HashMap<>();
        quantifierDict.put(0, "");
        quantifierDict.put(1, "Thousand");
        quantifierDict.put(2, "Million");
        quantifierDict.put(3, "Billion");
        return quantifierDict;

    }
}