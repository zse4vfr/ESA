<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                version="1.0">
    <xsl:output method="html" indent="yes"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>Drug</title>
            </head>
            <body>
                <h1>View drug</h1>
                <div style="text-align: right;">
                    <a href="/">Go to main</a>
                    <a href="/api/version2/drug/all">Go to all drugs</a>
                </div>
                <xsl:apply-templates/>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="DrugDTO">
        <div>
            <p>
                <div>id :
                    <xsl:value-of select="uniqueId"/>
                </div>
            </p>
            <p>
                <div>created at :
                    <xsl:value-of select="createdTimestamp"/>
                </div>
            </p>
            <p>
                <div>modified at :
                    <xsl:value-of select="modifiedTimestamp"/>
                </div>
            </p>
            <p>
                <div>name :
                    <xsl:value-of select="name"/>
                </div>
            </p>
            <p>
                <div>category :
                    <xsl:value-of select="category"/>
                </div>
            </p>
            <p>
                <div>quantity :
                    <xsl:value-of select="quantity"/>
                </div>
            </p>
            <p>
                <div>conditions :
                    <xsl:value-of select="conditions"/>
                </div>
            </p>
        </div>
    </xsl:template>
</xsl:stylesheet>