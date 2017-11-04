public class Mur extends Forme {

    private double coefficient;

    public Mur(int x, int y, int hauteur, int largeur) {
        super(x, y, hauteur, largeur, 0);
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    void dessine() {
        // TODO
        //System.out.printf("Mur(%d, %d)[%d, %d]%n", x, y, hauteur, largeur);
    }

    @Override
    public String toString() {
        return String.format("Mur(%d, %d)[%d, %d]", x, y, hauteur, largeur);
    }
}
