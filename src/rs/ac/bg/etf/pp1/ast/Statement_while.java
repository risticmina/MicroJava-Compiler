// generated with ast extension for cup
// version 0.8
// 22/6/2026 0:51:55


package rs.ac.bg.etf.pp1.ast;

public class Statement_while extends Statement {

    private WhileNonTerm WhileNonTerm;
    private WhileCondBegin WhileCondBegin;
    private Condition Condition;
    private WhileCondEnd WhileCondEnd;
    private WhileBodyBegin WhileBodyBegin;
    private Statement Statement;
    private WhileBodyEnd WhileBodyEnd;

    public Statement_while (WhileNonTerm WhileNonTerm, WhileCondBegin WhileCondBegin, Condition Condition, WhileCondEnd WhileCondEnd, WhileBodyBegin WhileBodyBegin, Statement Statement, WhileBodyEnd WhileBodyEnd) {
        this.WhileNonTerm=WhileNonTerm;
        if(WhileNonTerm!=null) WhileNonTerm.setParent(this);
        this.WhileCondBegin=WhileCondBegin;
        if(WhileCondBegin!=null) WhileCondBegin.setParent(this);
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.WhileCondEnd=WhileCondEnd;
        if(WhileCondEnd!=null) WhileCondEnd.setParent(this);
        this.WhileBodyBegin=WhileBodyBegin;
        if(WhileBodyBegin!=null) WhileBodyBegin.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.WhileBodyEnd=WhileBodyEnd;
        if(WhileBodyEnd!=null) WhileBodyEnd.setParent(this);
    }

    public WhileNonTerm getWhileNonTerm() {
        return WhileNonTerm;
    }

    public void setWhileNonTerm(WhileNonTerm WhileNonTerm) {
        this.WhileNonTerm=WhileNonTerm;
    }

    public WhileCondBegin getWhileCondBegin() {
        return WhileCondBegin;
    }

    public void setWhileCondBegin(WhileCondBegin WhileCondBegin) {
        this.WhileCondBegin=WhileCondBegin;
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public WhileCondEnd getWhileCondEnd() {
        return WhileCondEnd;
    }

    public void setWhileCondEnd(WhileCondEnd WhileCondEnd) {
        this.WhileCondEnd=WhileCondEnd;
    }

    public WhileBodyBegin getWhileBodyBegin() {
        return WhileBodyBegin;
    }

    public void setWhileBodyBegin(WhileBodyBegin WhileBodyBegin) {
        this.WhileBodyBegin=WhileBodyBegin;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public WhileBodyEnd getWhileBodyEnd() {
        return WhileBodyEnd;
    }

    public void setWhileBodyEnd(WhileBodyEnd WhileBodyEnd) {
        this.WhileBodyEnd=WhileBodyEnd;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(WhileNonTerm!=null) WhileNonTerm.accept(visitor);
        if(WhileCondBegin!=null) WhileCondBegin.accept(visitor);
        if(Condition!=null) Condition.accept(visitor);
        if(WhileCondEnd!=null) WhileCondEnd.accept(visitor);
        if(WhileBodyBegin!=null) WhileBodyBegin.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(WhileBodyEnd!=null) WhileBodyEnd.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(WhileNonTerm!=null) WhileNonTerm.traverseTopDown(visitor);
        if(WhileCondBegin!=null) WhileCondBegin.traverseTopDown(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(WhileCondEnd!=null) WhileCondEnd.traverseTopDown(visitor);
        if(WhileBodyBegin!=null) WhileBodyBegin.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(WhileBodyEnd!=null) WhileBodyEnd.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(WhileNonTerm!=null) WhileNonTerm.traverseBottomUp(visitor);
        if(WhileCondBegin!=null) WhileCondBegin.traverseBottomUp(visitor);
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(WhileCondEnd!=null) WhileCondEnd.traverseBottomUp(visitor);
        if(WhileBodyBegin!=null) WhileBodyBegin.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(WhileBodyEnd!=null) WhileBodyEnd.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Statement_while(\n");

        if(WhileNonTerm!=null)
            buffer.append(WhileNonTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(WhileCondBegin!=null)
            buffer.append(WhileCondBegin.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(WhileCondEnd!=null)
            buffer.append(WhileCondEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(WhileBodyBegin!=null)
            buffer.append(WhileBodyBegin.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(WhileBodyEnd!=null)
            buffer.append(WhileBodyEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Statement_while]");
        return buffer.toString();
    }
}
