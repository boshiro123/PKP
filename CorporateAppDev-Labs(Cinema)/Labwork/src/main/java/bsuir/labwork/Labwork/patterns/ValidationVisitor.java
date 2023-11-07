package bsuir.labwork.Labwork.patterns;


import bsuir.labwork.Labwork.entity.Cinema;
import bsuir.labwork.Labwork.interfaces.Visitor;

public class ValidationVisitor implements Visitor {
    @Override
    public void visit(Cinema cinema) {
        try {
            cinema.validate();
            System.out.println("Valid cinema: " + cinema);
        } catch (Exception e) {
            System.out.println("Invalid cinema detected: " + cinema + " Reason: " + e.getMessage());
        }
    }
}
