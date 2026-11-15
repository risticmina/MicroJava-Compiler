package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;

public class CodeGenerator extends VisitorAdaptor {
	
	private int mainPc;
	
	public int getMainPC() {
		
		return this.mainPc;
		
	}
	
	@Override
	public void visit(MethodDeclRetAndName_type methodDeclRetAndName_type) {
		
		methodDeclRetAndName_type.obj.setAdr(Code.pc); //pre enter instrukcije
		if(methodDeclRetAndName_type.getI2().equalsIgnoreCase("main")) {
			this.mainPc = Code.pc;
		}
		Code.put(Code.enter);
		Code.put(methodDeclRetAndName_type.obj.getLevel());//BROJ formalnih parametara b1
		Code.put(methodDeclRetAndName_type.obj.getLocalSymbols().size());// b2, broj lokalnih i formalnih par
		
	}
	
	@Override
	public void visit(MethodDeclRetAndName_void methodDeclRetAndName_void) {
		
		methodDeclRetAndName_void.obj.setAdr(Code.pc);//pre enter instrukrice 
		if(methodDeclRetAndName_void.getI1().equalsIgnoreCase("main")) {
			this.mainPc = Code.pc;
		}
		Code.put(Code.enter);
		Code.put(methodDeclRetAndName_void.obj.getLevel());//BROJ formalnih parametara b1
		Code.put(methodDeclRetAndName_void.obj.getLocalSymbols().size());// b2, broj lokalnih i formalnih par
		
	}
	
	@Override
	public void visit(MethodDecl methodDecl) {
		
		Code.put(Code.exit);
		Code.put(Code.return_);
		
	}
	
	@Override
	public void visit(Statement_print1 statement_print1) {
		
		Code.loadConst(0);
		if(statement_print1.getExpr().struct.equals(Tab.charType)) {
			Code.put(Code.bprint);
		}
		else {
			Code.put(Code.print);
		}		
		
	}
	
	@Override
	public void visit(Statement_print2 statement_print2) {
		
		Code.loadConst(statement_print2.getN2());
		if(statement_print2.getExpr().struct.equals(Tab.charType)) {
			Code.put(Code.bprint);
		}
		else {
			Code.put(Code.print);
		}	
		
	}
	
	@Override
	public void visit(FactorSub_n factorSub_n) {
		
		Code.loadConst(factorSub_n.getN1());
		
	}
	
	@Override
	public void visit(FactorSub_c factorSub_c) {
		
		Code.loadConst(factorSub_c.getC1());
		
	}
	
	@Override
	public void visit(FactorSub_b factorSub_b) {
		
		Code.loadConst(factorSub_b.getB1());
		
	}
	
	@Override
	public void visit(AddopTermList_add addopTermList_add) {
		
		if(addopTermList_add.getAddop() instanceof Addop_plus) {
			Code.put(Code.add);
		}
		else if(addopTermList_add.getAddop() instanceof Addop_minus) {
			Code.put(Code.sub);
		}
		
	}
	
	@Override
	public void visit(MulopFactorList_mul mulopFactorList_mul) {
		
		if(mulopFactorList_mul.getMulop() instanceof Mulop_mul) {
			Code.put(Code.mul);
		}
		else if(mulopFactorList_mul.getMulop() instanceof Mulop_div) {
			Code.put(Code.div);
		}
		else if(mulopFactorList_mul.getMulop() instanceof Mulop_rem) {
			Code.put(Code.rem);
		}
		
	}
	
	@Override
	public void visit(FactorSub_des factorSub_des) {
		
		if(!(factorSub_des.getDesignator() instanceof Designator_dot)) {
			Code.load(factorSub_des.getDesignator().obj);
		}
		
	}
	
	@Override
	public void visit(DesignatorArrayName designatorArrayName) {
		
		Code.load(designatorArrayName.obj);
		
	}
	
	@Override
	public void visit(DesignatorStatement_expr designatorStatement_expr) {
		
		Code.store(designatorStatement_expr.getDesignator().obj);
		
	}
	
