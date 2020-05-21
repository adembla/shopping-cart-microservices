insert into invoice(CUST_ID,DATE_OF_PURCHASE,MODE_PAY_ID,TOTAL_AMT) values(1,'2019-11-19 00:00:00','POS',55453);
insert into invoice(CUST_ID,DATE_OF_PURCHASE,MODE_PAY_ID,TOTAL_AMT) values(4,'2020-01-29 00:00:00','DEBIT CARD',1345);
insert into invoice(CUST_ID,DATE_OF_PURCHASE,MODE_PAY_ID,TOTAL_AMT) values(2,'2020-02-11 00:00:00','POS',2235);

insert into invoice_details(INVOICE_ID,PROD_ID, QUANTITY,UNIT_COST,TRANSACTION_ID) values(1,2,1,3150.0,'asfr3412bgfv');
insert into invoice_details(INVOICE_ID,PROD_ID, QUANTITY, UNIT_COST,TRANSACTION_ID) values(2,4,1,4300.0,'asfr3412bgfv');
insert into invoice_details(INVOICE_ID,PROD_ID, QUANTITY, UNIT_COST,TRANSACTION_ID) values(3,7,10,350.0,'asfr3412bgfv');
insert into invoice_details(INVOICE_ID,PROD_ID, QUANTITY, UNIT_COST,TRANSACTION_ID) values(1,1,2,2700.0,'asfr3412bgfv');

commit;
