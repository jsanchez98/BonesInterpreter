public class Stmt {
    private Var stmtVariable;
    private String stmtType;
    private Stmt_list subList;

    Stmt(){}

    Stmt(String type, Var variable){
        stmtType = type;
        stmtVariable = variable;
        subList = null;
    }

    Stmt(String type, Var variable, Stmt_list list){
        stmtType = type;
        stmtVariable = variable;
        subList = list;
    }

    public String repr(){
        return String.format("%s : %s", stmtType, stmtVariable.get_ID());
    }

    public String get_stmt_type(){return stmtType;}

    public Var get_stmt_var(){return stmtVariable;}

    public Stmt_list get_sublist(){return subList;}


}