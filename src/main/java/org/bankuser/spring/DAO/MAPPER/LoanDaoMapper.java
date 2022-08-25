package org.bankuser.spring.DAO.MAPPER;

import org.bankuser.spring.entity.Loan;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoanDaoMapper implements RowMapper<Loan> {
    @Override
    public Loan mapRow(ResultSet resultSet, int i) throws SQLException {
        Loan loan = new Loan();
        loan.setApplication(resultSet.getDate("applicationdata"));
        loan.setInterest(resultSet.getInt("interest"));
        loan.setMonth(resultSet.getInt("month"));
        loan.setMonthlyPayments(resultSet.getInt("monthlypayment"));
        return loan;
    }
}
