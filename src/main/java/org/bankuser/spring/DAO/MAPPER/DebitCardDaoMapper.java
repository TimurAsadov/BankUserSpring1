package org.bankuser.spring.DAO.MAPPER;

import org.bankuser.spring.entity.DebitCard;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DebitCardDaoMapper implements RowMapper<DebitCard> {

    @Override
    public DebitCard mapRow(ResultSet resultSet, int i) throws SQLException {
        DebitCard debitCard = new DebitCard();
        debitCard.setCardNumber(resultSet.getString("cardnumber"));
        debitCard.setExpireDate(resultSet.getDate("expiredate"));
        debitCard.setCvv(resultSet.getInt("cvv"));
        return debitCard;
    }
}
