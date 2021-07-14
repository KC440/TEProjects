package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCTransfersDao implements TransfersDao {

    private JdbcTemplate jdbcTemplate;



    public JDBCTransfersDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Transfer> allMyTransfers(long userAccountId) {
        String sql = "SELECT * FROM transfers WHERE account_from = ? OR account_to = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userAccountId, userAccountId);

        List<Transfer> myTransfers = new ArrayList<>();

        while (result.next()) {
            Transfer transfer = mapRowToTransfer(result);
            myTransfers.add(transfer);
        }
        return myTransfers;
    }
    @Override
    public void create(long fromId, long toId, double amount) {
        String sql = "INSERT INTO transfers VALUES (default, ?, ?, ?, ?, ?)";

        try {
            jdbcTemplate.update(sql, 2, 2, fromId, toId, amount);

        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public Transfer transferById(long myId, long transferId) {
        Transfer transfer = null;
        String sql = "SELECT * FROM transfers WHERE transfer_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferId);
        if (results.next()) {
            transfer = mapRowToTransfer(results);
        }
        if(transfer != null && (transfer.getAccountFrom() == myId || transfer.getAccountTo() == myId)) {
        return transfer;
        } else
            return null;
    }

    private Transfer mapRowToTransfer(SqlRowSet results) {
        Transfer transfer = new Transfer();
        transfer.setTransferId(results.getInt("transfer_id"));
        transfer.setTransferTypeId(results.getInt("transfer_type_id"));
        transfer.setTransferStatusId(results.getInt("transfer_status_id"));
        transfer.setAccountFrom(results.getInt("account_from"));
        transfer.setAccountTo(results.getInt("account_to"));
        transfer.setAmount(results.getDouble("amount"));
        return transfer;
    }

   /* @Override
    public String sendTransfer(long accountFrom, long accountTo, double amount) {
        if (accountFrom == accountTo) {
            return "Invalid transfer request.";
        }//if account from balance is >= amount requested
        //not sure about any of these logic statements
        if (accountFrom < amount) {
            String sql = "INSERT INTO transfers (transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                    "VALUES (2, 2, ?, ?, ? ";
            jdbcTemplate.update(sql, accountFrom, accountTo, amount);

            return "Transfer Completed";
        } else {
            return "Transaction failed";
        }
    }
    @Override
    public String requestTransfer(long accountFrom, long accountTo, double amount) {
            if (accountFrom == accountTo) {
                return "Invalid transfer request.";
        }
            if(amount == 1) {
                String sql = "INSERT INTO transfers (transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                        "VALUES (1, 1, ?, ?, ? ";
                jdbcTemplate.update(sql, accountFrom, accountTo, amount);

                return "Request sent";
            } else {
                return "Request failed";
            }
    }

    @Override
    public List<Transfer> pendingTransfers(int userId) {
        List<Transfer> list = new ArrayList<>();
        String sql = "SELECT t.*, u.username AS userFrom, v.username AS userTo FROM transfers t\n" +
                "        JOIN accounts a ON t.account_from = a.account_id\n" +
                "        JOIN accounts b ON t.account_to = b.account_id\n" +
                "        JOIN users u ON a.user_id = u.user_id\n" +
                "        JOIN users v ON b.user_id = v.user_id\n" +
                "        WHERE transfer_status_id = 1 AND (account_from = ? OR account_to = ?)";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, userId);
        while(results.next()) {
            Transfer transfer = mapRowToTransfer(results);
            list.add(transfer);
        }
        return list;
    }

    @Override
    public String updateTransferStatus(Transfer transfer, int transferStatusId) {
        if(transferStatusId == 3) {
            String sql = "UPDATE transfers SET transfers transfer_status_id = ? WHERE transfer_id = ? ";
            jdbcTemplate.update(sql, transferStatusId, transfer.getTransferId());
            //add amount to balance for receiving account
            //subtract amount from sending account
            return "Transfer request updated";
        }
        if(transfer.getAccountFrom() == transfer.getAmount()) {
            String sql = "UPDATE transfers SET transfers transfer_status_id = ? WHERE transfer_id = ? ";
            jdbcTemplate.update(sql, transferStatusId, transfer.getTransferId());
            //add amount to balance for receiving account
            //subtract amount from sending account
            return "Transfer request updated";
        } else {
            return "Insufficient funds in account";
        }

    }*/



}
