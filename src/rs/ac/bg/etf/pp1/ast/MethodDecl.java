// generated with ast extension for cup
// version 0.8
// 22/6/2026 0:51:55


package rs.ac.bg.etf.pp1.ast;

public class MethodDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private MethodDeclRetAndName MethodDeclRetAndName;
    private FormParsOrNot FormParsOrNot;
    private VarDeclListOrEmpty VarDeclListOrEmpty;
    private StatementOrEmpty StatementOrEmpty;

    public MethodDecl (MethodDeclRetAndName MethodDeclRetAndName, FormParsOrNot FormParsOrNot, VarDeclListOrEmpty VarDeclListOrEmpty, StatementOrEmpty StatementOrEmpty) {
        this.MethodDeclRetAndName=MethodDeclRetAndName;
        if(MethodDeclRetAndName!=null) MethodDeclRetAndName.setParent(this);
        this.FormParsOrNot=FormParsOrNot;
        if(FormParsOrNot!=null) FormParsOrNot.setParent(this);
        this.VarDeclListOrEmpty=VarDeclListOrEmpty;
        if(VarDeclListOrEmpty!=null) VarDeclListOrEmpty.setParent(this);
        this.StatementOrEmpty=StatementOrEmpty;
        if(StatementOrEmpty!=null) StatementOrEmpty.setParent(this);
    }

    public MethodDeclRetAndName getMethodDeclRetAndName() {
        return MethodDeclRetAndName;
    }

    public void setMethodDeclRetAndName(MethodDeclRetAndName MethodDeclRetAndName) {
        this.MethodDeclRetAndName=MethodDeclRetAndName;
    }

    public FormParsOrNot getFormParsOrNot() {
        return FormParsOrNot;
    }

    public void setFormParsOrNot(FormParsOrNot FormParsOrNot) {
        this.FormParsOrNot=FormParsOrNot;
    }

    public VarDeclListOrEmpty getVarDeclListOrEmpty() {
        return VarDeclListOrEmpty;
    }

    public void setVarDeclListOrEmpty(VarDeclListOrEmpty VarDeclListOrEmpty) {
        this.VarDeclListOrEmpty=VarDeclListOrEmpty;
    }

    public StatementOrEmpty getStatementOrEmpty() {
        return StatementOrEmpty;
    }

    public void setStatementOrEmpty(StatementOrEmpty StatementOrEmpty) {
        this.StatementOrEmpty=StatementOrEmpty;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodDeclRetAndName!=null) MethodDeclRetAndName.accept(visitor);
        if(FormParsOrNot!=null) FormParsOrNot.accept(visitor);
        if(VarDeclListOrEmpty!=null) VarDeclListOrEmpty.accept(visitor);
        if(StatementOrEmpty!=null) StatementOrEmpty.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodDeclRetAndName!=null) MethodDeclRetAndName.traverseTopDown(visitor);
        if(FormParsOrNot!=null) FormParsOrNot.traverseTopDown(visitor);
        if(VarDeclListOrEmpty!=null) VarDeclListOrEmpty.traverseTopDown(visitor);
        if(StatementOrEmpty!=null) StatementOrEmpty.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodDeclRetAndName!=null) MethodDeclRetAndName.traverseBottomUp(visitor);
        if(FormParsOrNot!=null) FormParsOrNot.traverseBottomUp(visitor);
        if(VarDeclListOrEmpty!=null) VarDeclListOrEmpty.traverseBottomUp(visitor);
        if(StatementOrEmpty!=null) StatementOrEmpty.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDecl(\n");

        if(MethodDeclRetAndName!=null)
            buffer.append(MethodDeclRetAndName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormParsOrNot!=null)
            buffer.append(FormParsOrNot.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclListOrEmpty!=null)
            buffer.append(VarDeclListOrEmpty.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementOrEmpty!=null)
            buffer.append(StatementOrEmpty.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDecl]");
        return buffer.toString();
    }
}
