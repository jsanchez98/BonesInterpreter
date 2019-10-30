import java.util.*;
public class Parser {
    private Lexer Plexer;
    private Token current_token;

    Parser(Lexer lexer){
        Plexer = lexer;
        current_token = lexer.get_next_token();
    }

    public class NoOp extends Stmt{
        NoOp(){
            stmtType = "NoStmt";
        }

        public String repr(){
            return "end_node";
        }
    }

    public Stmt_list parse(){
        Stmt_list tree = program();
        return tree;
    }

    public void eat(String token_type){
      if (current_token.get_token_type().equals(token_type)){
          System.out.println(String.format("Eaten token: %s, %s", current_token.get_token_type(), current_token.get_token_string()));
         current_token = Plexer.get_next_token();
      }
      else{
         System.out.println("Invalid token");
      }
    }

   public Stmt_list program(){
      Stmt_list root = compound_statement();
      eat("end");
       eat("SEMI");
      return root;
   }

   public Stmt_list compound_statement(){
       ArrayList<Stmt> nodes = statement_list();
       Stmt_list root = new Stmt_list();
       for(Stmt node: nodes){
           if(node != null) {
               root.addNode(node);
           }
       }
       return root;
   }

   public ArrayList<Stmt> statement_list(){
        Stmt node = statement();
        ArrayList<Stmt> results = new ArrayList<>();
        results.add(node);

        while (current_token.get_token_type() == "SEMI"){
            eat("SEMI");
            results.add(statement());
        }
        if (current_token.get_token_type() == "ID"){
           Plexer.error();
        }
        return results;
   }

   public Stmt statement(){
        Stmt stmtnode;
        if (current_token.get_token_type().equals("clear")){
           stmtnode = clr_statement();
        }
        else if (current_token.get_token_type().equals("incr")){
          stmtnode = incr_statement();
        }
        else if (current_token.get_token_type().equals("decr")){
          stmtnode = decr_statement();
        }
        else if (current_token.get_token_type().equals("while")){
          stmtnode = while_statement();
        }
        else{
           stmtnode = empty();
        }
        return stmtnode;
   }

   public Stmt incr_statement(){
       eat("incr");
       Var var = var();
       return new Stmt("incr", var);
   }

   public Stmt decr_statement(){
      eat("decr");
      Var var = var();
      Stmt stmt = new Stmt("decr", var);
      return stmt;
   }

   public Stmt clr_statement(){
      eat("clear");
      Var var = var();
      Stmt stmt = new Stmt("clear", var);
      return stmt;
   }

   public Stmt while_statement(){
       eat("while");
       Var var = var();
       eat("not");
       eat("ZERO");
       eat("do");
       eat("SEMI");
       Stmt_list list = compound_statement();
       Stmt stmt = new Stmt("while", var, list);
       eat("end");
       return stmt;
   }

   public Var var(){
      Var vnode = new Var(current_token);
      eat("ID");
      return vnode;
   }

   public NoOp empty(){
        return new NoOp();
   }
}