	@Override
	public void visit(Factor factor) {
		
		if(factor.getUnary() instanceof Unary_m) {
			Code.put(Code.neg);
		}
		
	}
	
	@Override
	public void visit(FactorSub_new_array factorSub_new_array) {
		
		Code.put(Code.newarray);
		if(factorSub_new_array.getType().struct.equals(Tab.charType)) {
			Code.put(0);
		}
		else {
			Code.put(1);
		}
		
	}
	
	@Override
	public void visit(DesignatorStatement_inc designatorStatement_inc) {
		
		if(designatorStatement_inc.getDesignator().obj.getKind() == Obj.Elem)
			Code.put(Code.dup2);
		Code.load(designatorStatement_inc.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(designatorStatement_inc.getDesignator().obj);
		
	}
	
	@Override
	public void visit(DesignatorStatement_dec designatorStatement_dec) {
		
		if(designatorStatement_dec.getDesignator().obj.getKind() == Obj.Elem)
			Code.put(Code.dup2);
		Code.load(designatorStatement_dec.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(designatorStatement_dec.getDesignator().obj);
		
	}
	
	@Override
	public void visit(Statement_return1 statement_return1) {
		
		Code.put(Code.exit);
		Code.put(Code.return_);
		
	}
	
	@Override
	public void visit(Statement_return2 statement_return2) {
		
		Code.put(Code.exit);
		Code.put(Code.return_);
		
	}
	
	@Override
	public void visit(Statement_read statement_read) {
		
		if(statement_read.getDesignator().obj.getType().equals(Tab.charType)) {
			Code.put(Code.bread);
		}
		else {
			Code.put(Code.read);
		}
		Code.store(statement_read.getDesignator().obj);
		
	}
	
	@Override
	public void visit(Designator_dot designator_dot) {
		
		Code.put(Code.arraylength);
		
	}
	
	@Override
	public void visit(DesignatorStatement_act_pars designatorStatement_act_pars) {
		
		Obj methObj = designatorStatement_act_pars.getDesignator().obj;
		String name = methObj.getName();
		if(name.equalsIgnoreCase("chr") || name.equalsIgnoreCase("ord")) {
			Code.put(Code.pop); 
			return;
		}
		if(name.equalsIgnoreCase("len")) {
			Code.put(Code.arraylength);
			Code.put(Code.pop);
			return;
		}
		int offset = methObj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offset);
		if(methObj.getType() != Tab.noType) {
			Code.put(Code.pop);
		}
		
	}
	
	@Override
	public void visit(FactorSub_meth factorSub_meth) {
		
		Obj methObj = factorSub_meth.getDesignator().obj;
		String name = methObj.getName();
		if(name.equalsIgnoreCase("chr") || name.equalsIgnoreCase("ord")) {
			return; 
		}
		if(name.equalsIgnoreCase("len")) {
			Code.put(Code.arraylength);
			return;
		}
		int offset = methObj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offset);
		
	}
	
	
	//condition 
	private Stack<Integer> skipCondFact = new Stack<>();
	private Stack<Integer> skipCondition = new Stack<>();
	private Stack<Integer> skipThen = new Stack<>();
	private Stack<Integer> skipElse= new Stack<>();
	
	private int returnRelop(Relop relop) {
		
		if(relop instanceof Relop_eq) {
			return Code.eq;
		}
		else if(relop instanceof Relop_ne) {
			return Code.ne;
		}
		else if(relop instanceof Relop_gt) {
			return Code.gt;
		}
		else if(relop instanceof Relop_ge) {
			return Code.ge;
		}
		else if(relop instanceof Relop_lt) {
			return Code.lt;
		}
		else if(relop instanceof Relop_le) {
			return Code.le;
		}
		else return 0;
		
	}
	
	
	@Override
	public void visit(CondFact_expr condFact_expr) {
		
		Code.loadConst(0);
		Code.putFalseJump(Code.ne, 0); 
		skipCondFact.push(Code.pc - 2);	
		
	}
	
