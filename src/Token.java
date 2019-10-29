public class Token {
    private String tokenType;
    private Integer tokenValue; //should not be static as multiple objects are in use in expr method
    private String tokenString;

    public Token(String type, Integer value) {
        if (value == 0) {
            tokenType = type;
            tokenValue = value;
        } else {
            tokenType = null;
            tokenValue = null;
        }
    }

    public Token(String type, String string) {
        tokenType = type;
        tokenString = string;
    }

    public String str() {
        if (tokenType == "INTEGER") {
            return String.format("Token(%s, %d)", tokenType, tokenValue);
        } else {
            return String.format("Token(%s, %s)", tokenType, tokenString);
        }
    }

    public String get_token_string(){return tokenString;}

    public int get_token_value() {
        return tokenValue;
    }

    public String get_token_type() {
        return tokenType;
    }

}
