// generated with ast extension for cup
// version 0.8
// 22/6/2026 0:51:55


package rs.ac.bg.etf.pp1.ast;

public class Condition_cond extends Condition {

    private ConditionTermList ConditionTermList;

    public Condition_cond (ConditionTermList ConditionTermList) {
        this.ConditionTermList=ConditionTermList;
        if(ConditionTermList!=null) ConditionTermList.setParent(this);
    }

    public ConditionTermList getConditionTermList() {
        return ConditionTermList;
    }

    public void setConditionTermList(ConditionTermList ConditionTermList) {
        this.ConditionTermList=ConditionTermList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConditionTermList!=null) ConditionTermList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConditionTermList!=null) ConditionTermList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConditionTermList!=null) ConditionTermList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Condition_cond(\n");

        if(ConditionTermList!=null)
            buffer.append(ConditionTermList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Condition_cond]");
        return buffer.toString();
    }
}