	@Override
	public void visit(CondFact_relop condFact_relop) {
		
		Code.putFalseJump(returnRelop(condFact_relop.getRelop()), 0); 
		skipCondFact.push(Code.pc - 2);
		
	}
	
	@Override
	public void visit(CondTerm condTerm) {
		
		Code.putJump(0); 
		skipCondition.push(Code.pc - 2); 

		while(!skipCondFact.empty()) {
			Code.fixup(skipCondFact.pop());
		}
		
	}
	
	@Override
	public void visit(Condition_cond condition_cond) {
	
		Code.putJump(0); 
		skipThen.push(Code.pc - 2);
		while(!skipCondition.empty()) {
			Code.fixup(skipCondition.pop());
		}
	
	}
	
	@Override
	public void visit(ElseStmt_no elseStmt_no) {
		
		Code.fixup(skipThen.pop());
		
		
	}
	
	@Override
	public void visit(Else else_) {
		
		Code.putJump(0);
		skipElse.push(Code.pc - 2);
		Code.fixup(skipThen.pop()); 
		
	}
	
	
	@Override
	public void visit(ElseStmt_yes elseStmt_yes) {
		
		Code.fixup(skipElse.pop()); 
		
	}
	
	private Stack<Integer> forCondBegin = new Stack<>();
	private Stack<Integer> forUpdateBegin = new Stack<>();
	private Stack<Integer> skipToBody = new Stack<>();
	private Stack<Boolean> forHasCondition = new Stack<>();
	
	@Override
	public void visit(ForCondBegin forCondBegin_) {
		
		forCondBegin.push(Code.pc);
		
	}
	
	@Override
	public void visit(ConditionOpt_cond conditionOpt_cond) {
		
		forHasCondition.push(true);
		
	}
	
	@Override
	public void visit(ConditionOpt_e conditionOpt_e) {
		
		forHasCondition.push(false);
		
	}
	
	@Override
	public void visit(ForCondEnd forCondEnd) {
		
		Code.putJump(0); 
		skipToBody.push(Code.pc - 2); 
		
	}
	
	@Override
	public void visit(ForUpdateBegin forUpdateBegin_) {
		
		forUpdateBegin.push(Code.pc);
		
	}
	
	@Override
	public void visit(ForUpdateEnd forUpdateEnd) {
		
		Code.putJump(forCondBegin.pop());
		
	}
	
	@Override
	public void visit(ForBodyBegin forBodyBegin) {
		
		Code.fixup(skipToBody.pop());
		
	}

	@Override
	public void visit(Statement_for statement_for) {
		
		Code.putJump(forUpdateBegin.pop());
		if(forHasCondition.pop()) {
			Code.fixup(skipThen.pop());
		}
		while(!breakJumps.peek().isEmpty()) {
			Code.fixup(breakJumps.peek().remove(0));
		}
		breakJumps.pop();
		
	}
	
	//break i continue
	private Stack<List<Integer>> breakJumps = new Stack<>();
	
	
	@Override
	public void visit(ForNonTerm forNonTerm) {
		
		breakJumps.push(new ArrayList<Integer>());
		
	}
	
	@Override
	public void visit(Statement_break statement_break) {
		
		Code.putJump(0);
		breakJumps.peek().add(Code.pc - 2);
		
	}
	
	@Override
	public void visit(Statement_continue statement_continue) {
		
		 Code.putJump(forUpdateBegin.peek());
		 
	}
	
	@Override
	public void visit(TernaryColon ternaryColon) {
		
		Code.putJump(0);
		skipElse.push(Code.pc - 2);
		Code.fixup(skipThen.pop());
		
	}
	
	@Override
	public void visit(Expr_condition expr_condition) {
		
		Code.fixup(skipElse.pop());
	}
	
	//findAny
	
