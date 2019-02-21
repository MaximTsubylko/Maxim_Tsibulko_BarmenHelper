package com.tsibulko.finaltask.dao;

import com.tsibulko.finaltask.dao.exception.DaoException;
import com.tsibulko.finaltask.dao.exception.PersistException;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public abstract class AbstractJdbcDao<T extends Identified<PK>, PK extends Number> implements GenericDAO<T, PK> {
    protected Connection connection;

    protected abstract List<T> parseResultSet(ResultSet rs) throws SQLException;

    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws SQLException;

    protected abstract void prepareStatementForDelete(PreparedStatement statement, T object) throws SQLException;

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws SQLException;

    protected abstract T prepareStatementForGet(PreparedStatement statement) throws SQLException;

    protected abstract List<T> prepareStatementForGetAll(PreparedStatement statement) throws SQLException;

    public abstract String getSelectQuery();

    public abstract String getSelectAllQuery();

    public abstract String getPersistQuery();

    public abstract String getUpdateQuery();

    public abstract String getDeleteQuery();


    @Override
    @AutoConnection
    public Optional<T> getByPK(PK key) throws DaoException, SQLException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(getSelectQuery() + " WHERE id = " + key)) {
            return Optional.of(parseResultSet(preparedStatement.executeQuery()).get(0));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    @AutoConnection
    public List<T> getAll() throws DaoException, SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(getSelectQuery())) {
            return parseResultSet(preparedStatement.executeQuery());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    @AutoConnection
    public T persist(T object) throws PersistException, SQLException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(getPersistQuery(), Statement.RETURN_GENERATED_KEYS)) {
            prepareStatementForInsert(preparedStatement, object);
            preparedStatement.execute();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    object.setId(generatedKeys.getInt(1));
                    return object;
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }

    @Override
    @AutoConnection
    public void update(T object) throws PersistException, SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(getUpdateQuery())) {
            prepareStatementForInsert(preparedStatement, object);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }

    @Override
    @AutoConnection
    public void delete(T object) throws PersistException, SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery())) {
            preparedStatement.setInt(1, (Integer) object.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }
}
