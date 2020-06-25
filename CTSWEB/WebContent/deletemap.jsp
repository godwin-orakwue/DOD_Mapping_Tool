<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<html>
	<head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
      <link type="text/css" rel="stylesheet" href="css/jxp.css"/>
      <link href="css/jxp.css" rel="stylesheet" media="screen"/>
      <link type="text/css" href="css/evenodd.css" rel="stylesheet" media="screen" />
		<title>Delete map</title>
	</head>
	<body><p/>
<f:view>

          <h:outputText id="appNameText" value="CTS" style="font-family: Arial, Helvetica, sans-serif; font-size:130%; color:#336699; " />
<a4j:form id="test1"><hr width="100%" class="borders" style="background-color:rgb(0,74,231); border-style:solid; border-width:medium; color:rgb(58,110,165);"/>
        <table id="table1">
        <tr>
          <td colspan="4"><H2>Are you sure you want to delete?</H2></td>
        </tr>
        <tr>
        <td>From Code</td>
        <td><h:inputText id="fromCode" value="#{backing_tab.mapData.fromCode}" readonly="true" /></td>
        <td><h:inputText id="fromDescription" value="#{backing_tab.mapData.fromDescription}" readonly="true" size="60" /></td>
        <td></td>
        </tr>
        <tr>
        <td>To Code</td>
        <td><h:inputText id="toCode" value="#{backing_tab.mapData.toCode}" readonly="true" /></td>
        <td><h:inputText id="toDescription" value="#{backing_tab.mapData.toDescription}" readonly="true" size="60" /></td>
        <td></td>
        </tr>
        <tr>
          <td></td>
        <td><h:inputHidden id="mapname" value="#{backing_tab.mapName}"  />
        </td><td></td>
        </tr>
        <tr>
          <td colspan="2">
              <div align="center"><a4j:commandButton value="Delete"
                                    action="#{backing_tab.delete}"
                                                   id="deletemap" styleClass="rich-button default" /><a4j:commandButton 
                                                   value="Cancel"
                                                   action="success"
                                                   id="return" immediate="true" styleClass="rich-button default" />
              </div>
            </td>
          </tr>
        </table>
</a4j:form>
		</f:view>
	</body>	
</html>  
