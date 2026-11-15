package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.concepts.Struct;

public class ActParCounter extends VisitorAdaptor {
	
	List<Struct> FinalActParList;
	
	Stack<List<Struct>> actParLists = new Stack<>();
	
	@Override
	public void visit(ActParListBegin actParListBegin) {
		
		actParLists.push(new ArrayList<>());
		
	}
	
	@Override
	public void visit(ActPar actPar) {
		
		actParLists.peek().add(actPar.getExpr().struct);
		
	}
	
	@Override
	public void visit(ActParList_rec actParList_rec) {
		
		FinalActParList = actParLists.pop();
		
	}
	
	@Override
	public void visit(ActParList_e actParList_e) {
		
		FinalActParList = actParLists.pop();
		
	}
}
