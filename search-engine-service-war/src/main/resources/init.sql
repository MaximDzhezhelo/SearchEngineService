insert into DOCUMENTS (DOCUMENT_ID, DOCUMENT_NAME)
    VALUES (NEXT VALUE FOR DOCUMENT_ID_SEQ, 'TEST' );
COMMIT;

insert into TOKENS (TOKEN_ID, TOKEN, DOCUMENT_ID)
    VALUES (NEXT VALUE FOR TOKEN_ID_SEQ, 'token_1', 1);
COMMIT;