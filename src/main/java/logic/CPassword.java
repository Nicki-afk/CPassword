package logic;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import static org.passay.CharacterOccurrencesRule.ERROR_CODE;

public abstract class CPassword {



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
            return "[email protected]#$%^&*()_+";
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


    public int howStrongIsThePassword(String password){


        return 0;


    }

    public int howStrongIsThePassword(){
        return 0;

    }

    
    abstract String generatePassword();


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
