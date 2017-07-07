function init() {
completeField = document.getElementById("searchId");
completeTable = document.getElementById("complete-table");
autoRow = document.getElementById("auto-row");
console.log("hello1");
}
function appendUser(username,user_id)
{
  row=document.createElement("tr");
  cell = document.createElement("td");
  textnode=document.createTextNode(username);
  //cell.appendChild(textnode);
  row.appendChild(cell);
  completeTable.appendChild(row);

linkElement = document.createElement("a");
linkElement.className = "popupItem";
linkElement.setAttribute("href", "autocomplete?action=lookup&searchId=" + user_id);
linkElement.appendChild(document.createTextNode(username));
cell.appendChild(linkElement);
}

function doCompletion() {

var url = "autocomplete?action=complete&searchId=" + searchId.value;
req = initRequest();
req.open("GET", url, true);
req.setRequestHeader('Content-Type', 'text/xml');
req.onreadystatechange = callback;
req.send();
}

function initRequest() {
if (window.XMLHttpRequest) {
if (navigator.userAgent.indexOf('MSIE') != -1) {
isIE = true;
}
return new XMLHttpRequest();
} else if (window.ActiveXObject) {
isIE = true;
return new ActiveXObject("Microsoft.XMLHTTP");
}
}

function parseMessages(responseXML) {
if (responseXML == null) {
return false;
} else {
  
var user = responseXML.getElementsByTagName("users")[0];
if (user.childNodes.length > 0) {
completeTable.setAttribute("bordercolor", "black");
completeTable.setAttribute("border", "1");
for (loop = 0; loop < user.childNodes.length; loop++) {
var user = user.childNodes[loop];
var username = user.getElementsByTagName("username")[0];
var user_Id = user.getElementsByTagName("user_id")[0];
appendUser(username.childNodes[0].nodeValue, user_Id.childNodes[0].nodeValue);
}
}
}
}

function callback() {
clearTable();

if (req.readyState == 4) {
    

parseMessages(req.responseXML);
}
}

function clearTable() {
if (completeTable.getElementsByTagName("tr").length > 0) {
//completeTable.style.display = 'none';
for (loop = completeTable.childNodes.length -1; loop >= 0 ; loop--) {
completeTable.removeChild(completeTable.childNodes[loop]);
}
}
}
