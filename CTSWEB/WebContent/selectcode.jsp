<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link type="text/css" href="css/evenodd.css" rel="stylesheet"
	media="screen" />
<title>View Map</title>
</head>
<body>
<p />
<f:view>
	<h:outputText id="appNameText" value="CTS"
		style="font-family: Arial, Helvetica, sans-serif; font-size:130%; color:#336699; " />
	<a4j:form id="test1">
		<hr width="100%" class="borders"
			style="background-color: rgb(0, 74, 231); border-style: solid; border-width: medium; color: rgb(58, 110, 165);" />
		<h2><h:outputText value="#{backing_tab.mapName}" /></h2><br>
		Filter Text: <h:inputText id="FILTERTEXT" value="#{backing_tab.filterText}" /><a4j:commandButton value="Filter"
                                    action="#{backing_tab.filter}" 
                                                   id="filter" styleClass="rich-button default" />
		<h:inputHidden id="mapname" value="#{backing_tab.mapName}" />
		<rich:panel>
			<h:selectOneListbox id="subscriptions" value="#{backing_tab.returncode}" size="30" >
                <f:selectItems value="#{backing_tab.lookupValue}" /> 
			</h:selectOneListbox>
		</rich:panel>
		<a4j:commandButton id="return2" action="#{backing_tab.lookupReturn}" value="Return"
			styleClass="rich-button default" />
		<a4j:commandButton id="return3" action="success" value="Cancel"
			styleClass="rich-button default" />
	</a4j:form>
</f:view>
</body>
</html>