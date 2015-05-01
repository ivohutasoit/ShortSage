package com.softhaxi.shortsage.v1.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class PhoneNumberValidator {
    private static final String INTERNATIONAL_PATTERN = 
            "([\\+(]?(\\d){2,}[)]?[- \\.]?(\\d){2,}[- \\.]?(\\d){2,}[- \\.]?(\\d){2,}[- \\.]?(\\d){2,})|([\\+(]?(\\d){2,}[)]?[- \\.]?(\\d){2,}[- \\.]?(\\d){2,}[- \\.]?(\\d){2,})|([\\+(]?(\\d){2,}[)]?[- \\.]?(\\d){2,}[- \\.]?(\\d){2,})";
    
    private static final String TELKOMSEL_PATTERN = 
            "^(\\\\+62|\\\\+0|0|62)8(1[123]|52|53|21|22|23)[0-9]{5,9}$";
    
    private static final String SIMPATI_PATTERN =
            "^(\\\\+62|\\\\+0|0|62)8(1[123]|2[12])[0-9]{5,9}$";
    
    private static final String AS_PATTERN = 
            "^(\\\\+62|\\\\+0|0|62)8(52|53|23)[0-9]{5,9}$";
    
    private static final String INDOSAT_PATTERN = 
            "^(\\\\+62815|0815|62815|\\\\+0815|\\\\+62816|0816|62816|\\\\+0816|\\\\+62858|0858|62858|\\\\+0814|\\\\+62814|0814|62814|\\\\+0814)[0-9]{5,9}$";
     
    private static final String IM3_PATTERN = 
            "^(\\\\+62855|0855|62855|\\\\+0855|\\\\+62856|0856|62856|\\\\+0856|\\\\+62857|0857|62857|\\\\+0857)[0-9]{5,9}$";
    
    private static final String XL_PATTERN = 
            "^(\\\\+62817|0817|62817|\\\\+0817|\\\\+62818|0818|62818|\\\\+0818|\\\\+62819|0819|62819|\\\\+0819|\\\\+62859|0859|62859|\\\\+0859|\\\\+0878|\\\\+62878|0878|62878|\\\\+0877|\\\\+62877|0877|62877)[0-9]{5,9}$";
    
    /**
     * 
     * @param number
     * @return 
     */
    public static boolean validate(String number) {
        Pattern pattern = Pattern.compile(INTERNATIONAL_PATTERN);
        Matcher matcher = pattern.matcher(number);
        
        return matcher.matches();
    }
}
