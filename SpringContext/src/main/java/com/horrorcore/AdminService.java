package com.horrorcore;

/**
 * The AdminService class provides administrative functionality for the application.
 * <p>
 * This class demonstrates constructor-based dependency injection in Spring. While it doesn't
 * use the @Service annotation like UserService, it's still registered as a bean in the
 * application context through XML configuration (ApplicationContext.xml).
 * <p>
 * Educational notes:
 * - This class shows how Spring manages dependencies between components
 * - The UserRepository is injected through constructor injection
 * - Spring creates a singleton instance of this service by default
 * - The same UserRepository instance is shared between services, demonstrating bean sharing
 */
public class AdminService {
    /**
     * Reference to the UserRepository bean.
     * This dependency is injected through constructor injection.
     */
    private UserRepository userRepository;

    /**
     * Constructs a new AdminService with the specified UserRepository.
     * <p>
     * This constructor enables Spring to inject the UserRepository dependency
     * when creating this service. In ApplicationContext.xml, the constructor-arg
     * element specifies which bean to inject into this parameter.
     *
     * @param userRepository The repository that manages user data and tracking
     */
    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Prints a greeting message and increments the user count in the repository.
     * <p>
     * This method demonstrates how the injected dependency is used within the service.
     * Each time this method is called, it increases the counter in the UserRepository
     * that is shared across the application.
     */
    public void hello() {
        userRepository.incrementCount();
        System.out.println("Hello from AdminService!");
    }
}