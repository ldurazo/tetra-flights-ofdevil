package com.dao;

import com.models.Airport;
import com.utils.FlightsAppObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class AirportDao {
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private FlightsAppObjectMapper objectMapper;

    @Autowired
    public void setDataSource(DataSource dataSource, FlightsAppObjectMapper objectMapper) {
        this.dataSource = dataSource;
        this.objectMapper = objectMapper;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public List<Airport> searchAirports(final String userInput){
        final String airportsQuery = " SELECT * FROM AIRPORTS WHERE city ilike ? limit 10";
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(airportsQuery);
                preparedStatement.setString(1, "%"+userInput+"%");
                return preparedStatement;
            }
        };
        return jdbcTemplate.query(preparedStatementCreator, new AirportMapper());
    }

    public class AirportMapper implements RowMapper<Airport> {
        @Override
        public Airport mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            Airport airport = new Airport();
            airport.setId(resultSet.getInt(Airport.ID_COLUMN));
            airport.setIata_code(resultSet.getString(Airport.IATA_COLUMN));
            airport.setLongitude(resultSet.getDouble(Airport.LONGITUDE_COLUMN));
            airport.setLatitude(resultSet.getDouble(Airport.LATITUDE_COLUMN));
            airport.setAirport_name(resultSet.getString(Airport.AIRPORT_NAME_COLUMN));
            airport.setCity_name(resultSet.getString(Airport.CITY_NAME));
            return airport;
        }
    }

}
