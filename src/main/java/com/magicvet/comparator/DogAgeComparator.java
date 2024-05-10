package main.java.com.magicvet.comparator;

import main.java.com.magicvet.model.Dog;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DogAgeComparator implements Comparator<Dog> {
    private static final String YEARS_PATTERN = "(\\d+)\\s*(y|year|years)";
    private static final String MONTHS_PATTERN = "(\\d+)\\s*(m|month|months)";

    @Override
    public int compare(Dog dog1, Dog dog2) {
        return convertAgeToMonths(dog1.getAge()) - convertAgeToMonths(dog2.getAge());
    }

    private int convertAgeToMonths(String age) {
        int years = parseValueUsingPattern(age,YEARS_PATTERN);
        int months = parseValueUsingPattern(age,MONTHS_PATTERN);
        return years * 12 + months;
    }

    private int parseValueUsingPattern(String input,String patternString){
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()){
            return Integer.parseInt(matcher.group(1));
        } else {
            return 0;
        }
    }
}
