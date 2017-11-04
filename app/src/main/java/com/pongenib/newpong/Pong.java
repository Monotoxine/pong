import java.util.ArrayList;
import java.util.List;

public class Pong extends Thread {
    int width;
    int height;
    private final List<Forme> formes = new ArrayList<>();
    private boolean stop = false;

    public Pong(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void run() {
        execute();
    }

    synchronized void execute() {
        while (!stop) {
            for (Forme forme : this.formes) {
                if (forme instanceof Mobile) {
                    Mobile mobile = (Mobile) forme;
                    if (mobile.anime()) {
                        mobile.deplace();
                        forme.dessine();
                    }
                }
            }
            try {
                wait(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    void collision(Mobile mobile) {
        int x = mobile.getX();
        int y = mobile.getY();
        int largeur = mobile.getLargeur();
        int hauteur = mobile.getHauteur();
        double orientation = mobile.getOrientation();
        if (x <= 0 || x >= width - largeur) {
            mobile.setOrientation(Math.PI - orientation);
        }
        if (y <= 0 || y >= height - hauteur) {
            mobile.setOrientation(-orientation);
        }
        verifierCollision(mobile);
    }

    private void verifierCollision(Mobile mobile) {
        double orientation = mobile.getOrientation();
        int vitesse = mobile.getVitesse();
        for (Forme forme : formes) {
            if (forme.equals(mobile)) {
                continue;
            }
            int direction = intersect(mobile, forme);
            switch (direction) {
                case 1:
                case 2:
                    System.err.printf("Collision entre %d: %s et %s%n", direction, mobile, forme);
                    if (forme instanceof Mur) {
                        mobile.setVitesse((int) Math.floor(((Mur) forme).getCoefficient() * vitesse));
                    }
                    mobile.setOrientation(-orientation);
                case 3:
                case 4:
                    if (forme instanceof Mur) {
                        mobile.setVitesse((int) Math.floor(((Mur) forme).getCoefficient() * vitesse));
                    }
                    System.err.printf("Collision entre %d: %s et %s%n", direction, mobile, forme);
                    mobile.setOrientation(Math.PI - orientation);
                default:

            }
        }
    }

    private int intersect(Forme formeOne, Forme formeTwo) {
        if (formeOne.getY() + formeOne.getHauteur() >= formeTwo.getY()
                && formeOne.getY() <= formeTwo.getY() + formeTwo.getHauteur())
            return formeOne.getY() <= formeTwo.getY() ? 1 : 2;

        if (formeOne.getX() + formeOne.getLargeur() >= formeTwo.getX()
                && formeOne.getX() <= formeTwo.getX() + formeTwo.getLargeur())
            return formeOne.getX() <= formeTwo.getX() ? 3 : 4;
        return 0;
    }

    public void add(Forme forme) {
        this.formes.add(forme);
    }

    public void remove(Forme forme) {
        this.formes.remove(forme);
    }
}
