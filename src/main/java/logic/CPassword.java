package logic;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import static org.passay.CharacterOccurrencesRule.ERROR_CODE;

public abstract class CPassword  {



    private String password;
    protected CharacterData lowerCaseChars = EnglishCharacterData.LowerCase , upperCaseChars = EnglishCharacterData.UpperCase;
    protected CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars) ,  upperCaseRule = new CharacterRule(upperCaseChars);
    protected  CharacterData digitChars = EnglishCharacterData.Digit;
    protected  CharacterRule digitRule = new CharacterRule(digitChars);
    protected  PasswordGenerator gen = new PasswordGenerator();

    protected CharacterData specialChars = new CharacterData() {
        public String getErrorCode() {
            return ERROR_CODE;
        }

        public String getCharacters() {
            return "#$%^&*()_+";
        }
    };

   protected CharacterRule splCharRule = new CharacterRule(specialChars);


    public CPassword(String password){

        this.password = password;
        lowerCaseRule.setNumberOfCharacters(2);
        upperCaseRule.setNumberOfCharacters(2);
        digitRule.setNumberOfCharacters(2);
        splCharRule.setNumberOfCharacters(2);

    }

    public CPassword(){}

    public int howStrongIsThePassword(String password){


        return 0;


    }

    public int howStrongIsThePassword(){

        int securePassword = 0;

        char[]upperCase = upperCaseChars.getCharacters().toCharArray();
        char[]lowerCase = lowerCaseChars.getCharacters().toCharArray();
        char[]specialChars = "#$%^&*()_+".toCharArray();
        char[] numbers = "123456789".toCharArray();


        for(int x = 0 ; x < upperCase.length; x++){

            if(password.indexOf(upperCase[x]) != -1){
                securePassword+=5;
            }
        }


        for (int x = 0; x < lowerCase.length; x++){
            if(password.indexOf(lowerCase[x]) > -1){
                securePassword+=5;
            }

        }



        for (int x = 0; x < specialChars.length; x++){
            if(password.indexOf(specialChars[x]) > -1){
                securePassword+=20;
            }
        }


        for (int x = 0; x < numbers.length; x++){
            if(password.indexOf(numbers[x]) > -1){
                securePassword+=5;
            }
        }



            if(securePassword > 100){
                return 100;
            }else if(securePassword < 0){
                return 0;
            }else{
                return securePassword;
            }


    }

    
    abstract public String generatePassword();


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
