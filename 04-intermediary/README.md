
1. SOAP Client (`com.university.client.PaymentProcessingClient.java`):

   - Odosiela SOAP request na `PaymentProcessingService`.
   - Sucastou requestu je custom header `ProcessingFee`, ktory sluzy na pridanie poplatku za platbu :D

2. SOAP Intermediary (`com.university.intermediary.SOAPIntermediary.java`):

   - Prijme SOAP request.
   - extrahuje header `ProcessingFee`.
   - Prepocita ciastku ktora sa posiela na cielovy server na zaklade headra.
   - preposle na cielovy server identicky request ale s upravenou ciastkov a uz bez headra.
   - Response ktory pride zo servra nasledna zase upravi pridanim headera o uspesnosti pridania poplatku `ProcessingStatus`
   - vrati vysledny response tomu kto volal intermediary.

3. priklad volania :

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://services.university.com/">
   <soapenv:Header>
        <ProcessingFee>5.00</ProcessingFee>
    </soapenv:Header>
   <soapenv:Body>
      <ser:processPayment>
         <studentId>1234</studentId>
         <amount>150</amount>
      </ser:processPayment>
   </soapenv:Body>
</soapenv:Envelope>

```

4. priklad vystupu

```xml
<?xml version="1.0" ?>
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
    <S:Header>
        <ser:ProcessingStatus xmlns:ser="http://services.university.com/">Success, fee included, with amount of 5.0. Total amount: 155.0</ser:ProcessingStatus>
    </S:Header>
    <S:Body>
        <ns2:processPaymentResponse xmlns:ns2="http://services.university.com/">
            <return>Platba uspesna, pre studenta so Student Id: 1234</return>
        </ns2:processPaymentResponse>
    </S:Body>
</S:Envelope>
```
