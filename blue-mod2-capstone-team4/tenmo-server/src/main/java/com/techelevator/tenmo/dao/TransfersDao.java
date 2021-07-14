package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransfersDao {

   List<Transfer> allMyTransfers(long userAccountId);
   Transfer transferById(long myId, long transferId);
   void create(long fromId, long toId, double amount);
   /*String sendTransfer (long accountFrom, long accountTo, double amount);
   String requestTransfer (long accountFrom, long accountTo, double amount);
   List<Transfer> pendingTransfers(int userId);
   String updateTransferStatus(Transfer transfer, int transferStatusId);*/
}
