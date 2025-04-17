## 02-soap priečinok generatedWSDL

Tento priečinok nieje súčastou riešenia.
Vytvoril som ho osobne len z vlastného záujmu a nahral som tam wsdl subory, ktoré mi program vygeneroval.

## 03-wsdl

Vytvoril som jedno wsdl ktore funguje na obe vystavene sluzby.
Sluzby som upravil podla potreby zadania, teda aby kazda obsahovala 2 akcie, z toho dovodu som nahral priecinok 02-updated

### 05-cxf

Implementoval som sluŽbu document service validation z predchadzajucich rieseni, no tento krat pomocou apache cxf, s pouzitim ws-security.
overovanie je robene cez meno a heslo.
testovaci uzivatel v programe je : testuser ; heslo123

### 07-bpel

implementovane na zaklade wsdl z tasku 03

### 08-rest

implementovana sluzba document-verification z predchadzajucich zadani
