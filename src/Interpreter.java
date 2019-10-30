import java.util.Collections;
import java.util.HashMap;
public class Interpreter {
    private Parser Iparser;
    private HashMap<String, Integer> values = new HashMap<>();

    Interpreter(Parser parser){
        Iparser = parser;
    }

    public void interpret(){
        Stmt_list Tree = Iparser.parse();
        System.out.println("Interpreting starts");
        visit_stmt_list(Tree);
    }

    public void visit_stmt_list(Stmt_list rootList){
        for(Stmt node: rootList.getList()){
            visit_stmt(node);
            System.out.println(String.format("interpreter:%s", node.repr()));
            System.out.println(Collections.singletonList(values));
        }
    }

    public void visit_stmt(Stmt stmt){
        if (stmt.get_stmt_type().equals("clear")){
            values.put(stmt.get_stmt_var().get_ID(), 0);
        }
        else if (stmt.get_stmt_type().equals("incr")){
            values.put(stmt.get_stmt_var().get_ID(), visit_var(stmt) + 1);
        }
        else if (stmt.get_stmt_type().equals("decr")){
            values.put(stmt.get_stmt_var().get_ID(), visit_var(stmt) - 1);
        }
        else if(stmt.get_stmt_type().equals("while")){
            while(visit_var(stmt) > 0) {
                visit_stmt_list(stmt.get_sublist());
            }
        }
    }

    public Integer visit_var(Stmt stmt){
        return values.get(stmt.get_stmt_var().get_ID());
    }

    public HashMap<String, Integer> get_HashMap(){return values;}

}
