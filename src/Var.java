public class Var {
    private Token IDtoken;
    private Integer tokenValue;
    private String tokenString;

    Var (Token current){
        IDtoken = current;
        tokenString = IDtoken.get_token_string();
    }

    public String get_ID(){return tokenString;}

}