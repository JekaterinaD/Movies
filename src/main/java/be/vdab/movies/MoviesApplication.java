package be.vdab.movies;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Scanner;

@SpringBootApplication
public class MoviesApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MoviesApplication.class, args);
    }

    @Override
    public void run(String... args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean isAuthenticated = false;

            while (!isAuthenticated) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                if ("cursist".equals(username) && "cursist".equals(password)) {
                    System.out.println("Login successful!");
                    isAuthenticated = true;
                } else {
                    System.out.println("Login failed. Please try again.");
                }
            }
        }
    }
}
