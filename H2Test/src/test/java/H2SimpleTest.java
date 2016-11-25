import dao.UserDao;
import dao.UserDaoImpl;
import model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * Created by Gleb_Yants on 26.11.2016.
 */
public class H2SimpleTest {

        private EmbeddedDatabase db;

        @Before
        public void setUp() {
            db = new EmbeddedDatabaseBuilder()
                    .setType(EmbeddedDatabaseType.H2)
                    .addScript("db.sql/create-db.sql")
                    .addScript("db.sql/insert-data.sql")
                    .build();
        }

        @Test
        public void testFindByname() {
            NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(db);
            UserDaoImpl userDao = new UserDaoImpl();
            userDao.setNamedParameterJdbcTemplate(template);

            User user = userDao.findByName("epam");

            Assert.assertNotNull(user);
            Assert.assertEquals(1, user.getId().intValue());
            Assert.assertEquals("epam", user.getName());
            Assert.assertEquals("epam@gmail.com", user.getEmail());

        }

        @After
        public void tearDown() {
            db.shutdown();
        }

    }

