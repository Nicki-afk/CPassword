package logic;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class Crypto {


    private String data;
    private byte[]code_word;
    private MessageDigest digest ;
    private byte[]salt = new byte[32];
    private SecureRandom random = new SecureRandom();
    private static Crypto crypto;

    protected Crypto(String data){

        this.data = data;

    }

    public Crypto(){


    }

    protected  byte[] initSalt(){

        byte[]salt = new byte[32];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);

        return salt;
    }







    // Add methods
    // - byte[] getEncryptCodeWord();
    // - boolean isCodeWord();


    // rewrite ...
    // return statement remake to byte[]arr
    protected byte[] encrypt(String inWord , byte[]salt){

        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.update(salt);


            return digest.digest(inWord.getBytes(StandardCharsets.UTF_8));
        }catch (Exception e){
            e.printStackTrace();
        }

        return new byte[]{0};


    }

    public  boolean isCodeWord(String word){

//        if(!new FileManager().filesExists()){
//            return false;
//        }else {
//
//
//            byte[] salt = new byte[32];
//            try {
//
//                MessageDigest digest = MessageDigest.getInstance("SHA-512");
//                digest.update(salt);
//                FileManager fileManager = new FileManager();
//                Byte[] inWord = ArrayUtils.toObject(digest.digest(word.getBytes(StandardCharsets.UTF_8)));
//                ArrayList<Byte> bytes = new ArrayList<>();
//
//                // this string to FileManager
//                Scanner scanner = new Scanner(fileManager.read()).useDelimiter("/");
//
//                while (scanner.hasNext()) {
//
//                    bytes.add(scanner.nextByte());
//
//                }
//
//
//                Byte[] codeWord = bytes.toArray(new Byte[bytes.size()]);
//
//
//                if(Arrays.equals(inWord, codeWord)){
//                   this.code_word = ArrayUtils.toPrimitive(codeWord);
//                    return true;
//
//                }else{
//                    return false;
//                }
//
//
//
//            } catch (Exception e) {
//
//
//                return false;
//            }
//
//        }


        return false;

    }





}
