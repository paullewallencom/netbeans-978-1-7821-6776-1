/*
 * Copyright 2014 David Salter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.davidsalter.cookbook.codegenerator;

import com.*;
import com.sun.source.tree.AnnotationTree;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.ModifiersTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.TypeParameterTree;
import com.sun.source.tree.VariableTree;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeKind;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.api.java.source.CancellableTask;
import org.netbeans.api.java.source.JavaSource;
import org.netbeans.api.java.source.JavaSource.Phase;
import org.netbeans.api.java.source.ModificationResult;
import org.netbeans.api.java.source.TreeMaker;
import org.netbeans.api.java.source.WorkingCopy;
import org.netbeans.spi.editor.codegen.CodeGenerator;
import org.openide.util.*;
import org.openide.util.Lookup;

/**
 * @author david@developinjava.com
 */
public class AddUnitTest implements CodeGenerator {

  JTextComponent textComp;

  /**
   *
   * @param context containing JTextComponent and possibly other items
   * registered by {@link CodeGeneratorContextProvider}
   */
  private AddUnitTest(Lookup context) { // Good practice is not to save Lookup outside ctor
    textComp = context.lookup(JTextComponent.class);
  }

  @MimeRegistration(mimeType = "text/x-java", service = CodeGenerator.Factory.class)
  public static class Factory implements CodeGenerator.Factory {
    public List<? extends CodeGenerator> create(Lookup context) {
      return Collections.singletonList(new AddUnitTest(context));
    }
  }

  /**
   * The name which will be inserted inside Insert Code dialog
   */
  public String getDisplayName() {
    return "JUnit Test...";
  }

  /**
   * This will be invoked when user chooses this Generator from Insert Code
   * dialog
   */
  public void invoke() {
    try {
      CancellableTask task = new CancellableTask<WorkingCopy>() {

        @Override
        public void cancel() {
        }

        @Override
        public void run(WorkingCopy workingCopy) throws Exception {
          workingCopy.toPhase(Phase.RESOLVED);
          CompilationUnitTree compilationUnitTree = workingCopy.getCompilationUnit();
          TreeMaker treeMaker = workingCopy.getTreeMaker();
          for (Tree typeDecl : compilationUnitTree.getTypeDecls()) {
            if (Tree.Kind.CLASS == typeDecl.getKind()) {
              ClassTree clazz = (ClassTree) typeDecl;
              ModifiersTree methodModifiers
                      = treeMaker.Modifiers(Collections.<Modifier>singleton(Modifier.PUBLIC),
                              Arrays.asList(treeMaker.Annotation(treeMaker.Identifier("Test"), Collections.EMPTY_LIST)));
              String methodName = "testAbc";
              String methodBody = "{ fail(\"Test not written yet\"); }";

              TypeElement typeElement = workingCopy.getElements().getTypeElement("java.lang.Exception");
              ExpressionTree throwsClause = treeMaker.QualIdent(typeElement);
              MethodTree newMethod
                      = treeMaker.Method(methodModifiers,
                              methodName,
                              treeMaker.PrimitiveType(TypeKind.VOID),
                              Collections.<TypeParameterTree>emptyList(),
                              Collections.EMPTY_LIST,
                              Collections.<ExpressionTree>singletonList(throwsClause),
                              methodBody,
                              null);
              ClassTree modifiedClazz = treeMaker.addClassMember(clazz, newMethod);
              workingCopy.rewrite(clazz, modifiedClazz);
            }
          }
        }

      };
      Document doc = textComp.getDocument();
      JavaSource javaSource = JavaSource.forDocument(doc);
      ModificationResult result = javaSource.runModificationTask(task);
      result.commit();
    } catch (Exception e) {
      Exceptions.printStackTrace(e);
    }
  }

}
