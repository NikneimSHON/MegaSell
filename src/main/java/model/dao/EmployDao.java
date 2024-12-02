package model.dao;

import model.dao.Interface.EmployDaoImpl;
import model.entity.AddressEntity;
import model.entity.EmployEntity;
import model.entity.dto.TypePermissionDTO;
import model.entity.dto.TypeActivityDTO;
import model.exception.DaoException;
import util.ConnectionManager;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            INSERT INTO employ (first_name, last_name, birthdate, date_registration, email, password, number, activity_id, access_permission_id)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

    private static final String UPDATE_SQL = """
            UPDATE employ
            SET first_name = ?, last_name = ?, birthdate = ?, email = ?, password = ?, number = ?, activity_id = ?, access_permission_id = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT id,id, first_name, last_name, birthdate, date_registration, email, password, number, activity_id, access_permission_id
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


    public static synchronized EmployDao getInstance() {
        if (instance == null) {
            instance = new EmployDao();
        }
        return instance;
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
    public TypeActivityDTO getTypeActivity(Long employId) {
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(FIND_ACTIVITY_SQL)) {
            preparedStatement.setLong(1, employId);
            var resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new NoSuchElementException("No activity found for employee ID: " + employId);
            }

            String activity = resultSet.getString("activity");

            if ("Allowed".equals(activity)) {
                return TypeActivityDTO.ALLOWED;
            } else {
                return TypeActivityDTO.BLOCKED;
            }


        } catch (SQLException e) {
            throw new DaoException("Error in getTypeActivity method", e);
        }
    }

    @Override
    public TypePermissionDTO getTypePermission(Long employId) {
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(FIND_TYPE_PERMISSION_SQL)) {
            preparedStatement.setLong(1, employId);
            var resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new NoSuchElementException("No permission found for employee ID: " + employId);
            }
            String permission = resultSet.getString("permission");


            if ("Admin".equals(permission)) {
                return TypePermissionDTO.ADMIN;
            } else {
                return TypePermissionDTO.USER;
            }

        } catch (SQLException e) {
            throw new DaoException("Error in getTypePermission method", e);
        }
    }

    @Override
    public Optional<EmployEntity> findById(Long id) {
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);

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
    public EmployEntity save(EmployEntity entity) {
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setDate(3, (Date) entity.getBirthDate());
            preparedStatement.setDate(4, (Date) entity.getDateRegistration());
            preparedStatement.setString(5, entity.getEmail());
            preparedStatement.setString(6, entity.getPassword());
            preparedStatement.setString(7, entity.getNumber());
            preparedStatement.setLong(8, entity.getActivityId());
            preparedStatement.setLong(9, entity.getAccessPermissionId());
            preparedStatement.executeUpdate();

            var resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                entity.setId(resultSet.getLong("id"));
            }
            return entity;

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
            preparedStatement.setDate(3, (Date) entity.getBirthDate());
            preparedStatement.setDate(4, (Date) entity.getDateRegistration());
            preparedStatement.setString(5, entity.getEmail());
            preparedStatement.setString(6, entity.getPassword());
            preparedStatement.setString(7, entity.getNumber());
            preparedStatement.setLong(8, entity.getActivityId());
            preparedStatement.setLong(9, entity.getAccessPermissionId());
            preparedStatement.executeUpdate();
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error in update method", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error in delete method", e);
        }
    }

    public static EmployEntity buildEmploy(ResultSet resultSet) throws SQLException {
        return new EmployEntity(
                resultSet.getLong(FIELD_ID),
                resultSet.getString(FIELD_FIRST_NAME),
                resultSet.getString(FIELD_LAST_NAME),
                resultSet.getDate(FIELD_BIRTH_DATE),
                resultSet.getDate(FIELD_DATE_REGISTRATION),
                resultSet.getString(FIELD_EMAIL),
                resultSet.getString(FIELD_PASSWORD),
                resultSet.getString(FIELD_NUMBER),
                resultSet.getLong(FIELD_ACTIVITY_ID),
                resultSet.getLong(FIELD_ACCESS_PERMISSION_ID)
        );

    }
}
