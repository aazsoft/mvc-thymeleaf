$(document).ready(function() {

	$("#jquery").click(function(event) {
		alert("Call JQuery codes");
	});

	$("#btnUserSearch").click(function(event) {
		$.ajax({
			type : 'POST',
			url : 'http://localhost:9000/user/search',
			//data : userSearchForm,
			dataType : 'json',
			success : function(data) {
				buildUserListTable(data);
			}
		});
		event.preventDefault();
	});

});

function buildUserListTable(data) {
	var bodyHtml = '';
	$.each(data, function(index, item) {
		bodyHtml += "<tr>";
		bodyHtml += "<td>" + item.id + "</td>";
		bodyHtml += "<td>" + item.username + "</td>";
		bodyHtml += "<td><a href='" + "/user/" + item.id + "'>" + item.email + "</a></td>";
		bodyHtml += "<td>" + item.role + "</td>";
		bodyHtml += "<td>" + item.age + "</td>";
		bodyHtml += "</tr>";
    });
	console.log(bodyHtml);
	$("#tbUserListBody").html(bodyHtml);
}
