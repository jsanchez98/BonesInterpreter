import java.io.*;
import java.lang.*;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException{
        System.out.println("Enter the name of the BareBones txt file to be run:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String Filename = reader.readLine();
        String line = null;
        String lines = "";
        FileReader filereader = new FileReader(Filename);
        BufferedReader fReader = new BufferedReader(filereader);

        while((line = fReader.readLine()) != null){
            lines += line;
        }

        System.out.println(lines);

        Lexer lexer = new Lexer(lines);
        Parser parser = new Parser(lexer);
        Interpreter interpreter = new Interpreter(parser);
        interpreter.interpret();

        System.out.println(Collections.singletonList(interpreter.get_HashMap()));
    }
}

