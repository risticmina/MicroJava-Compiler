// generated with ast extension for cup
// version 0.8
// 22/6/2026 0:51:55


package rs.ac.bg.etf.pp1.ast;

public class Statement_statement extends Statement {

    private StatementOrEmpty StatementOrEmpty;

    public Statement_statement (StatementOrEmpty StatementOrEmpty) {
        this.StatementOrEmpty=StatementOrEmpty;
        if(StatementOrEmpty!=null) StatementOrEmpty.setParent(this);
    }

    public StatementOrEmpty getStatementOrEmpty() {
        return StatementOrEmpty;
    }

    public void setStatementOrEmpty(StatementOrEmpty StatementOrEmpty) {
        this.StatementOrEmpty=StatementOrEmpty;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StatementOrEmpty!=null) StatementOrEmpty.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StatementOrEmpty!=null) StatementOrEmpty.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StatementOrEmpty!=null) StatementOrEmpty.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Statement_statement(\n");

        if(StatementOrEmpty!=null)
            buffer.append(StatementOrEmpty.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Statement_statement]");
        return buffer.toString();
    }
}
