package edu.colorado.thresher.core;

import com.ibm.wala.ipa.callgraph.propagation.MultiNewArrayInNode;

public abstract class AbstractConstraint implements Constraint {

  @Override
  public boolean isClinitConstraint() {
    /*
    for (PointerVariable var : this.getVars()) {
      if (var.isClinitVar()) {
        return true;
      }
    }
    return false;
    */
    // a constraint is a clinit constraint if *all* of it's vars are clinit vars
    for (PointerVariable var : this.getVars()) {
      if (!var.isClinitVar()) {
        return false;
      }
    }
    return true;
  }
  
  @Override
  public boolean isMultiDimArrayConstraint() { 
    for (PointerVariable var : this.getVars()) {
      Object key = var.getInstanceKey();
      if (key != null && key instanceof MultiNewArrayInNode) return true;
    }
    return false;
  }

}
