package com.tsibulko.finaltask.validation.impl;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.dao.exception.ConnectionPoolException;
import com.tsibulko.finaltask.dao.exception.DaoException;
import com.tsibulko.finaltask.util.DBUtil.InMemoryDBUtil;
import com.tsibulko.finaltask.validation.ServiceValid;
import com.tsibulko.finaltask.validation.ValidatorFactory;
import com.tsibulko.finaltask.validation.ValidatorType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ServiceDateValidatorTest {
    ValidatorFactory validatorFactory = ValidatorFactory.getInstance();
    ServiceValid validator;

    @BeforeEach
    void setUp() throws InterruptedException, SQLException, ConnectionPoolException, DaoException, IOException {
        InMemoryDBUtil.fill();
        validator = (ServiceValid) validatorFactory.getValidator(ValidatorType.SERVICE);
    }

    @AfterEach
    void tearDown() throws InterruptedException, SQLException, ConnectionPoolException, IOException {
        InMemoryDBUtil.drop();
    }

    @Test
    void isUniqueCocktil() throws SQLException, DaoException {
        Cocktail cocktaile = new Cocktail();
        cocktaile.setName("test name");
        assertTrue(validator.isUniqueCocktil(cocktaile));
    }

    @Test
    void isExistCoctil() throws SQLException, DaoException {
        Cocktail cocktaile = new Cocktail();
        cocktaile.setId(1);
        cocktaile.setName("Blood Mary");
        cocktaile.setDescription("Blood Mary description");
        cocktaile.setPrice(1000);
        assertTrue(validator.isExistCoctil(cocktaile));
    }

    @Test
    void isUniqueCustomer() throws SQLException, DaoException {
        Customer customer = new Customer();
        customer.setLogin("test");
        assertTrue(validator.isUniqueCustomer(customer));
    }

    @Test
    void isExistCustomer() throws SQLException, DaoException {
        Customer customer = new Customer();
        customer.setLogin("root");
        assertTrue(validator.isExistCustomer(customer));
    }
}