	@Override
	public void visit(FactorSub_find_any factorSub_find_any) {
		
		Obj[] temps = SemanticAnalyzer.findAnyTemps.get(factorSub_find_any);
	    Obj arrTemp = temps[0];
	    Obj valTemp = temps[1];
	    Obj idxTemp = temps[2];
	    Obj arrObj = factorSub_find_any.getDesignatorArrayName().obj;
	    Obj elemDummy = new Obj(Obj.Elem, "elem", arrObj.getType().getElemType());
	    
	    Code.store(valTemp);  
	    Code.store(arrTemp);   
	    Code.loadConst(0);
	    Code.store(idxTemp);  

	    int loopStart = Code.pc;

	    Code.load(idxTemp);
	    Code.load(arrTemp);
	    Code.put(Code.arraylength);
	    Code.putFalseJump(Code.lt, 0);
	    int notFoundJump = Code.pc - 2;

	    Code.load(arrTemp);
	    Code.load(idxTemp);
	    Code.load(elemDummy);
	    Code.load(valTemp);
	    Code.putFalseJump(Code.eq, 0);
	    int notEqualJump = Code.pc - 2;

	    Code.loadConst(1);
	    Code.putJump(0);
	    int doneJump = Code.pc - 2;
	    
	    Code.fixup(notEqualJump);
	    Code.load(idxTemp);
	    Code.loadConst(1);
	    Code.put(Code.add);
	    Code.store(idxTemp);
	    Code.putJump(loopStart);

	    Code.fixup(notFoundJump);
	    Code.loadConst(0);

	    Code.fixup(doneJump);
	}
	
	private Stack<Integer> mapLoopStart = new Stack<>();
	private Stack<Integer> mapEndJump = new Stack<>();
	
	//map
	@Override
	public void visit(MapBegin mapBegin) {
		
		Obj[] temps = SemanticAnalyzer.mapTemps.get(mapBegin);
	    Obj srcArrTemp  = temps[0];
	    Obj destArrTemp = temps[1];
	    Obj idxTemp     = temps[2];
	    Obj identObj    = temps[3];

	    Code.store(srcArrTemp);

	    Code.load(srcArrTemp);
	    Code.put(Code.arraylength);
	    Code.put(Code.newarray);
	    Code.put(destArrTemp.getType().getElemType().equals(Tab.charType) ? 0 : 1);
	    Code.store(destArrTemp);

	    Code.loadConst(0);
	    Code.store(idxTemp);       

	    int loopStart = Code.pc;
	    Code.load(idxTemp);
	    Code.load(srcArrTemp);
	    Code.put(Code.arraylength);
	    Code.putFalseJump(Code.lt, 0);   
	    int endJump = Code.pc - 2;

	   
	    Obj srcElemDummy = new Obj(Obj.Elem, "elem", srcArrTemp.getType().getElemType());
	    Code.load(srcArrTemp);
	    Code.load(idxTemp);
	    Code.load(srcElemDummy);
	    Code.store(identObj);

	    Code.load(destArrTemp);
	    Code.load(idxTemp);

	    mapLoopStart.push(loopStart);
	    mapEndJump.push(endJump);
	    
	}
	
	@Override
	public void visit(FactorSub_map factorSub_map) {
		
		Obj[] temps = SemanticAnalyzer.mapTemps.get(factorSub_map.getMapBegin());
	    Obj srcArrTemp  = temps[0];
	    Obj destArrTemp = temps[1];
	    Obj idxTemp     = temps[2];

	    Obj destElemDummy = new Obj(Obj.Elem, "elem", destArrTemp.getType().getElemType());
	    Code.store(destElemDummy);      

	    Code.load(idxTemp);
	    Code.loadConst(1);
	    Code.put(Code.add);
	    Code.store(idxTemp);             

	    Code.putJump(mapLoopStart.pop()); 
	    Code.fixup(mapEndJump.pop());
	    Code.load(destArrTemp); 
	    
	}
	
	private Stack<Integer> loopStart = new Stack<>();
			
	@Override
	public void visit(WhileBodyEnd whileBodyEnd) {
		
		Code.putJump(loopStart.pop());
		
	}
	
