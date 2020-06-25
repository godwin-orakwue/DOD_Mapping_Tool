<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich" %>
<html>
	<head>
         <META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
         <META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE">
         <META HTTP-EQUIV="EXPIRES" CONTENT="0">
         <link type="text/css" href="css/evenodd.css" rel="stylesheet" media="screen" />
		<title>Home</title>
	</head>
	<body><p/>
<f:view>
          <h:outputText id="appNameText" value="CTS" style="font-family: Arial, Helvetica, sans-serif; font-size:130%; color:#336699; " />
        <a4j:form id="test1"><hr width="100%" class="borders" style="background-color:rgb(0,74,231); border-style:solid; border-width:medium; color:rgb(58,110,165);"/>
<rich:tabPanel width="90%" headerAlignment="left" switchType="ajax" selectedTab="#{backing_tab.selectedTab}">
        <rich:tab label="Maps" id="maps">
<h:commandButton value="Export CSV" id="exportcsv" action="#{backing_tab.exportCSV}" styleClass="rich-button default" />
        <rich:dataTable value="#{backing_tab.maps}" var="map" rowClasses="odd-row, even-row" styleClass="stable">
          <rich:column  sortBy="#{map.mapName}">
            <f:facet name="header">
              <h:outputText value="Map name" />
            </f:facet>
            <h:outputText value="#{map.mapName}"/>
          </rich:column>
          <rich:column sortBy="#{map.fromOid}">
            <f:facet name="header">
              <h:outputText value="From OID" />
            </f:facet>
            <h:outputText value="#{map.fromOid}"/>
          </rich:column>
          <rich:column sortBy="#{map.toOid}">
            <f:facet name="header">
              <h:outputText value="To OID"/>
            </f:facet>
            <h:outputText value="#{map.toOid}"/>
          </rich:column>
          <rich:column sortBy="#{map.fromVocab}">
            <f:facet name="header">
              <h:outputText value="From Vocab"/>
            </f:facet>
            <h:outputText value="#{map.fromVocab}"/>
          </rich:column>
          <rich:column sortBy="#{map.toVocab}">
            <f:facet name="header">
              <h:outputText value="To Vocab"/>
            </f:facet>
            <h:outputText value="#{map.toVocab}"/>
          </rich:column>
          <rich:column>
            <f:facet name="header">
              <h:outputText value="Action"/>
            </f:facet>
              <a4j:commandLink action="#{backing_tab.viewmap}">
              <h:graphicImage value="/images/magnifier.gif" alt="View Map" style="border: none;" width="12" height="12" />
              <f:param name="mapname" value="#{map.mapName}" />
              </a4j:commandLink>&nbsp;
              <h:commandLink action="#{backing_tab.exportmap}">
              <h:graphicImage value="/images/edit.gif" alt="Export Map" style="border: none;" width="12" height="12" />
              <f:param name="fromOid" value="#{map.fromOid}" />
              </h:commandLink>
              </rich:column>
        </rich:dataTable>
        <br />
         </rich:tab>
        </rich:tabPanel>
</a4j:form>
		</f:view>
	</body>	
</html>  
