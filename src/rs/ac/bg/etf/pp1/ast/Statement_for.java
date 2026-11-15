// generated with ast extension for cup
// version 0.8
// 22/6/2026 0:51:55


package rs.ac.bg.etf.pp1.ast;

public class Statement_for extends Statement {

    private ForNonTerm ForNonTerm;
    private DesignatorStmOpt DesignatorStmOpt;
    private ForCondBegin ForCondBegin;
    private ConditionOpt ConditionOpt;
    private ForCondEnd ForCondEnd;
    private ForUpdateBegin ForUpdateBegin;
    private DesignatorStmOpt DesignatorStmOpt1;
    private ForUpdateEnd ForUpdateEnd;
    private ForBodyBegin ForBodyBegin;
    private Statement Statement;

    public Statement_for (ForNonTerm ForNonTerm, DesignatorStmOpt DesignatorStmOpt, ForCondBegin ForCondBegin, ConditionOpt ConditionOpt, ForCondEnd ForCondEnd, ForUpdateBegin ForUpdateBegin, DesignatorStmOpt DesignatorStmOpt1, ForUpdateEnd ForUpdateEnd, ForBodyBegin ForBodyBegin, Statement Statement) {
        this.ForNonTerm=ForNonTerm;
        if(ForNonTerm!=null) ForNonTerm.setParent(this);
        this.DesignatorStmOpt=DesignatorStmOpt;
        if(DesignatorStmOpt!=null) DesignatorStmOpt.setParent(this);
        this.ForCondBegin=ForCondBegin;
        if(ForCondBegin!=null) ForCondBegin.setParent(this);
        this.ConditionOpt=ConditionOpt;
        if(ConditionOpt!=null) ConditionOpt.setParent(this);
        this.ForCondEnd=ForCondEnd;
        if(ForCondEnd!=null) ForCondEnd.setParent(this);
        this.ForUpdateBegin=ForUpdateBegin;
        if(ForUpdateBegin!=null) ForUpdateBegin.setParent(this);
        this.DesignatorStmOpt1=DesignatorStmOpt1;
        if(DesignatorStmOpt1!=null) DesignatorStmOpt1.setParent(this);
        this.ForUpdateEnd=ForUpdateEnd;
        if(ForUpdateEnd!=null) ForUpdateEnd.setParent(this);
        this.ForBodyBegin=ForBodyBegin;
        if(ForBodyBegin!=null) ForBodyBegin.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public ForNonTerm getForNonTerm() {
        return ForNonTerm;
    }

    public void setForNonTerm(ForNonTerm ForNonTerm) {
        this.ForNonTerm=ForNonTerm;
    }

    public DesignatorStmOpt getDesignatorStmOpt() {
        return DesignatorStmOpt;
    }

    public void setDesignatorStmOpt(DesignatorStmOpt DesignatorStmOpt) {
        this.DesignatorStmOpt=DesignatorStmOpt;
    }

    public ForCondBegin getForCondBegin() {
        return ForCondBegin;
    }

    public void setForCondBegin(ForCondBegin ForCondBegin) {
        this.ForCondBegin=ForCondBegin;
    }

    public ConditionOpt getConditionOpt() {
        return ConditionOpt;
    }

    public void setConditionOpt(ConditionOpt ConditionOpt) {
        this.ConditionOpt=ConditionOpt;
    }

    public ForCondEnd getForCondEnd() {
        return ForCondEnd;
    }

    public void setForCondEnd(ForCondEnd ForCondEnd) {
        this.ForCondEnd=ForCondEnd;
    }

    public ForUpdateBegin getForUpdateBegin() {
        return ForUpdateBegin;
    }

    public void setForUpdateBegin(ForUpdateBegin ForUpdateBegin) {
        this.ForUpdateBegin=ForUpdateBegin;
    }

    public DesignatorStmOpt getDesignatorStmOpt1() {
        return DesignatorStmOpt1;
    }

    public void setDesignatorStmOpt1(DesignatorStmOpt DesignatorStmOpt1) {
        this.DesignatorStmOpt1=DesignatorStmOpt1;
    }

    public ForUpdateEnd getForUpdateEnd() {
        return ForUpdateEnd;
    }

    public void setForUpdateEnd(ForUpdateEnd ForUpdateEnd) {
        this.ForUpdateEnd=ForUpdateEnd;
    }

    public ForBodyBegin getForBodyBegin() {
        return ForBodyBegin;
    }

    public void setForBodyBegin(ForBodyBegin ForBodyBegin) {
        this.ForBodyBegin=ForBodyBegin;
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
        if(ForNonTerm!=null) ForNonTerm.accept(visitor);
        if(DesignatorStmOpt!=null) DesignatorStmOpt.accept(visitor);
        if(ForCondBegin!=null) ForCondBegin.accept(visitor);
        if(ConditionOpt!=null) ConditionOpt.accept(visitor);
        if(ForCondEnd!=null) ForCondEnd.accept(visitor);
        if(ForUpdateBegin!=null) ForUpdateBegin.accept(visitor);
        if(DesignatorStmOpt1!=null) DesignatorStmOpt1.accept(visitor);
        if(ForUpdateEnd!=null) ForUpdateEnd.accept(visitor);
        if(ForBodyBegin!=null) ForBodyBegin.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ForNonTerm!=null) ForNonTerm.traverseTopDown(visitor);
        if(DesignatorStmOpt!=null) DesignatorStmOpt.traverseTopDown(visitor);
        if(ForCondBegin!=null) ForCondBegin.traverseTopDown(visitor);
        if(ConditionOpt!=null) ConditionOpt.traverseTopDown(visitor);
        if(ForCondEnd!=null) ForCondEnd.traverseTopDown(visitor);
        if(ForUpdateBegin!=null) ForUpdateBegin.traverseTopDown(visitor);
        if(DesignatorStmOpt1!=null) DesignatorStmOpt1.traverseTopDown(visitor);
        if(ForUpdateEnd!=null) ForUpdateEnd.traverseTopDown(visitor);
        if(ForBodyBegin!=null) ForBodyBegin.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ForNonTerm!=null) ForNonTerm.traverseBottomUp(visitor);
        if(DesignatorStmOpt!=null) DesignatorStmOpt.traverseBottomUp(visitor);
        if(ForCondBegin!=null) ForCondBegin.traverseBottomUp(visitor);
        if(ConditionOpt!=null) ConditionOpt.traverseBottomUp(visitor);
        if(ForCondEnd!=null) ForCondEnd.traverseBottomUp(visitor);
        if(ForUpdateBegin!=null) ForUpdateBegin.traverseBottomUp(visitor);
        if(DesignatorStmOpt1!=null) DesignatorStmOpt1.traverseBottomUp(visitor);
        if(ForUpdateEnd!=null) ForUpdateEnd.traverseBottomUp(visitor);
        if(ForBodyBegin!=null) ForBodyBegin.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Statement_for(\n");

        if(ForNonTerm!=null)
            buffer.append(ForNonTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorStmOpt!=null)
            buffer.append(DesignatorStmOpt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForCondBegin!=null)
            buffer.append(ForCondBegin.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConditionOpt!=null)
            buffer.append(ConditionOpt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForCondEnd!=null)
            buffer.append(ForCondEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForUpdateBegin!=null)
            buffer.append(ForUpdateBegin.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorStmOpt1!=null)
            buffer.append(DesignatorStmOpt1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForUpdateEnd!=null)
            buffer.append(ForUpdateEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForBodyBegin!=null)
            buffer.append(ForBodyBegin.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Statement_for]");
        return buffer.toString();
    }
}
