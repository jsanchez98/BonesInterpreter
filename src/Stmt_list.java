import java.util.*;
public class Stmt_list {
    private ArrayList<Stmt> children = new ArrayList<>();

    Stmt_list(){}

    public void addNode(Stmt node){
        children.add(node);
    }

    public ArrayList<Stmt> getList(){ return children;}

}