package logic;

public class Password extends CPassword {


    private String pass;

    public Password(String pass){
        super(pass);
        this.pass = pass;
    }


    @Override
    String generatePassword() {
        return gen.generatePassword(10, splCharRule, lowerCaseRule,
                upperCaseRule, digitRule);
    }

    @Override
    public int howStrongIsThePassword() {
        return super.howStrongIsThePassword();
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
