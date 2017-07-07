function init() {
completeField = document.getElementById("searchId");
completeTable = document.getElementById("complete-table");
autoRow = document.getElementById("auto-row");
}

function appendProduct(productName,productId)
{
row=document.createElement("tr");
cell = document.createElement("td");
textnode=document.createTextNode(productName);
//cell.appendChild(textnode);
//row.appendChild(cell);
completeTable.appendChild(row);
linkElement = document.createElement("a");
linkElement.className = "popupItem";
linkElement.setAttribute("href", "autocomplete?action=lookup&searchId=" + productId);
linkElement.appendChild(document.createTextNode(productName));
cell.appendChild(linkElement);
console.log("in append status");

}

function doCompletion() {
var url = "autocomplete?action=complete&searchId=" + searchId.value;
req = initRequest();
req.open("GET", url, true);
req.setRequestHeader('Content-Type', 'text/xml');
req.onreadystatechange = callback;
req.send();
console.log("in complete state");
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
var products = responseXML.getElementsByTagName("products")[0];
if (products.childNodes.length > 0) {
completeTable.setAttribute("bordercolor", "black");
completeTable.setAttribute("border", "1");
for (loop = 0; loop < products.childNodes.length; loop++) {
var product = products.childNodes[loop];
var productName = product.getElementsByTagName("productName")[0];
var productId = product.getElementsByTagName("id")[0];
appendProduct(productName.childNodes[0].nodeValue, productId.childNodes[0].nodeValue);
}
}
}
}

function callback() {
clearTable();
if (req.readyState == 4) {
console.log("in ready state");
parseMessages(req.responseXML);
}
}

function clearTable() {
if (completeTable.getElementsByTagName("tr").length > 0) {
completeTable.style.display = 'none';
for (loop = completeTable.childNodes.length -1; loop >= 0 ; loop--) {
completeTable.removeChild(completeTable.childNodes[loop]);
}
}
}