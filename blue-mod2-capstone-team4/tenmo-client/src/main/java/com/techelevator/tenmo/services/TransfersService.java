package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;


public class TransfersService {


    private String baseUrl;
    private RestTemplate restTemplate = new RestTemplate();
    String TOKEN;

    public TransfersService(String url) {
        this.baseUrl = url;
    }

    public void createTransfer(long fromId, long toId, double amount, String token) {
        TOKEN = token;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(TOKEN);
        HttpEntity<Transfer> entity = new HttpEntity<>(headers);

        restTemplate.postForObject(
                baseUrl + "transfers/create/"+fromId+"/"+toId+"/"+amount, entity, Transfer.class);
    }

    public void listMyTransfers(long myId, String token) {
        TOKEN = token;
        Transfer[] transfers = restTemplate.exchange(
                baseUrl+"account/myTransfers/"+myId, HttpMethod.GET, makeAuthEntity(), Transfer[].class).getBody();
        System.out.println("---------------------------------------------------");
        System.out.println("          *****   My Transfers   *****");
        System.out.println(" ID                 To/From                 Amount");
        System.out.println("---------------------------------------------------");
        for(Transfer transfer : transfers) {
            if(transfer.getAccountFrom() == myId) {
            System.out.println(transfer.getTransferId()
                    +"                To: "
                    + transfer.getAccountTo()
                    +"             -$"
                    +String.format("%8.2f", transfer.getAmount()));
            } else if(transfer.getAccountTo() == myId) {
                System.out.println(transfer.getTransferId()
                        +"              From: "
                        + transfer.getAccountFrom()
                        +"              $"
                        +String.format("%8.2f", transfer.getAmount()));
            }
        }
    }

    public void getTransferDetails(long myId, long selectedTransferId, String token) {
        TOKEN = token;
        Transfer transfer = restTemplate.exchange(baseUrl+"account/transfer/"+myId+"/"+selectedTransferId,
                HttpMethod.GET, makeAuthEntity(), Transfer.class).getBody();

        if (transfer == null) {
            System.out.println("\r\n*** Invalid Choice ***");
        } else {
            String status = "";
            String type = "";
            switch (transfer.getTransferStatusId()) {
                case 1: {
                    status = "Pending";
                    break;
                }
                case 2: {
                    status = "Approved";
                    break;
                }
                case 3: {
                    status = "Rejected";
                    break;
                }
            }
            switch (transfer.getTransferTypeId()) {
                case 1: {
                    type = "Request";
                }
                case 2: {
                    type = "Send";
                }
            }
            System.out.println("\r\n---------------------------------");
            System.out.println("  ***** Transfer Details *****");
            System.out.println("---------------------------------");
            System.out.println("Id:                      "+selectedTransferId);
            if(transfer.getAccountFrom() == myId) {
                System.out.println("Amount:                 -$"+String.format("%.2f", transfer.getAmount()));

                System.out.println("Sent To:                 "+getUsernameByAccountId(transfer.getAccountTo()));
            } else if(transfer.getAccountTo() == myId) {
                System.out.println("Amount:                  $"+String.format("%.2f", transfer.getAmount()));

                System.out.println("Sent From:               "+getUsernameByAccountId(transfer.getAccountFrom()));
            }
            System.out.println("Status:                  "+status);
            System.out.println("Type:                    "+type);
            System.out.println("---------------------------------");

        }

    }

    private String getUsernameByAccountId(long id) {
        return restTemplate.getForObject(baseUrl+"users/"+id, String.class);
    }

    private HttpEntity makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(TOKEN);
        HttpEntity entity = new HttpEntity<>(headers);
        return entity;
    }

    }


