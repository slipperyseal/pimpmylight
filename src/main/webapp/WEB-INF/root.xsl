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

                <h3>Pimp My Light</h3>
                <div class="outer">
                    <div class="outermain">
                        <xsl:for-each select="artefacts">
                            <xsl:for-each select="Status/railwaySignal">
                                <div class="lightwrapper">
                                    <xsl:for-each select="Light">
                                        <div class="lightimage">
                                            <!--<a href="?update={name}"><img id="{name}" class="lightimage" onclick="javascript:updateLights('{name}')" src="{pathOffset}images/{name}-{illuminated}.jpg" border="0"/></a>-->
                                            <img id="{name}" class="lightimage" onclick="javascript:changeLight('{name}')" src="{pathOffset}images/{name}-{illuminated}.jpg" border="0"/>
                                        </div>
                                    </xsl:for-each>
                                </div>
                            </xsl:for-each>
                        </xsl:for-each>
                    </div>

                    <div class="para">
                        I'm a <a href="http://www.raspberrypi.org/">Raspberry Pi</a> controlled railway signal in Melbourne, Australia.
                        I'm running <a href="https://github.com/slipperyseal/silicone">Silicone</a> on Apache Tomcat and I'm <a href="https://github.com/slipperyseal/pimpmylight">open source</a>.
                        Pimp my lights and watch others do the same.
                        I also tweet live updates <a href="https://twitter.com/pimpmylight">@PimpMyLight</a>
                    </div>

                    <xsl:for-each select="status">
                        <xsl:if test=". = '404'">
                            <div class="container">
                                <h1>404 Not Found. Awkward.</h1>
                            </div>
                        </xsl:if>
                    </xsl:for-each>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
