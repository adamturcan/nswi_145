<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:S="http://schemas.xmlsoap.org/soap/envelope/"
    xmlns:ns2="http://services.university.com/"
    xmlns:ex="http://example.org/university#"
    xmlns:schema="http://schema.org/"
    exclude-result-prefixes="xsl S ns2">

  <xsl:output method="text" encoding="UTF-8"/>

  <xsl:template match="/">
    <xsl:text>@prefix ex: </xsl:text><xsl:text>&lt;</xsl:text><xsl:text>http://example.org/university#</xsl:text><xsl:text>&gt; .</xsl:text>
    <xsl:text>&#10;</xsl:text>  
    <xsl:text>@prefix xsd: </xsl:text><xsl:text>&lt;</xsl:text><xsl:text>http://www.w3.org/2001/XMLSchema#</xsl:text><xsl:text>&gt; .</xsl:text>
    <xsl:text>&#10;</xsl:text>  
    <xsl:text>@prefix schema: </xsl:text><xsl:text>&lt;</xsl:text><xsl:text>http://schema.org/</xsl:text><xsl:text>&gt; .</xsl:text>
    <xsl:text>&#10;&#10;</xsl:text> 

    <xsl:text>ex:verification_</xsl:text>
    <xsl:value-of select="//ns2:verifyDocumentsResponse/return/studentId"/>
    <xsl:text> a ex:VerificationRequest ;</xsl:text>

    <xsl:text>&#10;  schema:identifier "</xsl:text>
    <xsl:value-of select="//ns2:verifyDocumentsResponse/return/studentId"/>
    <xsl:text>"^^xsd:string ;</xsl:text>

    <xsl:text>&#10;  ex:result "</xsl:text>
    <xsl:value-of select="//ns2:verifyDocumentsResponse/return/verified"/>
    <xsl:text>"^^xsd:boolean ;</xsl:text>

    <xsl:text>&#10;  schema:description "</xsl:text>
    <xsl:value-of select="//ns2:verifyDocumentsResponse/return/comment"/>
    <xsl:text>"@en .</xsl:text>
  </xsl:template>

</xsl:stylesheet>
