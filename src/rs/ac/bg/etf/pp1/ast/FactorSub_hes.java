// generated with ast extension for cup
// version 0.8
// 22/6/2026 0:51:55


package rs.ac.bg.etf.pp1.ast;

public class FactorSub_hes extends FactorSub {

    private HesName HesName;

    public FactorSub_hes (HesName HesName) {
        this.HesName=HesName;
        if(HesName!=null) HesName.setParent(this);
    }

    public HesName getHesName() {
        return HesName;
    }

    public void setHesName(HesName HesName) {
        this.HesName=HesName;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(HesName!=null) HesName.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(HesName!=null) HesName.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(HesName!=null) HesName.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FactorSub_hes(\n");

        if(HesName!=null)
            buffer.append(HesName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FactorSub_hes]");
        return buffer.toString();
    }
}
