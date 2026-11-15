// generated with ast extension for cup
// version 0.8
// 22/6/2026 0:51:55


package rs.ac.bg.etf.pp1.ast;

public class Statement_foreach extends Statement {

    private ForeachNonTerm ForeachNonTerm;
    private ForeachVar ForeachVar;
    private ForeachColon ForeachColon;
    private ForeachArr ForeachArr;
    private ForeachBodyBegin ForeachBodyBegin;
    private Statement Statement;

    public Statement_foreach (ForeachNonTerm ForeachNonTerm, ForeachVar ForeachVar, ForeachColon ForeachColon, ForeachArr ForeachArr, ForeachBodyBegin ForeachBodyBegin, Statement Statement) {
        this.ForeachNonTerm=ForeachNonTerm;
        if(ForeachNonTerm!=null) ForeachNonTerm.setParent(this);
        this.ForeachVar=ForeachVar;
        if(ForeachVar!=null) ForeachVar.setParent(this);
        this.ForeachColon=ForeachColon;
        if(ForeachColon!=null) ForeachColon.setParent(this);
        this.ForeachArr=ForeachArr;
        if(ForeachArr!=null) ForeachArr.setParent(this);
        this.ForeachBodyBegin=ForeachBodyBegin;
        if(ForeachBodyBegin!=null) ForeachBodyBegin.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public ForeachNonTerm getForeachNonTerm() {
        return ForeachNonTerm;
    }

    public void setForeachNonTerm(ForeachNonTerm ForeachNonTerm) {
        this.ForeachNonTerm=ForeachNonTerm;
    }

    public ForeachVar getForeachVar() {
        return ForeachVar;
    }

    public void setForeachVar(ForeachVar ForeachVar) {
        this.ForeachVar=ForeachVar;
    }

    public ForeachColon getForeachColon() {
        return ForeachColon;
    }

    public void setForeachColon(ForeachColon ForeachColon) {
        this.ForeachColon=ForeachColon;
    }

    public ForeachArr getForeachArr() {
        return ForeachArr;
    }

    public void setForeachArr(ForeachArr ForeachArr) {
        this.ForeachArr=ForeachArr;
    }

    public ForeachBodyBegin getForeachBodyBegin() {
        return ForeachBodyBegin;
    }

    public void setForeachBodyBegin(ForeachBodyBegin ForeachBodyBegin) {
        this.ForeachBodyBegin=ForeachBodyBegin;
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
        if(ForeachNonTerm!=null) ForeachNonTerm.accept(visitor);
        if(ForeachVar!=null) ForeachVar.accept(visitor);
        if(ForeachColon!=null) ForeachColon.accept(visitor);
        if(ForeachArr!=null) ForeachArr.accept(visitor);
        if(ForeachBodyBegin!=null) ForeachBodyBegin.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ForeachNonTerm!=null) ForeachNonTerm.traverseTopDown(visitor);
        if(ForeachVar!=null) ForeachVar.traverseTopDown(visitor);
        if(ForeachColon!=null) ForeachColon.traverseTopDown(visitor);
        if(ForeachArr!=null) ForeachArr.traverseTopDown(visitor);
        if(ForeachBodyBegin!=null) ForeachBodyBegin.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ForeachNonTerm!=null) ForeachNonTerm.traverseBottomUp(visitor);
        if(ForeachVar!=null) ForeachVar.traverseBottomUp(visitor);
        if(ForeachColon!=null) ForeachColon.traverseBottomUp(visitor);
        if(ForeachArr!=null) ForeachArr.traverseBottomUp(visitor);
        if(ForeachBodyBegin!=null) ForeachBodyBegin.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Statement_foreach(\n");

        if(ForeachNonTerm!=null)
            buffer.append(ForeachNonTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForeachVar!=null)
            buffer.append(ForeachVar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForeachColon!=null)
            buffer.append(ForeachColon.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForeachArr!=null)
            buffer.append(ForeachArr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForeachBodyBegin!=null)
            buffer.append(ForeachBodyBegin.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Statement_foreach]");
        return buffer.toString();
    }
}
