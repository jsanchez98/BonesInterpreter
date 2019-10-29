import java.util.HashMap;
public class Lexer {
    private String lextext;
    private Integer pos;
    private Character current_char;
    HashMap <String, Token> keywords = new HashMap <String, Token>();

    public void set_keywords(){
        keywords.put("clear", new Token("clear", "clear"));
        keywords.put("incr", new Token("incr", "incr"));
        keywords.put("decr", new Token("decr", "decr"));
        keywords.put("while", new Token("while", "while "));
        keywords.put("not", new Token("not", "not"));
        keywords.put("do", new Token("do", "do"));
        keywords.put("end", new Token("end", "end"));
        keywords.put("0", new Token("zero", 0));
    }

    public Character get_current_char(){
        return current_char;
    }

    public Lexer(String text){
        set_keywords();
        lextext = text;
        pos = 0;
        current_char = lextext.charAt(pos);
    }

    public void advance(){
        pos += 1;
        if (pos > lextext.length() - 1){
            current_char = null;
        }
        else{
            current_char = lextext.charAt(pos);
        }
    }

    public void skip_whitespace(){
        while (!(current_char == null) &&  (current_char == ' ')) {
            advance();
        }
    }

    public Integer integer(){
        Integer result = 0;
        while (!(current_char == null) && (Character.isDigit(current_char))){
            result += Character.getNumericValue(current_char);
            advance();
        }
        return result;
    }

    public Token id(){
        String input;
        StringBuilder sb = new StringBuilder();
        while (!(current_char == null) && (Character.isLetter(current_char) || Character.getNumericValue(current_char) == 0)){
            sb.append(current_char);
            advance();
        }
        input = sb.toString();
        Token token = keywords.get(input);
        if (token == null){
            token = new Token("ID", input);
        }
        return token;
    }

    public Token get_next_token(){
        while (current_char != null){

            if (Character.isLetter(current_char)){
                return id();
            }

            if (current_char == ';'){
                advance();
                return new Token("SEMI", ";");
            }

            if (current_char == ' '){
                skip_whitespace();
                continue;
            }

            if (Character.getNumericValue(current_char) == 0){
                advance();
                return new Token("ZERO", 0);
            }
        }

        return new Token("EOF", "n");
    }

    public void error(){
        System.out.println("Invalid token!");
    }
}





