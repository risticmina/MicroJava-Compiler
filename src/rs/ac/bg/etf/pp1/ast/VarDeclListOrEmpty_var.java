// generated with ast extension for cup
// version 0.8
// 22/6/2026 0:51:55


package rs.ac.bg.etf.pp1.ast;

public class VarDeclListOrEmpty_var extends VarDeclListOrEmpty {

    private VarDeclListOrEmpty VarDeclListOrEmpty;
    private VarDeclList VarDeclList;

    public VarDeclListOrEmpty_var (VarDeclListOrEmpty VarDeclListOrEmpty, VarDeclList VarDeclList) {
        this.VarDeclListOrEmpty=VarDeclListOrEmpty;
        if(VarDeclListOrEmpty!=null) VarDeclListOrEmpty.setParent(this);
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
    }

    public VarDeclListOrEmpty getVarDeclListOrEmpty() {
        return VarDeclListOrEmpty;
    }

    public void setVarDeclListOrEmpty(VarDeclListOrEmpty VarDeclListOrEmpty) {
        this.VarDeclListOrEmpty=VarDeclListOrEmpty;
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclListOrEmpty!=null) VarDeclListOrEmpty.accept(visitor);
        if(VarDeclList!=null) VarDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclListOrEmpty!=null) VarDeclListOrEmpty.traverseTopDown(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclListOrEmpty!=null) VarDeclListOrEmpty.traverseBottomUp(visitor);
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclListOrEmpty_var(\n");

        if(VarDeclListOrEmpty!=null)
            buffer.append(VarDeclListOrEmpty.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclListOrEmpty_var]");
        return buffer.toString();
    }
}
