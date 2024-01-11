<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                version="1.0">
    <xsl:output method="html" indent="yes"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>Pharmacy</title>
            </head>
            <body>
                <h1>View pharmacy</h1>
                <div style="text-align: right;">
                    <a href="/">Go to main</a>
                    <a href="/api/version2/pharmacy/all">Go to all pharmacies</a>
                </div>
                <xsl:apply-templates/>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="PharmacyDTO">
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
                <div>address :
                    <xsl:value-of select="address"/>
                </div>
            </p>
            <p>
                <div>head :
                    <xsl:value-of select="head"/>
                </div>
            </p>
            <p>
                <div>district :
                    <xsl:value-of select="district"/>
                </div>
            </p>
            <p>
                <div>phone :
                    <xsl:value-of select="phone"/>
                </div>
            </p>
        </div>
        <p>drugs :</p>
        <xsl:for-each select="drugs/drugs">
            <div>name :
                <xsl:value-of select="drugName"/>
            </div>
            <div>category :
                <xsl:value-of select="category"/>
            </div>
            <div>quantity :
                <xsl:value-of select="quantity"/>
            </div>
            <div>conditions :
                <xsl:value-of select="conditions"/>
            </div>
            <p/>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>