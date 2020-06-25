<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich" %>
<html>
	<head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
      <link type="text/css" href="css/evenodd.css" rel="stylesheet" media="screen" />
		<title>View Map</title>
	</head>
	<body><p/>
<f:view>
          <h:outputText id="appNameText" value="CTS" style="font-family: Arial, Helvetica, sans-serif; font-size:130%; color:#336699; " />
<a4j:form id="test1"><hr width="100%" class="borders" style="background-color:rgb(0,74,231); border-style:solid; border-width:medium; color:rgb(58,110,165);"/>
<h2><h:outputText value="#{backing_tab.mapName}"/></h2>
<h:inputHidden id="mapname" value="#{backing_tab.mapName}" />
    <a4j:commandButton id="newmap" action="#{backing_tab.newmap}" value="New map" styleClass="rich-button default" /><br><br>
Filter Text: <h:inputText id="MAINFILTERTEXT" value="#{backing_tab.mainFilterText}" /><a4j:commandButton value="Filter"
                                    action="#{backing_tab.mainFilter}"
                                                   id="mainfilter" styleClass="rich-button default" />
        <rich:panel>
        <rich:dataTable id="queueviewid" rows="20" value="#{backing_tab.mapDetail}" var="details" rowClasses="odd-row, even-row" styleClass="stable">
            <rich:column sortBy="#{details.fromCode}" >
              <f:facet name="header">
                <h:outputText value="From Code"/>
              </f:facet>
              <h:outputText value="#{details.fromCode}"/>
            </rich:column>
            <rich:column sortBy="#{details.fromDescription}" >
              <f:facet name="header">
                <h:outputText value="From Description"/>
              </f:facet>
              <h:outputText value="#{details.fromDescription}"/>
            </rich:column>
            <rich:column sortBy="#{details.toCode}" >
              <f:facet name="header">
                <h:outputText value="To Code"/>
              </f:facet>
              <h:outputText value="#{details.toCode}"/>
            </rich:column>
            <rich:column sortBy="#{details.toDescription}">
              <f:facet name="header">
                <h:outputText value="To Description"/>
              </f:facet>
              <h:outputText value="#{details.toDescription}"/>
            </rich:column>
            <rich:column sortBy="#{details.active}" >
              <f:facet name="header">
                <h:outputText value="Active"/>
              </f:facet>
              <h:outputText value="#{details.active}"/>
            </rich:column>
            <rich:column>
              <f:facet name="header">
                <h:outputText value="Action"/>
              </f:facet>
              &nbsp;<a4j:commandLink action="#{backing_tab.deletemap}" rendered="#{backing_tab.admin}">
              <h:graphicImage value="/images/delete.gif" alt="Delete Map" style="border: none;" width="12" height="12" />
              <f:param name="mapName" value="#{backing_tab.mapName}" />
              <f:param name="fromCode" value="#{details.fromCode}" />
              </a4j:commandLink>&nbsp;&nbsp;
              <a4j:commandLink action="#{backing_tab.viewmapdetail}" rendered="#{backing_tab.admin}">
              <h:graphicImage value="/images/magnifier.gif" alt="Edit Map" style="border: none;" width="12" height="12" />
              <f:param name="mapName" value="#{backing_tab.mapName}" />
              <f:param name="fromCode" value="#{details.fromCode}" />
              </a4j:commandLink>
            </rich:column>
            <f:facet name="footer">
                <rich:datascroller id="quesc2" for="queueviewid" reRender="quesc1"  page="#{backing_tab.scrollerPage}" />
            </f:facet>
          </rich:dataTable>
        </rich:panel> 
<a4j:commandButton id="return2" action="success" value="Return" styleClass="rich-button default" />
</a4j:form>
</f:view>
	</body>	
</html>