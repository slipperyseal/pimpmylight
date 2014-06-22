<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html"/>
    <xsl:strip-space elements="*"/>
    <xsl:output indent="no" method="html"/>
    <xsl:template match="/Backing">
        <html>
            <head>
                <meta charset="UTF-8"/>
                <title>Pimp My Light</title>
                <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
                <link href="{pathOffset}css/bootstrap.min.css" rel="stylesheet"/>
                <link href="{pathOffset}css/purestyle.css" rel="stylesheet" media="all"/>
                <link href="{pathOffset}favicon.ico" rel="icon"/>
            </head>
            <body>
                <script src="https://code.jquery.com/jquery.js"/>
                <script src="{pathOffset}js/bootstrap.min.js"/>
                <script src="{pathOffset}js/pimp.js"/>

                <xsl:for-each select="notice">
                    <p><xsl:value-of select="."/></p>
                </xsl:for-each>

                <div class="topspacer"/>
                <h3>Pimp My Light</h3>

                click below to control <a href="http://twitter.com/slipperyseal">Slippery Seal</a>'s Raspberry Pi<br/>
                powered railway signal in realtime<br/><br/>

                <xsl:for-each select="status">
                    <xsl:if test=". = '404'">
                        <div class="container">
                            <h1>404 Not Found. Awkward.</h1>
                        </div>
                    </xsl:if>
                </xsl:for-each>

                <div class="outer">
                    <div class="outermain">
                        <xsl:for-each select="artefacts">
                            <xsl:for-each select="Status/railwaySignal">
                                <div class="lightwrapper">
                                    <xsl:for-each select="Light">
                                        <div class="lightimage">
                                            <a href="?update={name}"><img id="{name}" class="lightimage" onclick="javascript:updateLights('{name}')" src="{pathOffset}images/{name}-{illuminated}.jpg" border="0"/></a>
                                            <!--<img id="{name}" class="lightimage" onclick="javascript:updateLights('{name}')" src="{pathOffset}images/{name}-{illuminated}.jpg" border="0"/>-->
                                        </div>
                                    </xsl:for-each>
                                </div>
                            </xsl:for-each>
                        </xsl:for-each>
                    </div>
                </div>

            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
