package demo.simple.library;

import demo.simple.library.repository.AuthorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

//    @Bean
//    CommandLineRunner testConnection(DataSource dataSource) {
//        return args -> {
//            try (Connection conn = dataSource.getConnection();
//                 Statement stmt = conn.createStatement();
//                 ResultSet rs = stmt.executeQuery("SELECT TOP 5 * FROM author")) {
//
//                System.out.println("✅ Konekcija uspešna preko Spring Boot-a!");
//
//                while (rs.next()) {
//                    System.out.println(
//                            rs.getInt("id") + " " +
//                                    rs.getString("name") + " " +
//                                    rs.getString("surname")
//                    );
//                }
//
//            } catch (Exception e) {
//                System.out.println("❌ Greška pri konekciji: " + e.getMessage());
//                e.printStackTrace();
//            }
//        };
//    }
    @Bean
    public CommandLineRunner testRepo(AuthorRepository autorRepository) {
        return args -> {
            System.out.println("=== Lista autora iz baze ===");
            autorRepository.findAll().forEach(autor -> {
                System.out.println(autor.getId() + " " + autor.getFirstName() + " " + autor.getLastName());
            });
        };
    }
}
