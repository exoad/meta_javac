package com.jackmeng.compiler.internal;

import com.sun.source.util.TreeScanner;
import java.util.StringJoiner;

public class csun_MethodBodyVisitor
    extends TreeScanner< Void, StringBuilder >
{
  @Override
  public Void visitMethod(com.sun.source.tree.MethodTree node, StringBuilder modifiedMethod) {
    super.visitMethod(node, modifiedMethod);
    StringJoiner sj = new StringJoiner(System.lineSeparator());
    node.getBody().getStatements().forEach(stmt -> sj.add(stmt.toString()));
    modifiedMethod.append(sj.toString());
    return null;
  }
}
