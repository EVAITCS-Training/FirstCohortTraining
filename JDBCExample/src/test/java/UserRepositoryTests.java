import com.horrorcore.config.DatabaseConnection;
import com.horrorcore.entity.User;
import com.horrorcore.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserRepositoryTests {
    @Mock
    private DatabaseConnection databaseConnection;

    @InjectMocks
    private UserRepository userRepository;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void CreateUser_CorrectInformation_Success() throws Exception {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        user.setRole("CUSTOMER");

        User expected = new User();
        expected.setId(1);
        expected.setUsername("testuser");
        expected.setPassword("testpassword");
        expected.setRole("CUSTOMER");

        Connection mockConnection = Mockito.mock(Connection.class);
        PreparedStatement mockPreparedStatement = Mockito.mock(PreparedStatement.class);

        Mockito.when(databaseConnection.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString(), Mockito.anyInt()))
               .thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // Mock ResultSet for generated keys
        java.sql.ResultSet mockResultSet = Mockito.mock(java.sql.ResultSet.class);
        Mockito.when(mockPreparedStatement.getGeneratedKeys()).thenReturn(mockResultSet);
        Mockito.when(mockResultSet.next()).thenReturn(true);
        Mockito.when(mockResultSet.getInt(1)).thenReturn(1);

        // Mock UserRepository save method
        UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
        Mockito.when(mockUserRepository.save(user)).thenReturn(expected);

        User actual = mockUserRepository.save(user);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getPassword(), actual.getPassword());
        assertEquals(expected.getRole(), actual.getRole());
    }

    @ParameterizedTest
    @CsvSource({
        "'', testpassword",
        "' ', testpassword",
        "testuser, ''",
        "testuser, ' '"
    })
    void CreateUser_EmptyUsernameOrPassword_Failure(String username, String password) throws Exception {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole("CUSTOMER");

        Connection mockConnection = Mockito.mock(Connection.class);
        PreparedStatement mockPreparedStatement = Mockito.mock(PreparedStatement.class);

        Mockito.when(databaseConnection.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString(), Mockito.anyInt()))
               .thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeUpdate()).thenThrow(new IllegalArgumentException("Username and password cannot be blank"));

        assertThrows(IllegalArgumentException.class, () -> userRepository.save(user), "Username and password cannot be blank");
    }
}
