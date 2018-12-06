package org.ungs;

import org.ungs.clazz.Clazz;
import org.ungs.inheritanceTree.InheritanceNode;
import org.ungs.inheritanceTree.InheritanceTreeFactory;
import org.ungs.test.A;
import org.ungs.test.B;
import org.ungs.test.C;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        InheritanceTreeFactory factory = new InheritanceTreeFactory();

        Clazz Aclass = new Clazz(A.class);

        InheritanceNode node = factory.getTreeOf(Aclass);

        for (InheritanceNode n :
                node) {
            System.out.println(n.getContent().getName());
        }


    }
}
