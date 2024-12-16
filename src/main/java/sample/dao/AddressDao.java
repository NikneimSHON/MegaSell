package sample.dao;

import lombok.Builder;
import sample.dao.Interface.AddressDaoImpl;
import sample.entity.EmployEntity;
import sample.exception.DaoException;
import sample.util.ConnectionManager;
import sample.entity.AddressEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Builder

public class AddressDao implements AddressDaoImpl {

    private static AddressDao instance;
    private static final String FIELD_ID = "id";
    private static final String FIELD_STREET = "street";
    private static final String FIELD_ID_EMPLOYEE = "id_employs";
    private static final String FIELD_CITY = "city";
    private static final String FIELD_HOUSE_NUMBER = "house_number";
    private static final String FIELD_APARTMENT_NUMBER = "apartment_number";
    private static final String DELETE_SQL = """
            DELETE FROM address
            WHERE id = ?""";

    private static final String SAVE_SQL = """
            insert into address (street,id_employs,city,house_number,apartment_number)
            values (?,?,?,?,?)
            """;
    private static final String UPDATE_SQL = """
            Update address
            set street = ?,id_employs = ?,city = ?,house_number = ?,apartment_number = ?
            where id = ?
            """;
    private static final String FIND_ALL_SQL = """
            select id,street,id_employs,city,house_number,apartment_number
            from address
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            where id = ? 
            """;
    private static final String FIND_ADDRESS_BY_EMPLOY = """
            SELECT ad.id, ad.street, ad.id_employs, ad.city, ad.house_number, ad.apartment_number
            FROM address ad
            JOIN employ ON ad.id_employs = employ.id
            WHERE employ.id = ?
            """;


    public static synchronized AddressDao getInstance() {
        if (instance == null) {
            instance = new AddressDao();
        }
        return instance;
    }


    @Override
    public Optional<AddressEntity> findById(Integer id) { // БАГ
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);

            var resultSet = preparedStatement.executeQuery();
            AddressEntity addressEntity = null;
            if (resultSet.next()) {
                addressEntity = buildAddress(resultSet);

            }
            return Optional.ofNullable(addressEntity);

        } catch (SQLException e) {
            throw new DaoException("Error in findById method", e);
        }
    }


    @Override
    public boolean save(AddressEntity entity) {
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getStreet());
            preparedStatement.setInt(2, entity.getIdEmploy());
            preparedStatement.setString(3, entity.getCity());
            preparedStatement.setInt(4, entity.getHouseNumber());
            preparedStatement.setInt(5, entity.getApartmentHumber());

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
    public boolean update(AddressEntity entity) {
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, entity.getStreet());
            preparedStatement.setInt(2, entity.getIdEmploy());
            preparedStatement.setString(3, entity.getCity());
            preparedStatement.setInt(4, entity.getHouseNumber());
            preparedStatement.setInt(5, entity.getApartmentHumber());
            preparedStatement.setInt(6, entity.getId());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error in update method", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error in delete method", e);
        }
    }

    public static AddressEntity buildAddress(ResultSet resultSet) throws SQLException {
        return new AddressEntity(
                resultSet.getInt(FIELD_ID),
                resultSet.getString(FIELD_STREET),
                resultSet.getInt(FIELD_ID_EMPLOYEE),
                resultSet.getString(FIELD_CITY),
                resultSet.getInt(FIELD_HOUSE_NUMBER),
                resultSet.getInt(FIELD_APARTMENT_NUMBER)
        );
    }

    @Override
    public List<AddressEntity> findAllByEmployId(Integer employId) {
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(FIND_ADDRESS_BY_EMPLOY)) {
            preparedStatement.setInt(1, employId);
            var resultSet = preparedStatement.executeQuery();
            List<AddressEntity> addressList = new ArrayList<>();
            while (resultSet.next()) {
                addressList.add(buildAddress(resultSet));
            }
            return addressList;

        } catch (SQLException e) {
            throw new DaoException("Error in findEmployByAddress method", e);
        }
    }

    @Override
    public List<AddressEntity> findAll() {
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<AddressEntity> EmployList = new ArrayList<>();
            while (resultSet.next()) {
                EmployList.add(buildAddress(resultSet));
            }
            return EmployList;

        } catch (SQLException e) {
            throw new DaoException("Error in findAll method", e);
        }
    }
}
