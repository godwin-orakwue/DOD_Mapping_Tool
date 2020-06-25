<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<html>
	<head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
      <link type="text/css" rel="stylesheet" href="css/jxp.css"/>
      <link href="css/jxp.css" rel="stylesheet" media="screen"/>
      <link type="text/css" href="css/evenodd.css" rel="stylesheet" media="screen" />
		<title>Edit map</title>
	</head>
	<body><p/>
<f:view>
          <h:outputText id="appNameText" value="CTS" style="font-family: Arial, Helvetica, sans-serif; font-size:130%; color:#336699; " />
<a4j:form id="test1"><hr width="100%" class="borders" style="background-color:rgb(0,74,231); border-style:solid; border-width:medium; color:rgb(58,110,165);"/>
<h2><h:outputText value="#{backing_tab.mapName}"/></h2>
        <table id="table1">
        <tr>
        <td>From Code&nbsp;&nbsp;<a4j:commandLink action="#{backing_tab.vaselectcode}" rendered="#{backing_tab.vaselectcodedisp}" >
              <h:graphicImage value="/images/magnifier.gif" alt="lookupfrom" style="border: none;" width="12" height="12" />
              <f:param name="mapName" value="#{backing_tab.mapName}" />
              <f:param name="oid" value="#{backing_tab.mapData.fromOid}" />
              </a4j:commandLink>
        </td>
        <td><h:inputText id="fromCode" value="#{backing_tab.mapData.fromCode}" maxlength="75" /></td>
        <td><h:inputText id="fromDescription" value="#{backing_tab.mapData.fromDescription}" size="60" maxlength="255" /></td>
        </tr>
        <tr>
        <td>To Code&nbsp;&nbsp;<a4j:commandLink action="#{backing_tab.selectcode}" rendered="#{backing_tab.selectcodedisp}" >
              <h:graphicImage value="/images/magnifier.gif" alt="lookupto" style="border: none;" width="12" height="12" />
              <f:param name="mapName" value="#{backing_tab.mapName}" />
              <f:param name="oid" value="#{backing_tab.mapData.toOid}" />
              </a4j:commandLink>
        </td>
        <td><h:inputText id="toCode" value="#{backing_tab.mapData.toCode}" maxlength="75" /></td>
        <td><h:inputText id="toDescription" value="#{backing_tab.mapData.toDescription}" size="60"  maxlength="255" /></td>
        </tr>
        <tr>
        <td>Active</td>
        <td><h:selectBooleanCheckbox id="active" value="#{backing_tab.mapData.active}" /></td>
        <td>&nbsp;</td>
        </tr>
        <tr>
        <td><h:inputHidden id="mapname" value="#{backing_tab.mapName}"  /></td>
        <td><h:inputHidden id="currfromCode" value="#{backing_tab.currFromCode}" /></td>
        <td>&nbsp;</td>
        </tr>
        <tr>
          <td colspan="3">
              &nbsp;<h:outputText id="saveError"
                            binding="#{backing_tab.saveError}"
                            style="color:Red; font-weight:bolder;"/>
            </td>
          </tr>
        <tr>
          <td colspan="2">
              <div align="center"><a4j:commandButton value="Save"
                                    action="#{backing_tab.save}" rendered="#{backing_tab.admin}"
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