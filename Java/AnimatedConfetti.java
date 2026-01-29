package games;
import java.util.Random;

public class AnimatedConfetti {

    static final int WIDTH = 60;
    static final int HEIGHT = 20;
    static final int PARTICLES = 80;

    static final char[] SHAPES = {'*', '@', '#', '$', '%', '&'};
    static final String[] COLORS = {
        "\u001B[31m", // red
        "\u001B[32m", // green
        "\u001B[33m", // yellow
        "\u001B[34m", // blue
        "\u001B[35m", // purple
        "\u001B[36m"  // cyan
    };
    static final String RESET = "\u001B[0m";

    static class Particle {
        int x, y;
        char shape;
        String color;
    }

    public static void main(String[] args) throws Exception {
        Random rand = new Random();
        Particle[] particles = new Particle[PARTICLES];

        for (int i = 0; i < PARTICLES; i++) {
            particles[i] = new Particle();
            resetParticle(particles[i], rand);
        }

        while (true) {
            clearScreen();

            char[][] screen = new char[HEIGHT][WIDTH];
            for (Particle p : particles) {
                if (p.y >= 0 && p.y < HEIGHT && p.x >= 0 && p.x < WIDTH) {
                    screen[p.y][p.x] = p.shape;
                }
            }

            for (int y = 0; y < HEIGHT; y++) {
                for (int x = 0; x < WIDTH; x++) {
                    char c = screen[y][x];
                    if (c == 0) {
                        System.out.print(" ");
                    } else {
                        for (Particle p : particles) {
                            if (p.x == x && p.y == y) {
                                System.out.print(p.color + c + RESET);
                                break;
                            }
                        }
                    }
                }
                System.out.println();
            }

            for (Particle p : particles) {
                p.y++;
                if (p.y >= HEIGHT) {
                    resetParticle(p, rand);
                }
            }

            Thread.sleep(100);
        }
    }

    static void resetParticle(Particle p, Random rand) {
        p.x = rand.nextInt(WIDTH);
        p.y = rand.nextInt(HEIGHT) - HEIGHT;
        p.shape = SHAPES[rand.nextInt(SHAPES.length)];
        p.color = COLORS[rand.nextInt(COLORS.length)];
    }

    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
