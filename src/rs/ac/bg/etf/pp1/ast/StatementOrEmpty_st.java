// generated with ast extension for cup
// version 0.8
// 22/6/2026 0:51:55


package rs.ac.bg.etf.pp1.ast;

public class StatementOrEmpty_st extends StatementOrEmpty {

    private StatementOrEmpty StatementOrEmpty;
    private Statement Statement;

    public StatementOrEmpty_st (StatementOrEmpty StatementOrEmpty, Statement Statement) {
        this.StatementOrEmpty=StatementOrEmpty;
        if(StatementOrEmpty!=null) StatementOrEmpty.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public StatementOrEmpty getStatementOrEmpty() {
        return StatementOrEmpty;
    }

    public void setStatementOrEmpty(StatementOrEmpty StatementOrEmpty) {
        this.StatementOrEmpty=StatementOrEmpty;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StatementOrEmpty!=null) StatementOrEmpty.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StatementOrEmpty!=null) StatementOrEmpty.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StatementOrEmpty!=null) StatementOrEmpty.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementOrEmpty_st(\n");

        if(StatementOrEmpty!=null)
            buffer.append(StatementOrEmpty.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementOrEmpty_st]");
        return buffer.toString();
    }
}
