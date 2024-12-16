package sample.dao;

import lombok.SneakyThrows;
import sample.dao.Interface.EmployDaoImpl;
import sample.entity.EmployEntity;
import sample.exception.DaoException;
import sample.util.ConnectionManager;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


public class EmployDao implements EmployDaoImpl {
    private static EmployDao instance;
    private static final String FIELD_ID = "id";
    private static final String FIELD_FIRST_NAME = "first_name";
    private static final String FIELD_LAST_NAME = "last_name";
    private static final String FIELD_BIRTH_DATE = "birthdate";
    private static final String FIELD_DATE_REGISTRATION = "date_registration";
    private static final String FIELD_EMAIL = "email";
    private static final String FIELD_PASSWORD = "password";
    private static final String FIELD_NUMBER = "number";
    private static final String FIELD_ACTIVITY_ID = "activity_id";
    private static final String FIELD_ACCESS_PERMISSION_ID = "access_permission_id";

    private static final String DELETE_SQL = """
            DELETE FROM employ
            WHERE id = ?""";

    private static final String SAVE_SQL = """
            INSERT INTO employ (first_name, last_name, birthdate, email, password, number, activity_id, access_permission_id)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;

    private static final String UPDATE_SQL = """
            UPDATE employ
            SET first_name = ?, last_name = ?, birthdate = ?, email = ?, password = ?, number = ?, activity_id = ?, access_permission_id = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT id,id, first_name, last_name, birthdate, email, password, number, activity_id, access_permission_id
            FROM employ
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?""";

    private static final String FIND_TYPE_PERMISSION_SQL = """
             SELECT access_permission.permission
            from access_permission
            join employ on access_permission.id = employ.access_permission_id
            where employ.id = ?
            """;
    private static final String FIND_ACTIVITY_SQL = """
            select activity
            from current_activity
            join employ on current_activity.id = employ.activity_id
            where employ.id = ?
            """;
    private static final String FIND_EMPLOY_BY_NUMBER_AND_PASSWORD = """
            select *
            from employ
            where (number = ?) and (password = ?)
            """;
    private static final String FIND_EMPLOY_BY_NUMBER = """
            select *
            from employ
            where (number = ?)
            """;


    public static synchronized EmployDao getInstance() {
        if (instance == null) {
            instance = new EmployDao();
        }
        return instance;
    }

    @Override
    public Optional<EmployEntity> getEmployByNumberAndPassword(String number, String password) {
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(FIND_EMPLOY_BY_NUMBER_AND_PASSWORD)) {
            preparedStatement.setString(1, number);
            preparedStatement.setString(2, password);

            var resultSet = preparedStatement.executeQuery();
            EmployEntity employEntity = null;
            if (resultSet.next()) {
                employEntity = buildEmploy(resultSet);
            }
            return Optional.ofNullable(employEntity);
        } catch (SQLException e) {
            throw new DaoException("Error in getEmployByNumberAndPassword", e);
        }
    }

    @Override
    public Optional<EmployEntity> checkEmptyNumber(String number) {
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(FIND_EMPLOY_BY_NUMBER)) {
            preparedStatement.setString(1, number);

            var resultSet = preparedStatement.executeQuery();
            EmployEntity employEntity = null;
            if (resultSet.next()) {
                employEntity = buildEmploy(resultSet);
            }
            return Optional.ofNullable(employEntity);
        } catch (SQLException e) {
            throw new DaoException("Error in getEmployByNumberAndPassword", e);
        }
    }

    @Override
    public List<EmployEntity> findAll() {
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<EmployEntity> EmployList = new ArrayList<>();
            while (resultSet.next()) {
                EmployList.add(buildEmploy(resultSet));
            }
            return EmployList;

        } catch (SQLException e) {
            throw new DaoException("Error in findAll method", e);
        }
    }


    @Override
    public String getTypeActivity(Integer employId) {
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(FIND_ACTIVITY_SQL)) {
            preparedStatement.setInt(1, employId);
            var resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new NoSuchElementException("No activity found for employee ID: " + employId);
            }

            return resultSet.getString("activity");


        } catch (SQLException e) {
            throw new DaoException("Error in getTypeActivity method", e);
        }
    }

    @Override
    public String getTypePermission(Integer employId) {
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(FIND_TYPE_PERMISSION_SQL)) {
            preparedStatement.setLong(1, employId);
            var resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new NoSuchElementException("No permission found for employee ID: " + employId);
            }
            return resultSet.getString("permission");


        } catch (SQLException e) {
            throw new DaoException("Error in getTypePermission method", e);
        }
    }

    @Override
    public Optional<EmployEntity> findById(Integer id) {
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);

            var resultSet = preparedStatement.executeQuery();
            EmployEntity employEntity = null;
            if (resultSet.next()) {
                employEntity = buildEmploy(resultSet);

            }
            return Optional.ofNullable(employEntity);

        } catch (SQLException e) {
            throw new DaoException("Error in findById method", e);
        }
    }

    @Override
    public boolean save(EmployEntity entity) {
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setObject(3, entity.getBirthDate());
            preparedStatement.setString(4, entity.getEmail());
            preparedStatement.setString(5, entity.getPassword());
            preparedStatement.setString(6, entity.getNumber());
            preparedStatement.setInt(7, entity.getActivityId());
            preparedStatement.setInt(8, entity.getAccessPermissionId());
            preparedStatement.executeUpdate();

            var resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                entity.setId(resultSet.getInt("id"));
                return true;
            }
            return false;

        } catch (SQLException e) {
            throw new DaoException("Error in save method", e);
        }
    }

    @Override
    public boolean update(EmployEntity entity) {
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setObject(3, entity.getBirthDate());
            preparedStatement.setString(4, entity.getEmail());
            preparedStatement.setString(5, entity.getPassword());
            preparedStatement.setString(6, entity.getNumber());
            preparedStatement.setInt(7, entity.getActivityId());
            preparedStatement.setInt(8, entity.getAccessPermissionId());
            preparedStatement.executeUpdate();
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error in update method", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error in delete method", e);
        }
    }

    public static EmployEntity buildEmploy(ResultSet resultSet) throws SQLException {
        java.sql.Date sqlDate = resultSet.getDate(FIELD_BIRTH_DATE);
        LocalDate birthDate = (sqlDate != null) ? sqlDate.toLocalDate() : null;
        return new EmployEntity(
                resultSet.getInt(FIELD_ID),
                resultSet.getString(FIELD_FIRST_NAME),
                resultSet.getString(FIELD_LAST_NAME),
                birthDate,
                resultSet.getString(FIELD_EMAIL),
                resultSet.getString(FIELD_PASSWORD),
                resultSet.getString(FIELD_NUMBER),
                resultSet.getInt(FIELD_ACTIVITY_ID),
                resultSet.getInt(FIELD_ACCESS_PERMISSION_ID)
        );
    }

}
