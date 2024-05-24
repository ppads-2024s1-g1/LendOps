//!initial values
var selectedRow = null;
//!form submit logic
function onFormSubmit(e) {
  event.preventDefault();
  var formData = readFormData();
  if (selectedRow == null) {
    insertNewRecord(formData);
  } else {
    updateRecord(formData);
  }
  resetForm();
}
//!get method(Retriving the data)
function readFormData() {
  var formData = {};
  formData["Code"] = document.getElementById("Code").value;
  formData["Name"] = document.getElementById("Name").value;
  formData["Edn"] = document.getElementById("Edn").value;
  formData["Fone"] = document.getElementById("Fone").value;
  return formData;
}
//!insert the data (Post method)
function insertNewRecord(data) {
  var table = document
    .getElementById("storeList")
    .getElementsByTagName("tbody")[0];
  var newRow = table.insertRow(table.length);
  cell1 = newRow.insertCell(0);
  cell1.innerHTML = data.Code;
  cell2 = newRow.insertCell(1);
  cell2.innerHTML = data.Name;
  cell3 = newRow.insertCell(2);
  cell3.innerHTML = data.Edn;
  cell4 = newRow.insertCell(3);
  cell4.innerHTML = data.Fone;
  cell4 = newRow.insertCell(4);
  cell4.innerHTML = `<button onClick="onEdit(this)">Edit</button> <button onClick = "onDelete(this)">Delete</button>`;
}
//!edit and update the data (Update method)
//editing the data(get)
function onEdit(td) {
  selectedRow = td.parentElement.parentElement;
  document.getElementById("Code").value = selectedRow.cells[0].innerHTML;
  document.getElementById("Name").value = selectedRow.cells[1].innerHTML;
  document.getElementById("Edn").value = selectedRow.cells[2].innerHTML;
  document.getElementById("Fone").value = selectedRow.cells[3].innerHTML;
}
//updating the data
function updateRecord(formData) {
  selectedRow.cells[0].innerHTML = formData.Code;
  selectedRow.cells[1].innerHTML = formData.Name;
  selectedRow.cells[2].innerHTML = formData.Edn;
  selectedRow.cells[3].innerHTML = formData.Fone;
}
//!deleting the data (delete method)
//delete the data
function onDelete(td) {
  if (confirm("Are you sure about deleting😒 the data ?")) {
    row = td.parentElement.parentElement;
    document.getElementById("storeList").deleteRow(row.rowIndex);
    resetForm();
  }
}
//!reseting the values in form
function resetForm() {
  document.getElementById("Code").value = "";
  document.getElementById("Name").value = "";
  document.getElementById("Edn").value = "";
  document.getElementById("Fone").value = "";
  selectedRow = null;
}
