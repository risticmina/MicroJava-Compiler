// generated with ast extension for cup
// version 0.8
// 22/6/2026 0:51:55


package rs.ac.bg.etf.pp1.ast;

public class ActParList_rec extends ActParList {

    private ActParListBegin ActParListBegin;
    private ActPars ActPars;
    private ActParMore ActParMore;

    public ActParList_rec (ActParListBegin ActParListBegin, ActPars ActPars, ActParMore ActParMore) {
        this.ActParListBegin=ActParListBegin;
        if(ActParListBegin!=null) ActParListBegin.setParent(this);
        this.ActPars=ActPars;
        if(ActPars!=null) ActPars.setParent(this);
        this.ActParMore=ActParMore;
        if(ActParMore!=null) ActParMore.setParent(this);
    }

    public ActParListBegin getActParListBegin() {
        return ActParListBegin;
    }

    public void setActParListBegin(ActParListBegin ActParListBegin) {
        this.ActParListBegin=ActParListBegin;
    }

    public ActPars getActPars() {
        return ActPars;
    }

    public void setActPars(ActPars ActPars) {
        this.ActPars=ActPars;
    }

    public ActParMore getActParMore() {
        return ActParMore;
    }

    public void setActParMore(ActParMore ActParMore) {
        this.ActParMore=ActParMore;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ActParListBegin!=null) ActParListBegin.accept(visitor);
        if(ActPars!=null) ActPars.accept(visitor);
        if(ActParMore!=null) ActParMore.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ActParListBegin!=null) ActParListBegin.traverseTopDown(visitor);
        if(ActPars!=null) ActPars.traverseTopDown(visitor);
        if(ActParMore!=null) ActParMore.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ActParListBegin!=null) ActParListBegin.traverseBottomUp(visitor);
        if(ActPars!=null) ActPars.traverseBottomUp(visitor);
        if(ActParMore!=null) ActParMore.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ActParList_rec(\n");

        if(ActParListBegin!=null)
            buffer.append(ActParListBegin.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActPars!=null)
            buffer.append(ActPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActParMore!=null)
            buffer.append(ActParMore.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ActParList_rec]");
        return buffer.toString();
    }
}
