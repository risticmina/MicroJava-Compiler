package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.*;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticAnalyzer extends VisitorAdaptor {

	private boolean errorDetected = false;
	Logger log = Logger.getLogger(getClass());
	private Obj currentProgam;
	private Struct currentType;
	private int constant;
	private Struct constantType;
	private Struct boolType = Tab.find("bool").getType();
	private Obj currentMethod;
	private Obj mainMethod;
	private boolean isMap = false;
	private boolean isFindAny = false;
	private boolean returnHappend = false;
	private int loopCnt = 0;
	int nVars;
	private int tempCounter = 0;
	public static Map<SyntaxNode, Obj[]> findAnyTemps = new IdentityHashMap<>();
	public static Map<SyntaxNode, Obj[]> mapTemps = new IdentityHashMap<>();
	
	public static Map<SyntaxNode, Obj[]> foreachIdxVars = new HashMap<>();
	
	public void report_error(String message, SyntaxNode info) {
		
		errorDetected  = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	public boolean passed() {
		
		return !errorDetected;
		
	}
	
	private String kindToStr(int kind) {
		
	    switch(kind) {
	        case Obj.Var:  return "Var";
	        case Obj.Con:  return "Con";
	        case Obj.Meth: return "Meth";
	        case Obj.Type: return "Type";
	        case Obj.Prog: return "Prog";
	        case Obj.Elem: return "Elem";
	        default: return "Obj";
	    }
	    
	}

	private String structToStr(Struct s) {
		
	    if(s == null || s.equals(Tab.noType)) return "notype";
	    if(s.getKind() == Struct.Array) return "Arr of " + structToStr(s.getElemType());
	    if(s.equals(Tab.intType)) return "int";
	    if(s.equals(Tab.charType)) return "char";
	    if(s.equals(boolType)) return "bool";
	    return "Class";
	    
	}

	private String dumpObj(Obj o) {
		
	    return kindToStr(o.getKind()) + " " + o.getName() + ": " + structToStr(o.getType())
	           + ", " + o.getAdr() + ", " + o.getLevel();
	    
	}

	private void logSearch(int line, String name, Obj obj) {
		
	    if(obj == null || obj == Tab.noObj) {
	        log.info("Greska na " + line + "(" + name + ") nije nadjeno ");
	    } else {
	        log.info("Pretraga na " + line + "(" + name + "), nadjeno " + dumpObj(obj) + " ");
	    }
	    
	}
	

	@Override
	public void visit(ProgramName programName) {
		
		currentProgam = Tab.insert(Obj.Prog, programName.getI1(), Tab.noType);
		Tab.openScope();
		
	}
	
	@Override
	public void visit(Program program) {
		
		nVars = Tab.currentScope().getnVars();
		Tab.chainLocalSymbols(currentProgam);
		Tab.closeScope();
		currentProgam = null;
		
		if(mainMethod == null || mainMethod.getLevel() > 0) {
			report_error("Program nema adekvatnu main metodu", program);
		}
	
	}
	
	@Override
	public void visit(ConDecl conDecl) {
		
		Obj conObj = Tab.find(conDecl.getI1());
		if(conObj != Tab.noObj) {
			report_error("Dvostruka definicija konstante: "+conDecl.getI1(), conDecl);
		}
		else {
			if(constantType.assignableTo(currentType)) {
				conObj = Tab.insert(Obj.Con, conDecl.getI1(), currentType);
		 		conObj.setAdr(constant);
			}
			else {
				report_error("Neadekvatna dodela konstanti: " + conDecl.getI1(), conDecl);
			}
		}
		
	}
	
	@Override
	public void visit(Constant_n constant_n) {
		
		constant = constant_n.getN1();
		constantType = Tab.intType;
		
	}
	
	@Override
	public void visit(Constant_c constant_c) {
		constant = constant_c.getC1();
		constantType = Tab.charType;
	}
	
	@Override
	public void visit(Constant_b constant_b) {
		
		constant = constant_b.getB1();
		constantType = boolType;
		
	}
	
	@Override
	public void visit(VarDeclArr_var varDeclArr_var) {
		
		Obj varObj = null;
		if(currentMethod == null) {
			varObj = Tab.find(varDeclArr_var.getI1());
		}
		else {
			varObj = Tab.currentScope().findSymbol(varDeclArr_var.getI1());
		}
		if(varObj == null || varObj == Tab.noObj) {
			varObj = Tab.insert(Obj.Var, varDeclArr_var.getI1(), currentType);
		}
		else{
			report_error("Dvostruka definicija promenljive: "+varDeclArr_var.getI1(), varDeclArr_var);
		}
		
	}
	
	@Override
	public void visit(VarDeclArr_arr varDeclArr_arr) {
		
		Obj varObj = null;
		if(currentMethod == null) {
			varObj = Tab.find(varDeclArr_arr.getI1());
		}
		else {
			varObj = Tab.currentScope().findSymbol(varDeclArr_arr.getI1());
		}
		if(varObj == null || varObj == Tab.noObj) {
			varObj = Tab.insert(Obj.Var, varDeclArr_arr.getI1(), new Struct(Struct.Array, currentType));
		}
		else{
			report_error("Dvostruka definicija promenljive: "+varDeclArr_arr.getI1(), varDeclArr_arr);
		}
		
	}
	
	@Override
	public void visit(MethodDeclRetAndName_void methodDeclRetAndName_void) {
		
		Obj methObj = Tab.currentScope().findSymbol(methodDeclRetAndName_void.getI1());
		if(methObj == null || methObj == Tab.noObj) {
			methodDeclRetAndName_void.obj= currentMethod = Tab.insert(Obj.Meth, methodDeclRetAndName_void.getI1(), Tab.noType);
			Tab.openScope();
			if(methodDeclRetAndName_void.getI1().equalsIgnoreCase("main"))
				mainMethod = currentMethod;
		}
		else {
			report_error("Dvostruka definicija metode main: "+methodDeclRetAndName_void.getI1(), methodDeclRetAndName_void);
		}
		
	}
	
	@Override
	public void visit(MethodDeclRetAndName_type methodDeclRetAndName_type) {
		
		Obj methObj = Tab.currentScope().findSymbol(methodDeclRetAndName_type.getI2());
		if(methObj == null || methObj == Tab.noObj) {
			methodDeclRetAndName_type.obj = currentMethod = Tab.insert(Obj.Meth, methodDeclRetAndName_type.getI2(), currentType);
			Tab.openScope();
		}
		else {
			report_error("Dvostruka definicija metode: "+methodDeclRetAndName_type.getI2(), methodDeclRetAndName_type);
		}
		
	}
	
	@Override
	public void visit(MethodDecl methodDecl) {
		if(currentMethod != null) {
			Tab.chainLocalSymbols(currentMethod);
			Tab.closeScope();
			if(currentMethod.getType() != Tab.noType && !returnHappend) {
				report_error("Ne postoji iskaz return unutar metode" + currentMethod.getName(), methodDecl);
			}
			currentMethod = null;
			returnHappend = false;
		}
		
		
	}
	
	@Override
	public void visit(FormPars_var formPars_var) {
		
		Obj varObj = null;
		if(currentMethod == null) {
			report_error("Semanticka greska [formPars_var]", formPars_var);
		}
		else {
			varObj = Tab.currentScope().findSymbol(formPars_var.getI2());
		}
		if(varObj == null || varObj == Tab.noObj) {
			varObj = Tab.insert(Obj.Var, formPars_var.getI2(), currentType);
			varObj.setFpPos(1);
			currentMethod.setLevel(currentMethod.getLevel() + 1);
		}
		else{
			report_error("Dvostruka definicija formalnog parametra : "+formPars_var.getI2(), formPars_var);
		}
		
	}
	
	@Override
	public void visit(FormPars_arr formPars_arr) {
		
		Obj varObj = null;
		if(currentMethod == null) {
			report_error("Semanticka greska [formPars_arr]", formPars_arr);
		}
		else {
			varObj = Tab.currentScope().findSymbol(formPars_arr.getI2());
		}
		if(varObj == null || varObj == Tab.noObj) {
			varObj = Tab.insert(Obj.Var, formPars_arr.getI2(), new Struct(Struct.Array, currentType));
			varObj.setFpPos(1);
			currentMethod.setLevel(currentMethod.getLevel() + 1);
		}
		else{
			report_error("Dvostruka definicija formalnog parametra: "+formPars_arr.getI2(), formPars_arr);
		}
		
	}
	
	
	@Override
	public void visit(Type type) {
		
		Obj typeObj = Tab.find(type.getI1());
		if(typeObj == Tab.noObj) {
			report_error("Nepostojeci tip podatka: " +type.getI1(), type);
			type.struct = currentType = Tab.noType;
		}
		else if(typeObj.getKind()!= Obj.Type) {
			report_error("Neadekvatan tip podatka: " +type.getI1(), type);
			type.struct = currentType = Tab.noType;
		}
		else {
			type.struct = currentType = typeObj.getType();
			
		}
		
	}
	
	/*konteksni uslovii*/
	
	//designator
	
	@Override
	public void visit(Designator_ident designator_ident) {
		
		Obj varObj = Tab.find(designator_ident.getI1());
		logSearch(designator_ident.getLine(), designator_ident.getI1(), varObj);
		if(varObj == Tab.noObj) {
			report_error("Pristup nedefinisanoj promenljivi: " + designator_ident.getI1(), designator_ident);
			designator_ident.obj = Tab.noObj;
		}
		else if(varObj.getKind() != Obj.Var && varObj.getKind() != Obj.Con && varObj.getKind() != Obj.Meth) {
			report_error("Neadekvatna promenljiva: " + designator_ident.getI1(), designator_ident);
			designator_ident.obj = Tab.noObj;
		}
		else {
			designator_ident.obj = varObj;
		}
		
	}
	
	
	@Override
	public void visit(Designator_dot designator_dot) {
		
		Obj arrObj = designator_dot.getDesignatorArrayName().obj;
		if(arrObj == Tab.noObj)
			designator_dot.obj = Tab.noObj;
		else {
			designator_dot.obj = new Obj(Obj.Var, "length", Tab.intType);
		}
		
	}
	
	@Override
	public void visit(Designator_expr designator_expr) {
		
		Obj arrObj = designator_expr.getDesignatorArrayName().obj;
		if(arrObj == Tab.noObj)
			designator_expr.obj = Tab.noObj;
		else if(!designator_expr.getExpr().struct.equals(Tab.intType)) {
			report_error("Indeksiranje sa ne int vrednosti. [Designator_elem]", designator_expr);
			designator_expr.obj = Tab.noObj;
		}
		else {
			designator_expr.obj = new Obj(Obj.Elem, arrObj.getName() + "[$]", arrObj.getType().getElemType());
		}
		
	}
	
	
	//factorsub
	
	@Override
	public void visit(FactorSub_c factorSub_c) {
		
		factorSub_c.struct = Tab.charType;
		
	}
	
	@Override
	public void visit(FactorSub_n factorSub_n) {
		factorSub_n.struct = Tab.intType;
	}
	
	@Override
	public void visit(FactorSub_b factorSub_b) {
		
		factorSub_b.struct = boolType;
		
	}
	
	@Override
	public void visit(FactorSub_des factorSub_des) {
		
		factorSub_des.struct = factorSub_des.getDesignator().obj.getType();
		
	}
	
	@Override
	public void visit(FactorSub_new_array factorSub_new_array) {
		
		if(!factorSub_new_array.getExpr().struct.equals(Tab.intType)) {
			report_error("Velicina niza nije int tipa!. [factorSub_new_array]", factorSub_new_array);
			factorSub_new_array.struct = Tab.noType;
		}
		else {
			factorSub_new_array.struct = new Struct(Struct.Array, currentType);
			
		}
		
	}
	
	@Override
	public void visit(FactorSub_expr factorSub_expr) {
		
		factorSub_expr.struct = factorSub_expr.getExpr().struct;
		
	}
	
	@Override
	public void visit(FactorSub_meth factorSub_meth) {
		
		if(isFindAny || isMap) {
	        report_error("Nije dozvoljeno findAny i map u fji", factorSub_meth);
	        factorSub_meth.struct = Tab.noType;
	        isFindAny = false;
	        isMap = false;
	    }
		else if(factorSub_meth.getDesignator().obj.getKind() != Obj.Meth) {
			report_error("Poziv neadekvatne metode" + factorSub_meth.getDesignator().obj.getName(), factorSub_meth);
			factorSub_meth.struct = Tab.noType;
		}
		else {
			factorSub_meth.struct = factorSub_meth.getDesignator().obj.getType();
		
				List<Obj> paramObjs = new ArrayList<>();
				List<Struct> fpList = new ArrayList<>();
				for(Obj local: factorSub_meth.getDesignator().obj.getLocalSymbols()) {
					if(local.getKind() == Obj.Var && local.getAdr() < factorSub_meth.getDesignator().obj.getLevel()) {
						paramObjs.add(local);
					}
				}
				paramObjs.sort((a, b) -> Integer.compare(a.getAdr(), b.getAdr()));
				for(Obj p: paramObjs) {
					fpList.add(p.getType());
				}
				ActParCounter apc = new ActParCounter();
				factorSub_meth.getActParList().traverseBottomUp(apc);
				List<Struct> apList = apc.FinalActParList;
				try {
					if(fpList.size() != apList.size()) {
						throw new Exception("Greska velicina");
					}
					for(int i = 0; i < fpList.size(); i++) {
						Struct fps = fpList.get(i);
						Struct aps = apList.get(i);
						if(!aps.assignableTo(fps)) {
							throw new Exception("Greska tipovi");
						}
					}
					
				}
				catch (Exception e) {
					report_error("[" + e.getMessage() + "]Nekompatibilnost parametara pri pozivu metode "+ factorSub_meth.getDesignator().obj.getName(), factorSub_meth);
				}
		
		}
	
	}
	
	@Override
	public void visit(FactorSub_find_any factorSub_find_any) {
		
		Obj arrObj = factorSub_find_any.getDesignatorArrayName().obj;
		if(arrObj == Tab.noObj) {
			factorSub_find_any.struct = Tab.noType;
		}
		else if(!factorSub_find_any.getExpr().struct.equals(arrObj.getType().getElemType())) 
		{
			report_error("findAny: tip izraza ne odgovara tipu niza", factorSub_find_any);
			factorSub_find_any.struct = Tab.noType;
		}
		else {
			factorSub_find_any.struct = boolType;
			isFindAny = true;
			
			Obj arrTemp = Tab.insert(Obj.Var, "$farr" + tempCounter, arrObj.getType());
		    Obj valTemp = Tab.insert(Obj.Var, "$fval" + tempCounter, arrObj.getType().getElemType());
		    Obj idxTemp = Tab.insert(Obj.Var, "$fidx" + tempCounter, Tab.intType);
		    tempCounter++;

		    findAnyTemps.put(factorSub_find_any, new Obj[]{arrTemp, valTemp, idxTemp});
		}
		
	}
	
	private boolean isBuiltinType(Struct s) {
		
	    return s.equals(Tab.intType) || s.equals(Tab.charType) || s.equals(boolType);
	    
	}
	
	@Override
	public void visit(FactorSub_map factorSub_map) {
		
		Obj arrObj = factorSub_map.getDesignatorArrayName().obj;
		Obj identObj = Tab.find(factorSub_map.getI2());
		if(arrObj == Tab.noObj) {
			factorSub_map.struct = Tab.noType;
		}
		else if(!isBuiltinType(arrObj.getType().getElemType())) {
		        report_error("map: niz mora biti jednodimenzionalni niz ugradjenog tipa", factorSub_map);
		        factorSub_map.struct = Tab.noType;
		}
		else if(identObj == Tab.noObj) {
			report_error("Pristup nedefinisanoj promenljivi: " + factorSub_map.getI2(), factorSub_map);
			factorSub_map.struct = Tab.noType;
		}
		else if(identObj.getKind() != Obj.Var || !identObj.getType().equals(arrObj.getType().getElemType())){
			report_error("Neslaganje u tipu: " + factorSub_map.getI2(), factorSub_map);
			factorSub_map.struct = Tab.noType;
		}
		else {
			factorSub_map.struct = new Struct(Struct.Array, factorSub_map.getExpr().struct);
			isMap = true;
			
			 Obj srcArrTemp  = Tab.insert(Obj.Var, "$marr"  + tempCounter, arrObj.getType());
		     Obj destArrTemp = Tab.insert(Obj.Var, "$mdest" + tempCounter, factorSub_map.struct);
		     Obj idxTemp     = Tab.insert(Obj.Var, "$midx"  + tempCounter, Tab.intType);
		     tempCounter++;

		     mapTemps.put(factorSub_map.getMapBegin(), new Obj[]{srcArrTemp, destArrTemp, idxTemp, identObj});
			
		}
		
	}
	
	@Override
	public void visit(DesignatorArrayName designatorArrayName) {
		
		Obj arrObj = Tab.find(designatorArrayName.getI1());
		logSearch(designatorArrayName.getLine(), designatorArrayName.getI1(), arrObj);
		if(arrObj == Tab.noObj) {
			report_error("Pristup nedefinisanoj promenljivi niza: " + designatorArrayName.getI1(), designatorArrayName);
			designatorArrayName.obj = Tab.noObj;
		}
		else if(arrObj.getKind() != Obj.Var || arrObj.getType().getKind() != Struct.Array) {
			report_error("Neadekvatna promenljiva niza: " + designatorArrayName.getI1(), designatorArrayName);
			designatorArrayName.obj = Tab.noObj;
		}
		else {
			designatorArrayName.obj = arrObj;
		}
		
	}
	
	//factor
	
	@Override
	public void visit(Factor factor) {
		
		if(factor.getUnary() instanceof Unary_m) {
			if(factor.getFactorSub().struct.equals(Tab.intType)) {
				factor.struct = Tab.intType;
			}
			else {
				report_error("Negacija ne int vrednosti", factor);
				factor.struct = Tab.noType;
			}
		}
		else {
			factor.struct = factor.getFactorSub().struct;
		}
		
	}
	
	//expr
	
	@Override
	public void visit(MulopFactorList_factor mulopFactorList_factor) {
		
		mulopFactorList_factor.struct = mulopFactorList_factor.getFactor().struct;
		
	}
	
	@Override
	public void visit(MulopFactorList_mul mulopFactorList_mul) {
		
		Struct left = mulopFactorList_mul.getMulopFactorList().struct;
		Struct right = mulopFactorList_mul.getFactor().struct;
		
		if(left.equals(Tab.intType) && right.equals(Tab.intType)) {
			mulopFactorList_mul.struct = Tab.intType;
		}
		else {
			report_error("Mulop operacija ne int vrednosti.", mulopFactorList_mul);
			mulopFactorList_mul.struct = Tab.noType;
		}
		
	}
	
	@Override
	public void visit(Term term) {
		
		term.struct = term.getMulopFactorList().struct;
		
	}
	
	@Override
	public void visit(AddopTermList_term addopTermList_term) {
		
		addopTermList_term.struct = addopTermList_term.getTerm().struct;
		
	}
	
	@Override
	public void visit(AddopTermList_add addopTermList_add) {
		
		Struct right = addopTermList_add.getTerm().struct;
		Struct left = addopTermList_add.getAddopTermList().struct;
		if(left.equals(Tab.intType) && right.equals(Tab.intType)) {
			addopTermList_add.struct = Tab.intType;
		}
		else {
			report_error("Addop operacija ne int vrednosti", addopTermList_add);
			addopTermList_add.struct = Tab.noType;
		}
		
	}
	
	@Override
	public void visit(Expr_tem expr_tem) {
		
		expr_tem.struct = expr_tem.getAddopTermList().struct;
		
	}
	
	@Override
	public void visit(Expr_condition expr_condition) {
		
		Struct first = expr_condition.getExpr().struct;
		Struct second = expr_condition.getExpr1().struct;
		if(!first.equals(second)) {
			report_error("Tipovi izraza nisu jednaki!", expr_condition);
			expr_condition.struct = Tab.noType;
		}
		else {
			expr_condition.struct = first;
		}
		
	}
	
	//designator statements
	
	@Override
	public void visit(DesignatorStatement_expr designatorStatement_expr) {
		
		int kind = designatorStatement_expr.getDesignator().obj.getKind();
		if(kind != Obj.Var && kind != Obj.Elem) {
			report_error("Dodela u neadekvatnu promenljivu: " + designatorStatement_expr.getDesignator().obj.getName(), designatorStatement_expr);
		}
		else if(!designatorStatement_expr.getExpr().struct.assignableTo(designatorStatement_expr.getDesignator().obj.getType())) {
			report_error("Neadekvatna dodela vrednosti u promenljivu", designatorStatement_expr);
		}
		isFindAny = false;
		isMap = false;
		
	}
	
	@Override
	public void visit(DesignatorStatement_inc designatorStatement_inc) {
		
		int kind = designatorStatement_inc.getDesignator().obj.getKind();
		if(kind != Obj.Var && kind != Obj.Elem) {
			report_error("Inkrement neadekvatne promenljive " + designatorStatement_inc.getDesignator().obj.getName(), designatorStatement_inc);
		}
		else if(!designatorStatement_inc.getDesignator().obj.getType().equals(Tab.intType)) {
			report_error("Inkrement ne int promenljive: " + designatorStatement_inc.getDesignator().obj.getName(), designatorStatement_inc);
		}
		
	}
	
	@Override
	public void visit(DesignatorStatement_dec designatorStatement_dec) {
		
		int kind = designatorStatement_dec.getDesignator().obj.getKind();
		if(kind != Obj.Var && kind != Obj.Elem) {
			report_error("Dekrement neadekvatne promenljive" + designatorStatement_dec.getDesignator().obj.getName(), designatorStatement_dec);
		}
		else if(!designatorStatement_dec.getDesignator().obj.getType().equals(Tab.intType)) {
			report_error("Dekrement ne int promenljive: " + designatorStatement_dec.getDesignator().obj.getName(), designatorStatement_dec);
		}
		
	}
	
	@Override
	public void visit(DesignatorStatement_act_pars designatorStatement_act_pars) {
		
		if(isFindAny || isMap) {
		        report_error("Nije dozvoljeno findAny i map kao parametar", designatorStatement_act_pars);
		        isFindAny = false;
		        isMap = false;
		        return;
		    }
		else if(designatorStatement_act_pars.getDesignator().obj.getKind() != Obj.Meth) {
			report_error("Poziv neadekvatne metode" + designatorStatement_act_pars.getDesignator().obj.getName(), designatorStatement_act_pars);
		}
		else {
			List<Obj> paramObjs = new ArrayList<>();
			List<Struct> fpList = new ArrayList<>();
			for(Obj local: designatorStatement_act_pars.getDesignator().obj.getLocalSymbols()) {
				if(local.getKind() == Obj.Var && local.getAdr() < designatorStatement_act_pars.getDesignator().obj.getLevel()) {
					paramObjs.add(local);
				}
			}
			paramObjs.sort((a, b) -> Integer.compare(a.getAdr(), b.getAdr()));
			for(Obj p: paramObjs) {
				fpList.add(p.getType());
			}
			ActParCounter apc = new ActParCounter();
			designatorStatement_act_pars.getActParList().traverseBottomUp(apc);
			List<Struct> apList = apc.FinalActParList;
			try {
				
				if(fpList.size() != apList.size()) {
					throw new Exception("Greska velicina");
				}
				for(int i = 0; i < fpList.size(); i++) {
					Struct fps = fpList.get(i);
					Struct aps = apList.get(i);
					if(!aps.assignableTo(fps)) {
						throw new Exception("Greska tipovi");
					}
				}
				
			}
			catch (Exception e) {
				report_error("[" + e.getMessage() + "]Nekompatibilnost parametara pri pozivu metode "+ designatorStatement_act_pars.getDesignator().obj.getName(), designatorStatement_act_pars);
			}
		
		}
		
	}
	
	//statement
	
	@Override
	public void visit(Statement_read statement_read) {
		
		int kind = statement_read.getDesignator().obj.getKind();
		Struct type = statement_read.getDesignator().obj.getType();
		if(kind != Obj.Var && kind != Obj.Elem) {
			report_error("Read operacija neadekvatne promenljive " + statement_read.getDesignator().obj.getName(), statement_read);
		}
		else if(!type.equals(Tab.intType) && !type.equals(Tab.charType) && !type.equals(boolType)) {
			report_error("Read operacija ne int/char/bool promenljive: " + statement_read.getDesignator().obj.getName(), statement_read);
		}
		
	}
	
	@Override
	public void visit(Statement_print1 statement_print1) {
		
		Struct type = statement_print1.getExpr().struct;
		if(isFindAny || isMap) {
			report_error("Nije dozvoljeno findAny i map u printu", statement_print1);
		}
		else {
			 if(!type.equals(Tab.intType) && !type.equals(Tab.charType) && !type.equals(boolType)) {
					report_error("Print operacija ne int/char/bool promenljive: ", statement_print1);
				}
		}
		isFindAny = false;
		isMap = false;
		
	}
	
	
	@Override 
	public void visit(Statement_print2 statement_print2) {
		
		Struct type = statement_print2.getExpr().struct;
		if(isFindAny || isMap) {
			report_error("Nije dozvoljeno findAny i map u printu", statement_print2);
		}
		else {
			 if(!type.equals(Tab.intType) && !type.equals(Tab.charType) && !type.equals(boolType)) {
					report_error("Print operacija ne int/char/bool promenljive: ", statement_print2);
				}
		}
		isFindAny = false;
		isMap = false;
		
	}
	
	@Override
	public void visit(Statement_return1 statement_return1) {
		
		returnHappend = true;
		if(currentMethod.getType() != Tab.noType) {
			report_error("Dogodio se nevalidan return iskaz unutar metode" + currentMethod.getName(), statement_return1);
		}
	
	}
	
	@Override
	public void visit(Statement_return2 statement_return2) {
		
		returnHappend = true;
		if(isFindAny || isMap) {
			report_error("Nije dozvoljeno findAny i map u returnu", statement_return2);
		}
		else {
			if(!currentMethod.getType().equals(statement_return2.getExpr().struct)) {
				report_error("Dogodio se nevalidan return iskaz unutar metode" + currentMethod.getName(), statement_return2);
			}
		}
		isFindAny = false;
		isMap = false;
		
	}
	
	
	@Override
	public void visit(WhileNonTerm whileNonTerm) {
		
		loopCnt++;
		
	}
	
	@Override
	public void visit(Statement_while statement_while) {
		
		loopCnt--;
		
	}
	
	@Override
	public void visit(ForNonTerm forNonTerm) {
		
		loopCnt++;
		
	}
	
	@Override
	public void visit(Statement_for statement_for) {
		
		loopCnt--;
		
	}
	
	@Override
	public void visit(Statement_foreach statement_foreach) {
		
		Obj d1 = statement_foreach.getForeachVar().getDesignator().obj;
		Obj arr =statement_foreach.getForeachArr().getDesignator().obj;
		
		if(d1 != Tab.noObj && arr != Tab.noObj && arr.getType().getKind() == Struct.Array) {
	        if(!arr.getType().getElemType().equals(d1.getType())) {
	            report_error("Tip promenljive ne odgovara tipu elementa niza u foreach", statement_foreach);
	        }
	    }
		loopCnt--;
		
	}
	
	@Override
	public void visit(ForeachVar foreachVar) {
		
		Obj d1 = foreachVar.getDesignator().obj;
		if( d1 != Tab.noObj && d1.getKind() != Obj.Var && d1.getKind() != Obj.Elem) {
			report_error("losa prom", foreachVar);
		}
		
	}
	
	@Override
	public void visit(ForeachArr foreachArr) {
		
		Obj arr = foreachArr.getDesignator().obj;
		if(arr != Tab.noObj && (arr.getKind() != Obj.Var || arr.getType().getKind() != Struct.Array)) {
			report_error("nije niz", foreachArr);
		}
		
	}
	
	@Override
	public void visit(ForeachBodyBegin foreachBodyBegin) {
		
		loopCnt++;
	
	}
	
	@Override
	public void visit(Statement_break statement_break) {
		
		if(loopCnt == 0) {
			report_error("Break naredba se ne nalazi unutar tela petlje", statement_break);
		}
		
	}
	
	@Override
	public void visit(Statement_continue statement_continue) {
		
		if(loopCnt == 0) {
			report_error("Continue naredba se ne nalazi unutar tela petlje", statement_continue);
		}
		
	}
	
	//condition
	
	@Override
	public void visit(CondFact_expr condFact_expr) {
		
		if(isFindAny || isMap) {
	        report_error("Nije dozvoljeno findAny i map u uslovu", condFact_expr);
	        condFact_expr.struct = Tab.noType;
	        isFindAny = false;
	        isMap = false;
	    }
		else if(!condFact_expr.getAddopTermList().struct.equals(boolType)) {
			report_error("Logicki operand nije tipa bool", condFact_expr);
			condFact_expr.struct = Tab.noType;
		}
		else {
			condFact_expr.struct = boolType;
		}
		
	}
	
	@Override
	public void visit(CondFact_relop condFact_relop) {
		
		if(isFindAny || isMap) {
		        report_error("Nije dozvoljeno findAny i map u uslovu", condFact_relop);
		        condFact_relop.struct = Tab.noType;
		        isFindAny = false;
		        isMap = false;
		        return;
		}
		Struct left = condFact_relop.getAddopTermList().struct;
		Struct right = condFact_relop.getAddopTermList1().struct;
		if(left.compatibleWith(right)) {
			if(left.isRefType() || right.isRefType()) {
				if(condFact_relop.getRelop() instanceof Relop_eq || condFact_relop.getRelop() instanceof Relop_ne) {
					condFact_relop.struct = boolType;
				}
				else {
					report_error("Poredjenje ref tipova sa neadekvatnim relacionim operatorom", condFact_relop);
					condFact_relop.struct = Tab.noType;
				}
			}
			else {
				condFact_relop.struct = boolType;
			}
			
		}
		else {
			report_error("Logicki operandi nisu kompatibilni", condFact_relop);
			condFact_relop.struct = Tab.noType;
			
		}
		
	}
	
	@Override
	public void visit(CondFactList_cf condFactList_cf) {
		
		condFactList_cf.struct = condFactList_cf.getCondFact().struct;
		
	}
	
	@Override
	public void visit(CondFactList_and condFactList_and) {
		
		Struct left = condFactList_and.getCondFactList().struct;
		Struct right = condFactList_and.getCondFact().struct;
		if(left.equals(boolType) && right.equals(boolType)) {
			condFactList_and.struct = boolType;
		}
		else {
			report_error("And operacija ne bool vrednosti", condFactList_and);
			condFactList_and.struct = Tab.noType;
			
		}
		
	}
	
	@Override
	public void visit(CondTerm condTerm) {
		
		condTerm.struct = condTerm.getCondFactList().struct;
		
	}
	

	@Override
	public void visit(ConditionTermList_ct conditionTermList_ct) {
		
		conditionTermList_ct.struct = conditionTermList_ct.getCondTerm().struct;
		
	}
	
	@Override
	public void visit(ConditionTermList_or conditionTermList_or) {
		
		Struct left = conditionTermList_or.getConditionTermList().struct;
		Struct right = conditionTermList_or.getCondTerm().struct;
		if(left.equals(boolType) && right.equals(boolType)) {
			conditionTermList_or.struct = boolType;
		}
		else {
			report_error("OR operacija ne bool vrednosti", conditionTermList_or);
			conditionTermList_or.struct = Tab.noType;
		}
		
	}
	
	@Override
	public void visit(Condition_cond condition_cond) {
		
		condition_cond.struct = condition_cond.getConditionTermList().struct;
		if(!condition_cond.struct.equals(boolType)) {
			report_error("Uslov nije tipa bool", condition_cond);
		}
		
	}
	
	@Override
	public void visit(HesName hesName) {
		
		Obj arrObj = Tab.find(hesName.getI1());
		logSearch(hesName.getLine(), hesName.getI1(), arrObj);
		if(arrObj == Tab.noObj) {
			report_error("Pristup nedefinisanoj promenljivi niza: " + hesName.getI1(), hesName);
			hesName.obj = Tab.noObj;
		}
		else if(arrObj.getKind() != Obj.Var || arrObj.getType().getKind() != Struct.Array) {
			report_error("Neadekvatna promenljiva niza: " + hesName.getI1(), hesName);
			hesName.obj = Tab.noObj;
		}
		else {
			hesName.obj = arrObj;
		}
		
	}
	
	@Override
	public void visit(FactorSub_hes factorSub_hes) {
		
		 if(factorSub_hes.getHesName().obj == Tab.noObj) {
		        factorSub_hes.struct = Tab.noType;
		    }
		 else {
			 factorSub_hes.struct = factorSub_hes.getHesName().obj.getType().getElemType();
		 }
		 
	}

}