	@Override
	public void visit(WhileCondBegin whileCondBegin) {
		
		loopStart.push(Code.pc);
		
	}
	
	@Override
	public void visit(Statement_while statement_while) {
		
		Code.fixup(skipThen.pop());	
		
	}
	
	//foreach
	
	private Stack<Obj> foreachVarObj = new Stack<>();
	private Stack<Obj> foreachArrObj = new Stack<>();
	private Stack<Integer> foreachCondStart = new Stack<>();
	private Stack<Integer> foreachExitJump = new Stack<>();
	
	@Override
	public void visit(ForeachVar foreachVar) {
		
		foreachVarObj.push(foreachVar.getDesignator().obj);
		
	}
	
	@Override
	public void visit(ForeachArr foreachArr) {
		
		foreachArrObj.push(foreachArr.getDesignator().obj);
		
	}
	
	@Override
	public void visit(ForeachBodyBegin foreachBodyBegin) {
		
		Obj arr = foreachArrObj.peek();
		
		Code.load(arr);
		Code.loadConst(0);
		
		foreachCondStart.push(Code.pc);
		
		Code.put(Code.dup2);
		Code.put(Code.pop);
		Code.put(Code.arraylength);
		Code.put(Code.dup2);
		Code.putFalseJump(Code.lt, 0);
		foreachExitJump.push(Code.pc - 2);
		
		Code.put(Code.pop);
		
		Obj d1 = foreachVarObj.peek();
		Obj elemDummyObj = new Obj(Obj.Elem, "elem", arr.getType().getElemType());
		
		Code.put(Code.dup2);
		Code.load(elemDummyObj);
		Code.store(d1);
		
		breakJumps.push(new ArrayList<Integer>());
		
	}
	
	@Override
	public void visit(Statement_foreach statement_foreach) {
		
		Code.loadConst(1);
		Code.put(Code.add);
		Code.putJump(foreachCondStart.pop());
		
		Code.fixup(foreachExitJump.pop());
		Code.put(Code.pop);
		
		while(!breakJumps.peek().isEmpty()) {
			Code.fixup(breakJumps.peek().remove(0));
		}
		
		Code.put(Code.pop);
		Code.put(Code.pop);
		
		breakJumps.pop();
		foreachArrObj.pop();
		foreachVarObj.pop();
		
	}
	
	private Stack<Integer> skipHesArray = new Stack<>();
	int loopstart;
	
	@Override
	public void visit(HesName hesName) {
		
		Obj arr = hesName.obj;
		
		Code.put(Code.load_3);
		Code.put(Code.load_2);
		Code.put(Code.load_1);
		
		Code.load(arr);
		Code.loadConst(0);
		Code.put(Code.aload);
		
		Code.loadConst(1);
		
		Code.put(Code.store_2);
		
		loopstart = Code.pc;
		
		Code.put(Code.load_2);
	
		Code.load(arr);
		Code.put(Code.arraylength);
		
		Code.putFalseJump(Code.lt, 0);
		skipHesArray.push(Code.pc - 2);
		
		Code.put(Code.load_2);
		
		Code.put(Code.dup);
		Code.load(arr);
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
		Code.put(Code.aload);
		
		Code.put(Code.store_3);
		
		Code.put(Code.store_2); 
		Code.put(Code.store_1); 
		
		Code.put(Code.load_2);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.put(Code.store_2);
		
		Code.put(Code.load_1);
		Code.put(Code.load_3);
	
		Code.put(Code.load_1);
		
		Code.putFalseJump(Code.gt, loopstart);
		
		Code.put(Code.load_3);
		
		Code.put(Code.store_1);
		Code.put(Code.pop);
		
		Code.put(Code.load_1);
		
		Code.putJump(loopstart);
		
	}
	
	@Override
	public void visit(FactorSub_hes factorSub_hes) {
		
		Code.put(Code.store_1);
		while(!skipHesArray.isEmpty()) {
			Code.fixup(skipHesArray.pop());
		}
		
	}
	
}


